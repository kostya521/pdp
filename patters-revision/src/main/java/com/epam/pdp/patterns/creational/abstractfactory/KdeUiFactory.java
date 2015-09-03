package com.epam.pdp.patterns.creational.abstractfactory;

import com.epam.pdp.patterns.creational.abstractfactory.products.Button;
import com.epam.pdp.patterns.creational.abstractfactory.products.KdeButton;
import com.epam.pdp.patterns.creational.abstractfactory.products.KdeWindow;
import com.epam.pdp.patterns.creational.abstractfactory.products.Window;

public class KdeUiFactory implements UiFactory {
    public Button createButton() {
        return new KdeButton();
    }

    public Window createWindow() {
        return new KdeWindow();
    }

}
