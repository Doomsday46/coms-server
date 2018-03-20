package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.PrintTree;
import com.saviorru.comsserver.domain.Tournament;

public class ShowGridCommand extends Command {

    public ShowGridCommand(Tournament tournament) {
        super(tournament);
    }

    @Override
    public Boolean execute() {
        try {
            System.out.print(new PrintTree().printTree(tournament.getPlayerGrid()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "show grid";
    }

    @Override
    public String commandFormat() {
        return "command";
    }
}
