package com.grglucastr.players.handlers;

import com.google.gson.Gson;
import com.grglucastr.players.entities.Expert;
import com.grglucastr.players.entities.Player;
import com.grglucastr.players.producer.NoviceProducer;
import com.grglucastr.players.services.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerHandlerImpl implements PlayerHandler {

    @Autowired
    private ExpertService expertService;

    @Autowired
    private NoviceProducer noviceProducer;

    @Override
    public String process(Player player) {

        switch (player.getType()){
            case EXPERT:
                expertService.save((Expert) player);
                break;

            case NOVICE:
                Gson gson = new Gson();
                String json = gson.toJson(player);
                noviceProducer.postContentInTopic(json);
                break;
        }

        return player.processFeedbackMessage();
    }
}