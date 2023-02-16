package com.mobility;

/**
 * Mobile is an abstract class implementing Ilocatable.
 * this class defines movement in a two-dimensional space.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public abstract class Mobile implements Ilocatable {
    /**
     * Point object indicating Mobile object's location.
     */
    private Point location;
    /**
     * double value representing the total distance a mobile object has made.
     */
    private double totalDistance;

    /**
     * Mobile constructor.
     * @param location Point object (x,y) representing the location on the two-dimensional space
     */
    public Mobile(Point location) {
        setLocation(location);
        totalDistance = 0;
    }

    /**
     * Mobile copy constructor.
     * @param other Mobile object to copy.
     */
    public Mobile(Mobile other){
        this.location = new Point(other.getLocation()) ;
        this.totalDistance = other.getTotalDistance();
    }

    /**
     * totalDistance getter.
     *
     * @return totalDistance - double value of the total distance made by the animal.
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * implementation of Ilocatable interface setLocation.
     * @see com.mobility.Ilocatable
     *
     * location getter.
     * @return location - Point object representing the location of the object
     */
    @Override
    public Point getLocation() {return location;
    }

    /**
     * implementation of Ilocatable interface setLocation.
     * @see com.mobility.Ilocatable
     *
     * @param point - Point object to set the location to
     * @return boolean value if the set was successful or not.
     */
    @Override
    public boolean setLocation(Point point) {
        boolean isSuccess = Point.checkBoundaries(point);
        if (isSuccess) {
            this.location = new Point(point);
        } else {
            this.location = new Point();
        }
        return isSuccess;
    }

    /**
     * adding double value of parameter distance to total distance.
     *
     * @param distance double value to add.
     */
    public void addTotalDistance(double distance) {
        totalDistance += distance;
    }

    /**
     * calculating the distance between the current location to the next location
     * using Pythagoras.
     *
     * @param nextLocation Point object to measure its distance with.
     * @return the distance between two point objects.
     */
    public double calcDistance(Point nextLocation) {
        return Math.sqrt(Math.pow((location.getX() - nextLocation.getX()), 2)
                + Math.pow((location.getY() - nextLocation.getY()), 2));
    }

    /**
     *
     * If next location is valid updating the location of this object to the next location and adds the
     * distance between the previous location to the next location to the total distance the animal has traveled
     * else, return 0, indicating the animal didn't travel.
     *
     * @see com.mobility.Mobile setLocation for reference.
     * @param nextLocation Point object to change location to.
     * @return distance made between points or 0 if no distance was made.
     *
     */
    public double move(Point nextLocation) {
        //distance - the distance between the previous location to the next location
        double distance = calcDistance(nextLocation);
        if (setLocation(nextLocation)) {
            addTotalDistance(distance);
            return distance;
        }
        // if no distance was traveled or point values are out of bounds.
        return 0;
    }

    /**
     * checks if this object and the other object 'o' are identical.
     *
     * @param o the second object to check equality with.
     * @return true, if the both objects point to the same address in memory or the 2 objects have identical attributes
     * values, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mobile mobile = (Mobile) o;

        if (Double.compare(mobile.getTotalDistance(), getTotalDistance()) != 0) return false;
        return getLocation() != null ? getLocation().equals(mobile.getLocation()) : mobile.getLocation() == null;
    }
}
