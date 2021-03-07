package com.grglucastr.players.services;

import com.grglucastr.players.dto.PlayerRequest;
import com.grglucastr.players.entities.Expert;
import com.grglucastr.players.entities.Invalid;
import com.grglucastr.players.entities.Novice;
import com.grglucastr.players.entities.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    public List<Player> convertPlayerRequest(List<PlayerRequest> players){
        return players.stream()
                .map(PlayerService::convertPlayerRequest)
                .collect(Collectors.toList());
    }

    public static Player convertPlayerRequest(PlayerRequest playerRequest){

        Player player = null;

        if(playerRequest.getType().equalsIgnoreCase("expert")){
            player = new Expert();
        }else if(playerRequest.getType().equalsIgnoreCase("novice")){
            player = new Novice();
        }else{
            player = new Invalid();
        }

        player.setName(playerRequest.getName());
        return player;
    }
}