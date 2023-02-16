package com.graphics;

/**
 * IThread is an interface used to extend Runnable and supports the start and stop operations.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface IThread extends Runnable {
    /**
     * Indicating the terminator of the thread.
     */
    void stop();

    /**
     * Indicating the initiator of the thread.
     */
    void start();
}
