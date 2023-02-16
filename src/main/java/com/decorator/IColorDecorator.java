package com.decorator;

/**
 * IColorDecorator interface allows implementors to decorate an object
 * by color during runtime.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface IColorDecorator {
    /**
     * Color setter.
     * @param color String representation of a color to decorate the object with.
     */
    public void setColor(String color);
}
