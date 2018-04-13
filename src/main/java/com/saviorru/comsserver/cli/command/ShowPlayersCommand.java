package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.localization.SingletonResourceBundle;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class ShowPlayersCommand implements Command {

    private Tournament tournament;

    public ShowPlayersCommand(Tournament tournament){
        if (tournament == null) throw new NullPointerException("Tournament not created");
        this.tournament = tournament;
    }

    @Override
    public Boolean execute(){
        int number = 0;
        for(Player player: tournament.getPlayers()){
            number++;
            System.out.println(SingletonResourceBundle.getResourceBundle().getString("text.player") + number + " : " + player.getFirstName() + " , "
                    + player.getLastName() + " , " + SingletonResourceBundle.getResourceBundle().getString("age") + ": " + player.getAge());

        }
        return number > 0;
    }

}
