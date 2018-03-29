package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.MatchState;
import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.model.*;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleGenerator;
import com.saviorru.comsserver.domain.schedule.ScheduleGeneratorImpl;
import com.saviorru.comsserver.domain.schematictype.*;
import com.saviorru.comsserver.domain.winnerindetifier.WinnerIdentifier;
import com.saviorru.comsserver.exceptions.EmptyParameter;
import com.saviorru.comsserver.exceptions.FinishTournamentException;
import com.saviorru.comsserver.exceptions.StartTournamentException;
import sun.invoke.empty.Empty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TennisTournament implements Tournament {


    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private PlayerDispatcher playerDispatcher;
    private boolean isStart;
    private List<PrizePlace> prizePlaces;
    private ScheduleGenerator scheduleGenerator;
    private WinnerIdentifier winnerIdentifier;
    private TournamentSettings tournamentSettings;


    public TennisTournament(PlayerDispatcher playerDispatcher, LocationDispatcher locationDispatcher, TournamentSettings tournamentSettings, Schedule schedule) {
        if (playerDispatcher == null || locationDispatcher == null || schedule == null || tournamentSettings == null)
            throw new NullPointerException();
        if (playerDispatcher.getAllPlayers().isEmpty() || locationDispatcher.getAllLocations().isEmpty())
            throw new IllegalArgumentException("Empty parameter");
        if (playerDispatcher.getAllPlayers().size() < 2 || locationDispatcher.getAllLocations().size() < 1)
            throw new IllegalArgumentException("Not enough players or tables");
        this.playerDispatcher = playerDispatcher;
        this.schedule = schedule;
        this.locationDispatcher = locationDispatcher;
        this.isStart = false;
        this.tournamentSettings = tournamentSettings;
        this.prizePlaces = new ArrayList<>();
        generationSchedule();
    }

    private TennisTournament(TennisTournament target) {
        if (target != null) {
            this.playerDispatcher = target.playerDispatcher;
            this.schedule = target.schedule;
            this.locationDispatcher = target.locationDispatcher;
            this.isStart = target.isStart;
            this.winnerIdentifier = target.winnerIdentifier;
            this.prizePlaces = target.prizePlaces;
            this.scheduleGenerator = target.scheduleGenerator;
            this.tournamentSettings = target.tournamentSettings;
        }
    }

    private void generationSchedule() {
        this.winnerIdentifier = tournamentSettings.getWinnerIdentifier();
        generate(tournamentSettings.getScheme(playerDispatcher.getAllPlayers().size()));
    }

    private void generate(Scheme scheme) {
        this.scheduleGenerator = new ScheduleGeneratorImpl(this.playerDispatcher, this.locationDispatcher, tournamentSettings.getDateDispatcher(), scheme);
        this.schedule = this.scheduleGenerator.generateSchedule();
    }

    @Override
    public String getName() {
        return this.tournamentSettings.getTournamentName();
    }

    @Override
    public List<Player> getPlayers() {
        return this.playerDispatcher.getAllPlayers();
    }

    @Override
    public Schedule getSchedule() {
        if (!isStart) throw new StartTournamentException("Tournament is not started");
        return this.schedule;
    }

    @Override
    public List<Location> getLocations() {
        if (isStart) throw new StartTournamentException("Tournament is not started");
        return locationDispatcher.getAllLocations();
    }

    @Override
    public SchemeType getSchemeType() {
        return this.tournamentSettings.getSchemeType();
    }

    @Override
    public void start() {
        if (!this.isStart) {
            this.isStart = true;
            return;
        }
        throw new StartTournamentException("Tournament is started");
    }

    @Override
    public void finish() {
        if (this.isStart) {
            if (schedule.getMatchesByState(MatchState.PLAYED).size() == 0)
                throw new FinishTournamentException("Matches didn't played");
            if (this.schedule.getAllMatches().size() != this.scheduleGenerator.getScheme().getMaxPairCount())
                return;
            List<Player> winners = this.winnerIdentifier.identifyWinners(schedule.getAllMatches());
            for (int i = 0; i < this.tournamentSettings.getPrizePlacesCount(); i++) {
                this.prizePlaces.add(new PrizePlaceThePlayer(winners.get(i), i + 1));
            }
            tournamentSettings.getDateDispatcher().setEndDate(LocalDateTime.now());
        } else
            throw new FinishTournamentException("Tournament is not started");
    }

    @Override
    public Match getNextMatch() {
        if (!(isStart)) throw new StartTournamentException("Tournament is not started");
        List<Match> matchesByState = schedule.getMatchesByState(MatchState.NOTPLAYED);
        if (matchesByState.size() == 0) return null;
        Match nextMatch = matchesByState.get(0);
        for (int i = 0; i < matchesByState.size() - 1; i++) {
            if (matchesByState.get(i).getDate().isBefore(matchesByState.get(i + 1).getDate())) {
                nextMatch = matchesByState.get(i);
            }
        }
        return nextMatch;
    }

    @Override
    public void finishMatch(Match match, Score score) {
        if (match == null || score == null) throw new NullPointerException();
        if (!(isStart)) throw new StartTournamentException("Tournament is not started");
        match.setPoints(score.getPointsFirstSide(), score.getPointsSecondSide());
        match.setMatchState(MatchState.PLAYED);
        this.locationDispatcher.freeLocation(match.getLocation());
        this.schedule = this.scheduleGenerator.updateSchedule(match, this.schedule);
    }

    @Override
    public void finishMatches(List<Match> matches, List<Score> points) {
        if (!(isStart)) throw new StartTournamentException("Tournament is not started");
        if (matches == null || points == null) throw new NullPointerException();
        for (int i = 0; i < matches.size(); i++) {
            finishMatch(matches.get(i), points.get(i));
        }
    }

    @Override
    public boolean isStart() {
        return this.isStart;
    }

    @Override
    public Player getThePrizePlace(int prizePlace) {
        if (prizePlace < 0 || prizePlace > tournamentSettings.getPrizePlacesCount())
            throw new IllegalArgumentException("Not a correct prize-winning place");
        if (prizePlaces != null) {
            for (PrizePlace thePrizePlace : prizePlaces) {
                if (thePrizePlace.getPrizePlace() == prizePlace) {
                    return thePrizePlace.getPlayer();
                }
            }
        }
        return null;
    }

    @Override
    public LocalDateTime getStartDate() {
        return tournamentSettings.getDateDispatcher().getStartDate();
    }

    @Override
    public LocalDateTime getEndDate() {
        return tournamentSettings.getDateDispatcher().getEndDate();
    }

    @Override
    public PlayerGrid getPlayerGrid() {
        return this.scheduleGenerator.getScheme().getPlayerGrid();
    }

    @Override
    public Scheme getScheme() {
        return this.scheduleGenerator.getScheme();
    }


    @Override
    public TournamentReport getTournamentReport() {
        return new TournamentReport(this);
    }

    @Override
    public Tournament clone() {
        return new TennisTournament(this);
    }

    @Override
    public TournamentSettings getTournamentSettings() {
        return tournamentSettings;
    }
}
