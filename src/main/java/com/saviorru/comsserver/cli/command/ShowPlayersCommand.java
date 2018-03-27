package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.TournamentBuilder;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.model.Player;

public class ShowPlayersCommand implements Command {

    private TournamentBuilder tournamentBuilder;

    public ShowPlayersCommand(TournamentBuilder tournamentBuilder){
        this.tournamentBuilder = tournamentBuilder;
    }

    @Override
    public Boolean execute() throws Exception {
        int number = 0;
        for(Player player: tournamentBuilder.getPlayerDispatcher().getAllPlayers()){
            number++;
            System.out.println("Игрок " + number + " : " + player.getFirstName()  + " , "
                            + player.getLastName() + " , " +  " age: " + player.getAge());

        }
        return number > 0;
    }

}
