package com.epam.pdp.patterns.behavioral.strategy;

/*
* Context
* */
public class Fighter implements Unit {
    private FightStrategy fightStrategy;

    public void setFightStrategy(FightStrategy fightStrategy) {
        this.fightStrategy = fightStrategy;
    }

    public void fight() {
        fightStrategy.fight();
    }

}
