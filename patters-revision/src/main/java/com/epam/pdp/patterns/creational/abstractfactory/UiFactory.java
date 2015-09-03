package com.epam.pdp.patterns.creational.abstractfactory;

import com.epam.pdp.patterns.creational.abstractfactory.products.Button;
import com.epam.pdp.patterns.creational.abstractfactory.products.Window;

public interface UiFactory {
    Button createButton();

    Window createWindow();
}
