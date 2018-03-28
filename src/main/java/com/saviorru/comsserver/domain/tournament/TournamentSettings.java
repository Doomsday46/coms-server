package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.schematictype.Scheme;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.winnerindetifier.WinnerIdentifier;

import java.time.LocalDateTime;

public interface TournamentSettings {

    public DateDispatcher getDateDispatcher();
    public Scheme getScheme(Integer playersCount);
    public WinnerIdentifier getWinnerIdentifier();
    public SchemeType getSchemeType();
    public LocalDateTime getStartDate();
    public TimeSettings getTimeSettings();
    public String getTournamentName();
    public Integer getPrizePlacesCount();

}
