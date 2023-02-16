package com.graphics;

/**
 * IAnimalBehavior is an interface implemented by Animals who wish to have GUI attributes.
 *
 * @author Sagie Baram
 * @author Lior Shilon
 */
public interface IAnimalBehavior {
    /**
     * @return String representation of the animal name, i.e. Lion - Lion etc.
     */
    public String getAnimalName();

    /**
     * size getter
     * @return int representation of the animal size.
     */
    public int getSize();

    /**
     * eatInc increments the total eat count of each animal implementing it.
     */
    public void eatInc();

    /**
     * eat count getter.
     * @return int representation of the animal's eat count.
     */
    public int getEatCount();

    /**
     * changes getter.
     * @return boolean representation of the animal change state.
     */
    public boolean getChanges();

    /**
     * changes setter.
     * @param state boolean value to set the animal state.
     */
    public void setChanges (boolean state);

    /**
     * Suspend state setter.
     * animal enters a suspended state - waiting in ready queue.
     */
    public void setSuspended();

    /**
     * Resumed state setter.
     * animal enters resumed state - done waiting in ready queue.
     */
    public void setResumed();
}
