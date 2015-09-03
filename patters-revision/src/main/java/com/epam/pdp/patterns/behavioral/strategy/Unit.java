package com.epam.pdp.patterns.behavioral.strategy;

public interface Unit {
    void setFightStrategy(FightStrategy fightStrategy);

    void fight();
}
