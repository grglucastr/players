package com.grglucastr.players;

import com.grglucastr.players.entities.Expert;
import com.grglucastr.players.entities.Invalid;
import com.grglucastr.players.entities.Novice;
import com.grglucastr.players.entities.Player;
import com.grglucastr.players.enums.PlayerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PlayerTests {

    private Player player;

    @Test
    public void testReturnMessageForExpertPlayer(){
        player = new Expert();
        player.setName("Sub Zero");

        String feedbackMessage = player.processFeedbackMessage();
        String expectedMessage = "Player Sub Zero stored in DB.";

        Assertions.assertEquals(expectedMessage, feedbackMessage);
        Assertions.assertEquals(PlayerType.EXPERT, player.getType());
    }

    @Test
    public void testReturnMessageForNovicePlayer(){
        player = new Novice();
        player.setName("Scorpion");

        String feedbackMessage = player.processFeedbackMessage();
        String expectedMessage = "Player Scorpion sent to Kafka topic.";

        Assertions.assertEquals(expectedMessage, feedbackMessage);
        Assertions.assertEquals(PlayerType.NOVICE, player.getType());
    }

    @Test
    public void testReturnMessageForInvalidPlayer(){
        player = new Invalid();
        player.setName("Reptile");

        String feedbackMessage = player.processFeedbackMessage();
        String expectedMessage = "Player Reptile did not fit.";

        Assertions.assertEquals(expectedMessage, feedbackMessage);
        Assertions.assertEquals(PlayerType.INVALID, player.getType());
    }

}
