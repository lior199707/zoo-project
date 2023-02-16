package com.privateutil;

import com.animals.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import static com.graphics.IDrawable.PICTURE_PATH;

/**
 * PrivateGraphicUtils class holds utility methods used within the UI parts of the project.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class PrivateGraphicUtils {

    /**
     * @param FRAME_X integer value of the frame width in pixels.
     * @param FRAME_Y integer value of the frame height in pixels.
     * @return Point reference for creating a UI, so it will be in the middle of the screen.
     */
    public static Point centerWindow(int FRAME_X, int FRAME_Y){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point((screenSize.width / 2) - (FRAME_X / 2), (screenSize.height / 2) - (FRAME_Y / 2));
    }

    /**
     * @param type String representing of the animal's type.
     * @param color String representing the animal's color.
     * @param direction int representing animal's walking direction.
     *                  1 - right
     *                  -1 - left
     * @return String value of the path for loading the corresponding animal's picture based on animal's type,
     * shortPath, color and direction.
     */
    public static String findAnimalImagePath(String type, String color, int direction){
        StringBuilder path = new StringBuilder();
        path.append(PICTURE_PATH);
        path.append(type.toLowerCase());
        path.append("_images/");
        switch (type) {
            case "Lion" -> path.append("lio");
            case "Bear" -> path.append("bea");
            case "Giraffe" -> path.append("grf");
            case "Turtle" -> path.append("trt");
            case "Elephant" -> path.append("elf");
        }

        switch (color.toUpperCase()) {
            case "NATURAL" -> path.append("_n_");
            case "RED" -> path.append("_r_");
            case "BLUE" ->  path.append("_b_");
        }

        if (direction == 1){
            path.append("1.png");
        } else {
            path.append("2.png");
        }

        return String.valueOf(path);
    }

    /**
     * @param type String representing of the food's type.
     * @return String value of the path for loading the corresponding food's picture based on the food's type.
     */
    public static String findFoodImagePath(String type){
        StringBuilder path = new StringBuilder();
        String lowerCaseType = type.toLowerCase();
        path.append(PICTURE_PATH);
        path.append("food_images/");
        path.append(lowerCaseType);
        if (lowerCaseType.equals("meat")){
            path.append(".gif");
        } else {
            path.append(".png");
        }

        return String.valueOf(path);
    }

    /**
     * loads jpg and png images, right now used only for savanna background image.
     * @param format The format of the image(jpg, png).
     * @return String value, the path of the image to load.
     */
    public static String findBackgroundImagePath(String format){
        StringBuilder path = new StringBuilder();
        path.append(PICTURE_PATH);
        path.append("background_images/");
        path.append("savanna");
        if (format.equals("png")){
            path.append(".png");
        } else {
            path.append(".jpg");
        }
        return String.valueOf(path);
    }

    /**
     * resizes an image by its path, wanted width and wanted height.
     * used in order to present the image in the AddAnimalDialog.
     *
     * @param path the path of the image to resize
     * @param width the new width of the image
     * @param height the new height of the image
     * @return ImageIcon reference, SCALE_SMOOTH image with new width and height, if image failed to load return null.
     */
    // change to path name.
    public static ImageIcon resizeImage(String path, int width, int height){
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            ImageIcon image = new ImageIcon(bufferedImage);
            Image resizedImage = image.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            System.err.println(path + " Not found!");
        }
        return null;
    }

    /**
     * resizes animal's image to constant width(220) and height(180).
     * used in order to present the image in the MoveAnimalDialog.
     *
     * @param animal the animal to resize its image.
     * @return ImageIcon reference, SCALE_SMOOTH image with new width(200) and height(180).
     */
    public static ImageIcon setAnimalImageIcon(Animal animal){
        BufferedImage bufferedImage = animal.getImg1();
        ImageIcon image = new ImageIcon(bufferedImage);
        Image resizedImage = image.getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * creates a new border with the wanted title, title position and title justification.
     * @param title String, the title of the border.
     * @param titlePosition Integer, the position of the title.
     * @param titleJustification Integer, title justification of the border.
     * @return TitleBorder reference of the wanted border.
     */
    public static TitledBorder createTitledBorder(String title, int titlePosition, int titleJustification){
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitlePosition(titlePosition);
        border.setTitleJustification(titleJustification);
        return border;
    }

    /**
     * class ErrorDialogException extends class Exception.
     * handles invalid input in Dialogs (AddAnimalDialog).
     */
    public static class ErrorDialogException extends Exception {
        /**
         * Pops up an Error Dialog window with the wanted container and message.
         * @param container the container of the error dialog window.
         * @param message the message to be presented in the error dialog window.
         */
        public ErrorDialogException(Container container, String message){
            JOptionPane.showMessageDialog(container, message,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Pops up an Information Dialog window with the wanted container and message.
     * @param container the container of the information dialog window.
     * @param message the message to be presented in the information dialog window.
     */
    public static void popInformationDialog(Container container, String message){
        JOptionPane.showMessageDialog(container, message,"Information",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * gets a Buffered image and creates and returns a deep of clone of it.
     * @param bi BufferedImage object to copy.
     * @return copied BufferedImage object.
     */
    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
