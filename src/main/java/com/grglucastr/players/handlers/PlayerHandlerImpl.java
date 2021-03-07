package com.grglucastr.players.handlers;

import com.grglucastr.players.entities.Expert;
import com.grglucastr.players.entities.Player;
import com.grglucastr.players.services.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerHandlerImpl implements PlayerHandler {

    @Autowired
    private ExpertService expertService;

    @Override
    public String process(Player player) {

        switch (player.getType()){
            case EXPERT:
                expertService.save((Expert) player);
                return player.processFeedbackMessage();

            case NOVICE:
            default:
                return player.processFeedbackMessage();
        }
    }
}