package com.saviorru.comsserver.domain.dispatcher;

import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.exceptions.FoundObjectException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private Map<Player, Integer> playersNumbersMap;

    public PlayerService()
    {
        this.playersNumbersMap = new HashMap<>();
    }

    public void addPlayer(Player player)
    {
        if (player == null) throw new NullPointerException();
        if (playersNumbersMap.containsKey(player)) throw new FoundObjectException("Player already exist in dispatcher");
        for (int i =0; i  < playersNumbersMap.entrySet().size()+1; i++)
        {
            if (!(playersNumbersMap.values().contains(i+1)))
            {playersNumbersMap.put(player, i+1);
                break;}
        }
    }
    public void addPlayers(List<Player> playersList)
    {
        if (playersList == null) throw new NullPointerException();
        for (Player player: playersList)
        {
            this.addPlayer(player);
        }

    }
    public List<Integer> getAllPlayersNumbers()
    {
        return new ArrayList<Integer>(this.playersNumbersMap.values());
    }

    public Integer getPlayerNumber(Player player)
    {
        if (player == null) throw new NullPointerException();
        if (!(playersNumbersMap.containsKey(player))) throw new FoundObjectException("Can't find specified player");
        return playersNumbersMap.get(player);
    }
    public List<Player> getAllPlayers()
    {
        return new ArrayList<>(this.playersNumbersMap.keySet());
    }
    public Player getPlayerByNumber(Integer playerNumber)
    {
        if (playerNumber == null ) throw new NullPointerException();
        if (!(playersNumbersMap.containsValue(playerNumber))) throw new FoundObjectException("Can't find specified number");
        for (Player player: this.playersNumbersMap.keySet())
        {
            if (this.playersNumbersMap.get(player) == playerNumber)
                return player;
        }
        return null;
    }

}
