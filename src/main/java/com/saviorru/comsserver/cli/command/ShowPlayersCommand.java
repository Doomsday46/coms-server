package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class ShowPlayersCommand implements Command {

    private Tournament tournament;

    public ShowPlayersCommand(Tournament tournament){
        this.tournament = tournament;
    }

    @Override
    public Boolean execute(){
        int number = 0;
        for(Player player: tournament.getPlayers()){
            number++;
            System.out.println("Игрок " + number + " : " + player.getFirstName()  + " , "
                            + player.getLastName() + " , " +  " age: " + player.getAge());

        }
        return number > 0;
    }

}
