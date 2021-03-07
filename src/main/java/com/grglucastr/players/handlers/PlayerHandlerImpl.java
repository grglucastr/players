package com.grglucastr.players.handlers;

import com.google.gson.Gson;
import com.grglucastr.players.entities.Expert;
import com.grglucastr.players.entities.Player;
import com.grglucastr.players.producer.NoviceProducer;
import com.grglucastr.players.services.ExpertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerHandlerImpl implements PlayerHandler {

    @Autowired
    private ExpertService expertService;

    @Autowired
    private NoviceProducer noviceProducer;

    @Override
    public String process(Player player) {

        switch (player.getType()){
            case EXPERT:
                log.info("Saving expert player to database: " + player.getName());
                expertService.save((Expert) player);
                break;

            case NOVICE:
                Gson gson = new Gson();
                String json = gson.toJson(player);
                log.info("Posting novice player: "+ player.getName() + " to topic: novice-players");
                noviceProducer.postContentInTopic(json);
                break;

            default:
                log.info("Invalid player: "+ player.getName() + ". Nothing will be processed");
                break;
        }

        return player.processFeedbackMessage();
    }
}