package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.CommandParameter;
import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import com.saviorru.comsserver.domain.tournament.TournamentSettingsImpl;

import java.time.LocalDateTime;

public class AddSettingTournamentCommand implements Command {

    private TournamentBuilder tournamentBuilder;
    private CommandParameter commandParameter;

    public AddSettingTournamentCommand(TournamentBuilder tournamentBuilder, CommandParameter commandParameter) {
        this.tournamentBuilder = tournamentBuilder;
        this.commandParameter = commandParameter;
    }

    @Override
    public Boolean execute(){
        SchemeType schemeType = null;
        tournamentBuilder.setTimeSettings(new TimeSettings());
        TournamentSettings tournamentSettings = new TournamentSettingsImpl((String) commandParameter.getParameter(0),
                                                                            (SchemeType) commandParameter.getParameter(1),
                                                                            (LocalDateTime) commandParameter.getParameter(2),
                                                                            tournamentBuilder.getTimeSettings());
        tournamentBuilder.setTournamentSettings(tournamentSettings);
        return true;
    }

}
