package com.grglucastr.players.entities;

import com.grglucastr.players.enums.PlayerType;

public class Novice extends Player {

    public Novice() {
        super(PlayerType.NOVICE);
    }

    @Override
    public String processFeedbackMessage() {
        return "Player " + getName() + " sent to Kafka topic.";
    }
}