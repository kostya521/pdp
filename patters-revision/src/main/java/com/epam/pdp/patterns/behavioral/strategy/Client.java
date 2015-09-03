package com.epam.pdp.patterns.behavioral.strategy;

public class Client {
    public static void main(String[] args) {
        Unit unit = new Fighter();
        unit.setFightStrategy(new AxeStragegy());
        unit.fight();
        unit.setFightStrategy(new SwordStrategy());
        unit.fight();
    }

}
