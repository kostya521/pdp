package com.epam.pdp.patterns.creational.abstractfactory;

import com.epam.pdp.patterns.creational.abstractfactory.products.Button;
import com.epam.pdp.patterns.creational.abstractfactory.products.Window;
import com.epam.pdp.patterns.creational.abstractfactory.products.XwindowButton;
import com.epam.pdp.patterns.creational.abstractfactory.products.XwindowWindow;

public class XwindowUiFactory implements UiFactory {
    public Button createButton() {
        return new XwindowButton();
    }

    public Window createWindow() {
        return new XwindowWindow();
    }

}
