package com.grglucastr.players.entities;

import com.grglucastr.players.enums.PlayerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "expert")
public class Expert extends Player {

    public Expert() {
        super(PlayerType.EXPERT);
    }

    @Override
    public String processFeedbackMessage() {
        return "Player " + getName() + " stored in DB.";
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return super.getId();
    }

    @Override
    @Column
    public String getName() {
        return super.getName();
    }
}