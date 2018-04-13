package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.*;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Player;

import java.time.LocalDate;

public class CommandFactory {


    public Command getCommand(CommandParameter commandParameter) {
        switch (commandParameter.getNameCommand()) {
            case "set player":
                return new AddPlayerCommand(new Player((String) commandParameter.getParameter(0),
                                                        (String) commandParameter.getParameter(1),
                                                        (LocalDate) commandParameter.getParameter(3)));
            case "show schedule": {
                return new ShowScheduleCommand();
            }
            case "show players":
                return new ShowPlayersCommand();
            case "show locations":
                return new ShowLocationCommand();
            case "set location":
                return new AddLocationCommand(new Location((String) commandParameter.getParameter(0),
                                                            (String) commandParameter.getParameter(1)));
            case "start":
                return new StartTournamentCommand();
            case "finish":
                return new FinishTournamentCommand();
            case "create tournament":
                return new CreateTennisTournamentCommand();
            case "set match result":
                return new AddMatchResultCommand(commandParameter);
            case "set setting":
                return new AddSettingTournamentCommand(commandParameter);
            case "show grid":
                return new ShowGridCommand();
            case "report":
                return new ReportCommand();
            case "choose tournament":
                return new ChooseTournamentCommand(commandParameter);
        }
        throw new NullPointerException("Command not found");
    }
}
