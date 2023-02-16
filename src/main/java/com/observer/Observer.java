package com.observer;

import com.graphics.ZooPanel;

/**
 * Observer interface implemented by classes who wish to observe other objects.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface Observer {
    /**
     * notify method.
     * @param pan ZooPanel object.
     */
    public void notify(ZooPanel pan);
}
