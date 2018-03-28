package com.saviorru.comsserver.domain.model;


public class PrizePlaceThePlayer implements PrizePlace {

    private int prizePlace;
    private Player player;

    public PrizePlaceThePlayer(Player player,int prizePlace){
        if(player == null) throw new NullPointerException("To create a prize is a player ");
        if(prizePlace < 0) throw new IllegalArgumentException("Not a correct prize number");
        this.player = player;
        this.prizePlace = prizePlace;
    }

    @Override
    public int getPrizePlace() {
        return prizePlace;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
