package com.grglucastr.players.entities;

import com.grglucastr.players.enums.PlayerType;

public class Invalid extends Player {

    public Invalid() {
        super(PlayerType.INVALID);
    }

    @Override
    public String processFeedbackMessage() {
        return "Player " + getName() + " did not fit.";
    }
}