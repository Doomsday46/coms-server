package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

public class TournamentService {

    private TournamentBuilder tournamentBuilder;
    private TournamentManager tournamentManager;
    private CommandFactory commandFactory;

    public TournamentService(TournamentBuilder tournamentBuilder, TournamentManager tournamentManager, CommandFactory commandFactory) {
        this.tournamentBuilder = tournamentBuilder;
        this.tournamentManager = tournamentManager;
        this.commandFactory = commandFactory;
    }

    public boolean executeCommand(CommandParameter commandParameter){
        commandFactory.getCommand(tournamentManager,tournamentBuilder,commandParameter);
        return true;
    }
}
