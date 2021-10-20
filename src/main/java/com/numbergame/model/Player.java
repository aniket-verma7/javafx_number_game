package com.numbergame.model;

public class Player
{
    private String name;
    private boolean chance;

    public Player(String name, boolean chance) {
        this.name = name;
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChance() {
        return chance;
    }

    public void setChance(boolean chance) {
        this.chance = chance;
    }
}
