package com.grglucastr.players.entities;


import com.grglucastr.players.enums.PlayerType;

public abstract class Player {

    protected Integer id;
    protected String name;
    protected PlayerType type;

    public abstract String processFeedbackMessage();

    public Player(PlayerType type){
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }
}