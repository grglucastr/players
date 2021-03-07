package com.grglucastr.players.entities;


import com.grglucastr.players.enums.PlayerType;

public abstract class Player {

    protected Integer id;
    protected String name;
    protected PlayerType playerType;

    public Player(PlayerType playerType){
        this.playerType = playerType;
    }


    public abstract void process();


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

    public PlayerType getPlayerType() {
        return playerType;
    }
}