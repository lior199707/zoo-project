package com.mobility;

/**
 * Ilocatable interface describes the functionality of a location.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public interface Ilocatable {
    /**
     * @return the location of the object as a point (x,y)
     */
    public Point getLocation();

    /**
     * sets the location of the object to the new location which is point
     * @param point the new locating
     * @return true if the location of the object has changed to the new location, false otherwise
     */
    public boolean setLocation(Point point);
}
