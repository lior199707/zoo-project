package com.animals;

import com.diet.IDiet;
import com.food.EFoodType;
import com.food.Food;
import com.food.IEdible;
import com.graphics.IAnimalBehavior;
import com.graphics.IDrawable;
import com.graphics.IThread;
import com.graphics.ZooPanel;
import com.mobility.Mobile;
import com.mobility.Point;
import com.observer.Observable;
import com.observer.Observer;
import com.privateutil.PrivateGraphicUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Animal class is an abstract class representing a mobile animal.
 * Every animal type is also edible, drawable and has a set of behaviors.
 * Every Animal object is a thread.
 * @see com.mobility.Mobile
 * @see com.food.IEdible
 * @see com.graphics.IDrawable
 * @see com.graphics.IAnimalBehavior
 * @see com.graphics.IThread
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public abstract class Animal extends Mobile implements IEdible, IDrawable, IAnimalBehavior, IThread, Observable, Cloneable {
    /**
     * String value of the animal name.
     */
    private String name;
    /**
     * double value of the animal weight.
     */
    private double weight;
    /**
     * IDiet reference indicating the diet type of animal.
     */
    private IDiet diet;
    /**
     * Static attribute,the default color of an animal: "NATURAL".
     */
    private static final String DEFAULT_COLOR = "NATURAL";
    /**
     * Static attribute, indicating the maximum distance in x and y coords between animal and food so
     * the animal will be able to eat the food.
     */
    private static final int EAT_DISTANCE = 10;
    /**
     * Static attribute, indicating movement in the direction of the positive X-axis (right).
     */
    public static final int RIGHT_DIRECTION = 1;
    /**
     * Static attribute, indicating movement in the direction of the negative X-axis (left).
     */
    public static final int LEFT_DIRECTION = -1;
    /**
     * Static attribute, indicating movement in the direction of the positive Y-axis (down).
     */
    public static final int DOWN_DIRECTION = 1;
    /**
     * Static attribute, indicating movement in the direction of the negative Y-axis (up).
     */
    public static final int UP_DIRECTION = -1;
    /**
     * Boolean indicates the animal has moved to another location.
     */
    private boolean coordChanged;
    /**
     * Int the size of the animal, valid range: 50-300, the size affects eating and image size on the screen.
     */
    private int size;
    /**
     * Int the horizontal speed of an animal, valid range: 1-10.
     */
    private int horSpeed;
    /**
     * Int the vertical speed of an animal, valid range: 1-10.
     */
    private int verSpeed;
    /**
     * String value of the animal color.(Red, Blue, Natural).
     */
    private String col;
    /**
     * Int indicating movement direction on the X-axis.
     * 1 indicates movement to the positive direction of the X-axis.
     * -1 indicates movement to the negative direction of the X-axis.
     */
    private int x_dir = RIGHT_DIRECTION;
    /**
     * Int indicating movement direction on the Y-axis.
     * 1 indicates movement to the positive direction of the Y-axis.
     * -1 indicates movement to the negative direction of the Y-axis.
     */
    private int y_dir = DOWN_DIRECTION;
    /**
     * Int value of the number of Food the animal ate.
     */
    private int eatCount;
    /**
     * ZooPanel reference to the main panel the animals are drawn upon.
     */
    private ZooPanel pan;
    /**
     * BufferedImage reference to the 2 possible images of an animal.
     * img1 - movement to the right.
     * img2 - movement to the left.
     */
    private BufferedImage img1, img2;

    /**
     * boolean representation of the thread suspended state.
     */
    private volatile boolean threadSuspended;

    /**
     * an Observer object used to notify events.
     */
    private Observer controller;

    /**
     * Atomic boolean flag which indicates if the thread is alive or not.
     */
    private AtomicBoolean threadAlive = new AtomicBoolean(true);



    /**
     * Animal constructor
     *
     * @param name String value of the animal's name, should contain only letters.
     * @param location Point (x,y) indicating the location of the animal.
     *                  X coordinate valid range: 0-800.
     *                  Y coordinate valid range: 0-600.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     */
    public Animal(String name, Point location, int size, int horSpeed, int verSpeed, String col){
        super(location);
        setName(name);
        setSize(size);
        setHorSpeed(horSpeed);
        setVerSpeed(verSpeed);
        setColor(col);
        this.eatCount = 0;
        this.coordChanged = false;
        this.threadSuspended = false;
    }

    /**
     * Animal Ctor, creates an animal without providing the color, sets color to Default Color.
     * used for instantiating animals in main.
     *
     * @param name String value of the animal's name, should contain only letters.
     * @param location Point (x,y) indicating the location of the animal.
     *                  X coordinate valid range: 0-800.
     *                  Y coordinate valid range: 0-600.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     */
    public Animal(String name, Point location, int size, int horSpeed, int verSpeed) {
        this(name,location,size, horSpeed,verSpeed,DEFAULT_COLOR);
    }

    /**
     * Animal copy constructor.
     * @param other Animal object to copy.
     */
    public Animal(Animal other){
        super(other);
        this.name = other.getName();
        this.weight = other.getWeight();
        this.coordChanged = other.getChanges();
        this.size = other.getSize();
        this.horSpeed = other.getHorSpeed();
        this.verSpeed = other.getVerSpeed();
        this.col = other.getColor();
        this.x_dir = other.getXDirection();
        this.y_dir = other.getYDirection();
        this.eatCount = other.getEatCount();
        this.pan = other.getPan();
        this.diet = other.getDiet();
        this.img1 = PrivateGraphicUtils.deepCopy(other.getImg1());
        this.img2 = PrivateGraphicUtils.deepCopy(other.getImg2());
        this.threadSuspended = other.isSuspended();
        this.controller = other.getController();
        this.threadAlive = new AtomicBoolean(other.getIsAlive());
    }

    /**
     * name getter.
     * @return String value representing animal name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * weight getter.
     * @return double value representing animal weight.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * diet getter.
     * @return IDiet reference.
     */
    public IDiet getDiet() {
        return this.diet;
    }
    /**
     * movement direction on X-axis getter.
     * @return 1 indicates movement to the positive direction of the X-axis.
     *         -1 indicates movement to the negative directing of the X-axis.
     */
    public int getXDirection() {
        return this.x_dir;
    }

    /**
     * movement direction on Y-axis getter.
     * @return 1 indicates movement to the positive direction of the Y-axis.
     *         -1 indicates movement to the negative directing of the Y-axis.
     */
    public int getYDirection() {
        return this.y_dir;
    }

    /**
     * vertical speed getter.
     * @return int value representing animal vertical speed.
     */
    public int getVerSpeed() {
        return this.verSpeed;
    }
    /**
     * horizontal speed getter.
     * @return int value representing animal horizontal speed.
     */
    public int getHorSpeed() {
        return this.horSpeed;
    }
    /**
     * image 2 getter.
     * @return Image of the animal facing to the left.
     */
    public BufferedImage getImg2() {
        return this.img2;
    }
    /**
     * image 1 getter.
     * @return Image of the animal facing to the right.
     */
    public BufferedImage getImg1() {
        return this.img1;
    }

    /**
     * ZooPanel getter.
     * @return ZooPanel reference of the main panel the animals are drawn upon.
     */
    public ZooPanel getPan(){
        return pan;
    }

    /**
     * Controller getter.
     * @return Observer object indicating the animal's controller.
     */
    public Observer getController(){
        return this.controller;
    }

    /**
     * Default color getter.
     * @return String representation of the default color for an animal("NATURAL").
     */
    public static String getDefaultColor() {
        return DEFAULT_COLOR;
    }

    /**
     * threadSuspended getter.
     * @return boolean value representing the current state of the thread, if suspended or not.
     */
    public boolean isSuspended() {
        return threadSuspended;
    }

    /**
     * threadAlive getter.
     * @return boolean value indicating if the object's thread is alive or not.
     */
    public boolean getIsAlive(){
        return this.threadAlive.get();
    }
    /**
     * threadSuspended setter.
     * @param state the new state of the thread.
     * @return true as the state will change upon call.
     */
    public boolean setThreadSuspended(boolean state) {
        threadSuspended = state;
        return true;
    }

    /**
     * name setter.
     * this method checks if given name param is alphabetic, if so it updates the name field.
     * otherwise, it removes all non-alphabetic characters.
     * if the String is not empty assigns the remaining characters to name, otherwise sets "NaN",
     * indicating the animal has no name.
     *
     * @param name the String object to set animal name.
     * @return boolean value if name was set or not (NaN).
     */
    public boolean setName(String name) {
        boolean isSuccess = name.chars().allMatch(Character::isAlphabetic);
        if (isSuccess){
            this.name = name;
        } else {
            System.out.println("-- Removing non-alphabetic characters from name --");
            this.name = name.replaceAll("[^A-Za-z]", "");
            System.out.println("New name: " + this.name);
            isSuccess = !Objects.equals(this.name, "");
            if (!isSuccess){
                this.name = "NaN";
            }
        }
        return isSuccess;
    }

    /**
     * size setter.
     * checks if the size given is between 50 and 300, if between boundaries sets the size of the animal
     * to the size given and returns true, otherwise returns false indicates failure.
     *
     * @param size Int value of the animal size.
     * @return true if succeeded, false otherwise.
     */
    public boolean setSize(int size){
        boolean isSuccess = false;
        int MIN_RANGE = 50,MAX_RANGE = 300;
        if (MIN_RANGE <= size && size <= MAX_RANGE){
            this.size = size;
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * vertical speed setter.
     * checks if the speed given is between 1 and 10, if between boundaries sets the vertical speed of the animal
     * to the speed given and returns true, otherwise sets the vertical speed to 1 and returns false indicates failure.
     *
     * @param verSpeed Int value representing the vertical speed of the animal, valid values: 1,-1.
     *                 1 indicates movement to the positive direction of the Y-axis.
     *                 -1 indicates movement to the negative direction of the Y-axis.
     * @return true is succeeded, false otherwise.
     */
    public boolean setVerSpeed(int verSpeed){
        boolean isSuccess = false;
        int MIN_RANGE = 1,MAX_RANGE = 10;
        if (MIN_RANGE <= verSpeed && verSpeed <= MAX_RANGE){
            this.verSpeed = verSpeed;
            isSuccess = true;
        } else this.verSpeed = 1;
        return isSuccess;
    }

    /**
     * horizontal speed setter.
     * checks if the speed given is between 1 and 10, if between boundaries sets the horizontal speed of the animal
     * to the speed given and returns true, otherwise sets the horizontal speed to 1 and returns false indicates failure.
     *
     * @param horSpeed Int value representing the  horizontal speed of the animal, valid values: 1,-1.
     *                 1 indicates movement to the positive direction of the X-axis.
     *                 -1 indicates movement to the negative direction of the X-axis.
     * @return true is succeeded, false otherwise.
     */
    public boolean setHorSpeed(int horSpeed){
        boolean isSuccess = false;
        int MIN_RANGE = 1,MAX_RANGE = 10;
        if (MIN_RANGE <= horSpeed && horSpeed <= MAX_RANGE){
            this.horSpeed = horSpeed;
            isSuccess = true;
        } else this.horSpeed = 1;
        return isSuccess;
    }


    /**
     * color setter.
     * checks if the color given is valid ,if valid sets the color of the animal
     * to the color given and returns true,
     * otherwise sets the color to NATURAL and returns false indicates failure.
     *
     * @param color String value of the animal's color, valid value ("RED", "BLUE", "NATURAL")
     * @return true is succeeded, false otherwise.
     */
    public boolean setColor(String color){
        boolean isSuccess = true;
        String upperCaseColor  = color.toUpperCase();
        switch (upperCaseColor) {
            case "RED" -> this.col = "RED";
            case "BLUE" -> this.col = "BLUE";
            case "NATURAL" -> this.col = "NATURAL";
            default -> {
                this.col = "NATURAL";
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * weight setter.
     * checks if weight >= 0, if so it updates the weight field.
     * otherwise, set the weight to 0.  weight cannot be negative!
     *
     * @param weight double value representing weight.
     * @return boolean value if weight was set or not.
     */
    public boolean setWeight(double weight) {
        boolean isSuccess = weight > 0;
        if (isSuccess) {
            this.weight = weight;
        }
        return isSuccess;
    }

    /**
     * diet setter.
     * @param diet the diet object to set.
     * @return always true.
     */
    public boolean setDiet(IDiet diet) {
        this.diet = diet;
        return true;
    }

    /**
     * ZooPanel setter.
     * @param pan the main panel the animals are drawn upon.
     */
    public void setPan(ZooPanel pan) {
        this.pan = pan;
    }

    /**
     * abstract method, animals will have to implement their own short path name.
     * @return String representation of the animal short path name. i.e. Lion - lio etc.
     */
    public abstract String animalShortPathName();


    /**
     * abstract method, animals will have to implement their own sounds.
     */
    public abstract void makeSound();

    /**
     * eat activates the appropriate eat method based on the current animal's diet.
     * if the current animal is able to eat the given animal it will activate makeSound
     * of the current animal and set the new weight.
     * @param food an edible type of animal to eat.
     * @return boolean value if this animal was able to eat the given animal or not.
     */
    public boolean eat(IEdible food) {
        boolean isSuccess = false;
        double updatedWeight = this.diet.eat(this, food);
        if (updatedWeight != 0) {
            makeSound();
            setWeight(this.weight + updatedWeight);
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * gets the food of the ZooPanel, this method is called after moving an animal.
     * checks if the animal can eat the food in the ZooPanel, first checks if there is food in the panel than checks
     * if the distance between the x,y cords of the animal and the x,y cords of food is at most 10.
     * if both conditions are true the animals eats the food,otherwise it doesn't and false is returned.
     *
     * @param food food of the ZooPanel (food: "Cabbage","Lettuce","Meat").
     * @return true if succeeded eating food, false otherwise.
     */
    public boolean conditionalFoodEating(Food food) {
        if (food == null) return false;

        int distanceX = Math.abs(this.getLocation().getX() - food.getLocation().getX());
        int distanceY = Math.abs(this.getLocation().getY() - food.getLocation().getY());

        if (distanceX <= EAT_DISTANCE && distanceY <= EAT_DISTANCE)
            if (this.eat(food)){
                pan.setPanelFood(null);
                pan.updateEatCount(this);
                pan.getInfoTable().updateTable();
                return true;
            }
        return false;
    }

    @Override
    public void setObserver(Observer o){
        controller = o;
    }

    /**
     * move uses Mobile.move() method to set the new location and calculate the distance between
     * the current and nextLocation.
     * It evaluates the new weight with the distance based on this formula:
     * current weight - (distance * current weight * CONST = 0.00025)
     *
     * @see com.mobility.Mobile move() for reference.
     * @param nextLocation Point object to change location to.
     * @return distance traveled between two points.
     */
    @Override
    public double move(Point nextLocation) {
        final double CONST = 0.00025;
        double distance = super.move(nextLocation);
        if (distance > 0) {
            double currentWeight = getWeight();
            this.setWeight(currentWeight - (distance * currentWeight * CONST));
            this.coordChanged = true;
        }
        return distance;
    }

    /**
     * location getter.
     * @see com.mobility.Mobile getLocation() for reference.
     * @return current location of the animal, based on Mobile.getLocation.
     */
    @Override
    public Point getLocation() {
        return super.getLocation();
    }

    /**
     * Override interface IEdible getFoodType().
     * The food type of all animals is MEAT except Lion.
     * class Lion overrides this method.
     *
     * @return food type MEAT.
     */
    @Override
    public EFoodType getFoodType() {
        return EFoodType.MEAT;
    }

    /**
     * Draws animal's picture on the main panel based on her size and location.
     *
     * @param g the graphics object to protect.
     */
    @Override
    public void drawObject(Graphics g) {
        int size = getSize();
        int coordinateX = getLocation().getX();
        int coordinateY = getLocation().getY();

        if (x_dir == RIGHT_DIRECTION) { // goes to the right side
            g.drawImage(getImg1(),coordinateX - (size/3),coordinateY - (size/8), (int) (size * 1.2),size, getPan());
        }
        else // goes to the left side
            g.drawImage(getImg2(), coordinateX, coordinateY - (size / 8), (int) (size * 1.2), size, getPan());
    }

    /**
     * Initializes two images based on the animal's type, color and short path name.
     * image1 will contain an image of the animal facing to the right.
     * image1 will contain an image of the animal facing to the left.
     *
     * @param shortPathName the image's short path name of the corresponding animal.
     */
    @Override
    public void loadImages(String shortPathName) {
        String path;
        path = PrivateGraphicUtils.findAnimalImagePath(this.getAnimalName(),col, RIGHT_DIRECTION);
        try {
            img1 = ImageIO.read(new File(path));
        } catch (IOException e) {
                JOptionPane.showOptionDialog(pan, "Img1 failed to load", "ERROR",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                        null, null, null);
        }
        path = PrivateGraphicUtils.findAnimalImagePath(this.getAnimalName(),col, LEFT_DIRECTION);
        try {
            img2 = ImageIO.read(new File(path));
        } catch (IOException e) {
                JOptionPane.showOptionDialog(pan, "Img2 failed to load", "ERROR",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                        null, null, null);
        }
    }

    /**
     * override interface IDrawable
     *
     * @return String representation of the animal's color
     */
    @Override
    public String getColor() {
        return this.col;
    }


    /**
     * override interface IAnimalBehavior.
     * Used for repaint of the ZooPanel after an animal has moved.
     *
     * @return Boolean value indicates if the animal has moved
     */
    @Override
    public boolean getChanges (){
        return this.coordChanged;
    }

    /**
     * override interface IAnimalBehavior.
     * changes setter, sets Boolean changes to the Boolean value of state.
     *
     * @param state Boolean value to set changes to.
     */
    @Override
    public void setChanges (boolean state){
        this.coordChanged = state;
    }

    /**
     * override interface IAnimalBehavior.
     * @see IAnimalBehavior
     */
    @Override
    public synchronized void setSuspended() {
        this.threadSuspended = true;
    }
    /**
     * override interface IAnimalBehavior.
     * @see IAnimalBehavior
     */
    @Override
    public synchronized void setResumed(){
        this.threadSuspended = false;
        notify();
    }

    /**
     * override interface IAnimalBehavior.
     * After animal ate increases it's eat counter by 1.
     */
    @Override
    public void eatInc() {
        this.eatCount++;
    }

    /**
     * override interface IAnimalBehavior.
     * @return Int value representing the animal size.
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * override interface IAnimalBehavior.
     * @return Int value representing the amount of food the animal ate
     */
    @Override
    public int getEatCount() {
        return this.eatCount;
    }


    /**
     * moving the animal to the center of the screen (400,300) by decrementing or incrementing the
     * current coordinates based on speed value and attempting to eat the food.
     */
    public void goToFood(){
        Point currLocation = this.getLocation();

        int currX = currLocation.getX();
        int currY = currLocation.getY();
        int nextX = currX ,nextY = currY;

        // if currY > 300 decrement the Y current coordinate by vertical speed value
        if (currY > Point.getMaxY() / 2){
            nextY = currY - verSpeed;
        }
        // if currY > 300 increment the Y current coordinate by vertical speed value
        else if (currY < Point.getMaxY() / 2) {
             nextY = currY + verSpeed;
        }

        // if currX < 400 increment the X current coordinate by horizontal speed value
        if (currX < Point.getMaxX() / 2) {
            nextX = currX + horSpeed;
            x_dir = RIGHT_DIRECTION;
        }
        // if currX > 400 decrement the X current coordinate by horizontal speed value
        else if (currX > Point.getMaxX() / 2) {
            nextX = currX - horSpeed;
            x_dir = LEFT_DIRECTION;
        }

        // moving the animal to the new location.
        Point newLocation = new Point(nextX, nextY);
        this.move(newLocation);

    }

    /**
     * initializing and starting the animal thread.
     */
    public void start(){
        threadAlive.set(true);
    }

    /**
     * stop terminates the thread by setting the atomic boolean flag to false.
     */
    public void stop(){
        threadAlive.set(false);
    }

    /**
     * runs while the thread is alive.
     * moving animal according to its current direction, speed and dynamically change
     * the image when reaching the boundaries.
     * the animal will move towards food and attempt to eat it when food is available.
     */
    @Override
    public void run() {
        while (threadAlive.get()) {
            // if the thread is currently suspended, it will wait until notified.
            synchronized (this) {
                if (isSuspended()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // will sleep for 0.1 seconds to avoid stuttering when repainting the lion image.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // attempting to move towards food and eat it.
            if (pan.getFood() != null && this.diet.canEat(pan.getFood().getFoodType())){
                goToFood();
                synchronized (this.getClass()) {
                    conditionalFoodEating(pan.getFood());
                }
            }
            // moving and changing animal direction based on the current location and the speed values.
            else {
                if (getLocation().getX() - getHorSpeed() < Point.getMinXY()) x_dir = RIGHT_DIRECTION;
                if (getLocation().getX() + getHorSpeed() > Point.getMaxX()) x_dir = LEFT_DIRECTION;
                if (getLocation().getY() - getVerSpeed() <= Point.getMinXY()) y_dir = DOWN_DIRECTION;
                if (getLocation().getY() + getVerSpeed() >= Point.getMaxY()) y_dir = UP_DIRECTION;

                int nextX = getLocation().getX() + horSpeed * getXDirection();
                int nextY = getLocation().getY() + verSpeed * getYDirection();
                Point nextLocation  = new Point(nextX,nextY);
                move(nextLocation);
            }
            setChanges(true);
            controller.notify(pan);
        }
    }

    /**
     * equals method of animal object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Animal animal = (Animal) o;

        if (Double.compare(animal.getWeight(), getWeight()) != 0) return false;
        if (getName() != null ? !getName().equals(animal.getName()) : animal.getName() != null) return false;
        return getDiet() != null ? getDiet().equals(animal.getDiet()) : animal.getDiet() == null;
    }

    /**
     * @return String representation of the animal object.
     */
    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "]: " + this.name;
    }

    /**
     * abstract method of clone.
     * descendants will be able to implement the clone method.
     * @return the cloned Animal object.
     */
    @Override
    public abstract Animal clone();
}
