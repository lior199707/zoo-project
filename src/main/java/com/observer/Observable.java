package com.observer;

/**
 * Observable interface.
 * allows implementors to set an Observer object.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface Observable {
    /**
     * setObserver set observer to observable object.
     * @param o the observer object to set.
     */
    public void setObserver(Observer o);
}
