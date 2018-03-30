package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.schematictype.OlympicScheme;
import com.saviorru.comsserver.domain.schematictype.RoundScheme;
import com.saviorru.comsserver.domain.schematictype.Scheme;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.winnerindetifier.OlympicWinnerIndentifier;
import com.saviorru.comsserver.domain.winnerindetifier.RoundWinnerIdentifier;
import com.saviorru.comsserver.domain.winnerindetifier.WinnerIdentifier;
import com.saviorru.comsserver.exceptions.EmptyParameter;

import java.time.LocalDateTime;

public class TournamentSettingsImpl implements TournamentSettings {
    private SchemeType schemeType;
    private LocalDateTime startDate;
    private TimeSettings timeSettings;
    private String tournamentName;


    private Integer prizePlacesCount;

    public TournamentSettingsImpl(String tournamentName, SchemeType schemeType, LocalDateTime startDate, TimeSettings timeSettings)
    {
        if (schemeType == null || startDate == null || timeSettings == null) throw new NullPointerException();
        if (tournamentName.isEmpty()) throw new EmptyParameter("Empty tournament name");
        if (startDate.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Can't set the past date and time");
        this.schemeType = schemeType;
        this.startDate = startDate;
        this.timeSettings = timeSettings;
        this.tournamentName = tournamentName;
        if (schemeType == SchemeType.ROUND) {
            this.prizePlacesCount = 3;
        }
        if (schemeType == SchemeType.OLYMPIC) {
            this.prizePlacesCount = 1;
        }
    }

    public void setPrizePlacesCount(Integer prizePlacesCount) {
        this.prizePlacesCount = prizePlacesCount;
    }
    @Override
    public DateDispatcher getDateDispatcher()  {
        return new DateDispatcher(startDate, timeSettings);
    }

    @Override
    public Scheme getScheme(Integer playersCount){

        if (schemeType == SchemeType.ROUND) {
            return new RoundScheme(playersCount);
        }
        if (schemeType == SchemeType.OLYMPIC) {
            return new OlympicScheme(playersCount);
        }
        return null;
    }

    @Override
    public WinnerIdentifier getWinnerIdentifier() {
        if (schemeType == SchemeType.ROUND) {
            return new RoundWinnerIdentifier();
        }
        if (schemeType == SchemeType.OLYMPIC) {
            return new OlympicWinnerIndentifier();
        }
        return null;
    }

    public SchemeType getSchemeType() {
        return schemeType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public TimeSettings getTimeSettings() {
        return timeSettings;
    }

    @Override
    public String getTournamentName() {
        return this.tournamentName;
    }

    @Override
    public Integer getPrizePlacesCount() {
        return prizePlacesCount;
    }
}
