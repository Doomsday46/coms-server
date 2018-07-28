package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.CommandParameter;
import com.saviorru.comsserver.domain.dispatcher.PlayerService;
import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.model.Player;

import java.time.LocalDate;

public class AddPlayerCommand implements Command {

    private PlayerService playerService;
    private Player player;

    public AddPlayerCommand(Player player) {
        this.player = player;
    }

    @Override
    public Boolean execute(){
        playerService.addPlayer(player);
        return true;
    }

}
