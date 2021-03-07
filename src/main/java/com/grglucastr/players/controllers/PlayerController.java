package com.grglucastr.players.controllers;

import com.grglucastr.players.dto.PlayerRequest;
import com.grglucastr.players.dto.PlayerResponse;
import com.grglucastr.players.entities.Player;
import com.grglucastr.players.handlers.PlayerHandler;
import com.grglucastr.players.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerHandler playerHandler;

    @PostMapping("/players")
    public PlayerResponse players(@RequestBody List<PlayerRequest> requestPlayers){

        List<Player> players = playerService.convertPlayerRequest(requestPlayers);
        List<String> messages = new ArrayList<>();

        for(Player player: players){
            messages.add(playerHandler.process(player));
        }

        return new PlayerResponse(messages);
    }
}