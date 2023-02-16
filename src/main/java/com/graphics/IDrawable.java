package com.graphics;

import java.awt.*;

/**
 * interface IDrawable, drawable object has an image and can be drawn on the screen.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface IDrawable {
    /**
     * The main path for the folder containing the images of the project.
     */
    public final static String PICTURE_PATH = "src/main/resources/assignment2_pictures/";

    /**
     * loads the image of the matching animal by the short path given.
     * @param shortPathName  - String representation of the short path name for loading animals images.
     *                       i.e. Lion - lio, Bear - bea, Turtle - trt, Elephant - elf, Giraffe - grf.
     */
    public void loadImages(String shortPathName);

    /**
     * Draws the image of the implementing class on the screen.
     * @param g - graphics object to protect.
     */
    public void drawObject (Graphics g);

    /**
     * @return String representation of the implementing class color.
     */
    public String getColor();
}
