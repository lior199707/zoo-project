package com.observer;

import com.graphics.ZooPanel;

/**
 * Controller is an Observer which controls the zoo panel.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Controller implements Observer {
    /**
     * notify method.
     * @param pan ZooPanel object.
     */
    @Override
    public void notify(ZooPanel pan) {
        pan.manageZoo();
    }
}
