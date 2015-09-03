package com.epam.pdp.patterns.creational.abstractfactory;

import com.epam.pdp.patterns.creational.abstractfactory.products.Button;
import com.epam.pdp.patterns.creational.abstractfactory.products.Window;

public class Client {
    public static void main(String[] args) {
        UiFactory factory = new KdeUiFactory();

        Button button = factory.createButton();
        Window window = factory.createWindow();
    }

}
