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


    public TennisTournament(PlayerDispatcher playerDispatcher, LocationDispatcher locationDispatcher, TournamentSettings tournamentSettings, Schedule schedule) throws Exception {
        if (playerDispatcher == null || locationDispatcher == null ||  schedule == null || tournamentSettings == null)
            throw new NullPointerException();
        if (playerDispatcher.getAllPlayers().isEmpty() || locationDispatcher.getAllLocations().isEmpty())
            throw new Exception("Empty parameter");
        if(playerDispatcher.getAllPlayers().size() < 2 || locationDispatcher.getAllLocations().size() < 1)
            throw new Exception("Not enough players or tables");
        this.playerDispatcher = playerDispatcher;
        this.schedule = schedule;
        this.locationDispatcher = locationDispatcher;
        this.isStart = false;
        this.tournamentSettings = tournamentSettings;
        this.prizePlaces = new ArrayList<>();
        generationSchedule();
    }

    private TennisTournament(TennisTournament target) throws Exception {
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

    private void generationSchedule() throws Exception {
            this.winnerIdentifier = tournamentSettings.getWinnerIdentifier();
            generate(tournamentSettings.getScheme(playerDispatcher.getAllPlayers().size()));
    }

    private void generate(Scheme scheme) throws Exception {
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
    public Schedule getSchedule() throws StartTournamentException {
        if (!isStart) throw new StartTournamentException("Tournament is not started");
        return this.schedule;
    }

    @Override
    public List<Location> getLocations() throws StartTournamentException {
        if (isStart) throw new StartTournamentException("Tournament is not started");
        return locationDispatcher.getAllLocations();
    }

    @Override
    public SchemeType getSchemeType() {
        return this.tournamentSettings.getSchemeType();
    }

    @Override
    public void start() throws Exception {
        if (!this.isStart) {
            this.isStart = true;
            return;
        }
        throw new Exception("Tournament is started");
    }

    @Override
    public void finish() throws Exception {
        if (this.isStart) {
            if (schedule.getMatchesByState(MatchState.PLAYED).size() == 0) throw new FinishTournamentException("Matches didn't played");
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
    public Match getNextMatch() throws Exception {
        if (!(isStart)) throw new Exception("Tournament is not started");
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
    public void finishMatch(Match match, Score score) throws Exception {
        if (match == null || score == null) throw new NullPointerException();
        if (!(isStart)) throw new Exception("Tournament is not started");
        match.setPoints(score.getPointsFirstSide(), score.getPointsSecondSide());
        match.setMatchState(MatchState.PLAYED);
        this.locationDispatcher.freeLocation(match.getLocation());
        this.schedule = this.scheduleGenerator.updateSchedule(match, this.schedule);
    }

    @Override
    public void finishMatches(List<Match> matches, List<Score> points) throws Exception {
        if (!(isStart)) throw new Exception("Tournament is not started");
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
    public Player getThePrizePlace(int prizePlace) throws Exception {
        if (prizePlace < 0 || prizePlace > tournamentSettings.getPrizePlacesCount())
            throw new Exception("Not a correct prize-winning place");
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
        try {
            return tournamentSettings.getDateDispatcher().getStartDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LocalDateTime getEndDate() {
        try {
            return tournamentSettings.getDateDispatcher().getEndDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PlayerGrid getPlayerGrid() throws Exception {
        return this.scheduleGenerator.getScheme().getPlayerGrid();
    }

    @Override
    public Scheme getScheme() {
        return this.scheduleGenerator.getScheme();
    }


    @Override
    public TournamentReport getTournamentReport() throws Exception {
        return new TournamentReport(this);
    }

    @Override
    public Tournament clone() {
        try {
            return new TennisTournament(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public TournamentSettings getTournamentSettings() {
        return tournamentSettings;
    }
}
