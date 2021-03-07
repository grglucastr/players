package com.grglucastr.players.entities;

import com.grglucastr.players.enums.PlayerType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expert")
public class Expert extends Player {

    public Expert() {
        super(PlayerType.EXPERT);
    }

    @Override
    public void process() {

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