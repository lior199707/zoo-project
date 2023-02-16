package com.food;

import com.graphics.IDrawable;
import com.graphics.ZooPanel;
import com.mobility.Ilocatable;
import com.mobility.Point;
import com.privateutil.PrivateGraphicUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * abstract class Food, handles foods of types meat and plants, food is edible has a location and drawable.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public abstract class Food implements IEdible, Ilocatable, IDrawable {

    /**
     *double value representing the height of the food.
     */
    private double height;
    /**
     *Point object representing the location of the food.
     */
    private Point location;
    /**
     *double value representing the weight of the food.
     */
    private double weight;
    /**
     * ZooPanel reference to the main panel the food is drawn upon.
     */
    private ZooPanel pan;
    /**
     * image reference, the image of the food.
     */
    private BufferedImage img;

    /**
     * Food constructor
     * sets the height and weight of the food to random values, and sets food's location to the middle of the
     * ZooPanel.
     * sets food's image to the matching food type image.
     */
    public Food() {
        Random rand = new Random();
        this.height = rand.nextInt(30);
        this.weight = rand.nextInt(12);
        this.location = new Point(Point.getMaxX()/2, Point.getMaxY()/2);
        loadImages(foodShortPathName());
    }

    /**
     * Food copy constructor.
     * @param other Food object to copy.
     */
    public Food(Food other){
        this.location = other.getLocation();
        this.height = other.getHeight();
        this.weight = other.getWeight();
        this.pan = other.pan;
        this.img = PrivateGraphicUtils.deepCopy(other.img);
    }

    /**
     * @return double value representing plant weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return double value representing plant height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @param pan , the panel main panel the food is drawn upon.
     */
    public void setPan(ZooPanel pan) {
        this.pan = pan;
    }

    /**
     * @param height double value representing plant height.
     * @return boolean value indicating if set was successful or not.
     */
    public boolean setHeight(double height) {

        boolean isSuccess = (height >= 0);
        if (isSuccess) {
            this.height = height;
        } else {
            this.height = 0;
        }
        return isSuccess;
    }

    /**
     * @param weight double value representing plant weight.
     * @return boolean value indicating if the set was successful or not.
     */
    public boolean setWeight(double weight) {
        boolean isSuccess = (weight >= 0);
        if (isSuccess) {
            this.weight = weight;
        } else {
            this.weight = 0;
        }
        return isSuccess;
    }

    /**
     * @return String representation of the short path name for loading food image.
     * will be overridden by classes that extend from abstract class Food.
     */
    public abstract String foodShortPathName();


    /**
     * toString method of Food class.
     * @return String representation of the food object.
     */
    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }

    /**
     * @see com.mobility.Ilocatable getLocation()
     *
     * @return Point reference of the food's location, always:(400,300).
     */
    @Override
    public Point getLocation() {
        return this.location;
    }

    /**
     * checks if the point given is a valid point(boundaries (0-800,0-600)), if valid sets food location to the
     * new location and returns true, otherwise returns false.
     * @see com.mobility.Ilocatable setLocation(Point).
     *
     * @param newLocation Point reference for the new location of the food.
     * @return Boolean value representing success or failure.
     */
    @Override
    public boolean setLocation(Point newLocation) {
        boolean isSuccess = Point.checkBoundaries(newLocation);
        if (isSuccess) {
            this.location = newLocation;
        }
        return isSuccess;
    }

    /**
     * @return null, food doesn't have color.
     */
    @Override
    public String getColor() {
        return null;
    }

    /**
     * Draws the image of the food in the middle of the ZooPanel.
     * @param g the graphics object to protect
     */
    @Override
    public void drawObject(Graphics g) {
        // need to draw the object at the center of the screen.
        g.drawImage(img, location.getX() + 80, location.getY() + 60, 50,50,pan);
    }

    /**
     * loads the image of the food by its short path name.
     * @param shortPathName, String representation of the short path name for loading corresponding food image.
     */
    @Override
    public void loadImages(String shortPathName) {
        String path = PrivateGraphicUtils.findFoodImagePath(shortPathName);
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            JOptionPane.showOptionDialog(pan, "Food image failed to load", "ERROR",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                    null, null, null);
        }
    }
}
