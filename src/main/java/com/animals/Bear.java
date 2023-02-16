
package com.animals;


import com.diet.Omnivore;
import com.mobility.Point;

/**
 * Bear class representing the Bear animal. It can roar!
 * @see com.animals.AnimalRoar
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Bear extends AnimalRoar {
    /**
     * FurColors enum indicating the fur color of a bear.
     */
    private FurColors furColor;
    /**
     * constant double value of the default weight of a bear.
     */
    private static final double DEFAULT_WEIGHT = 308.2;
    /**
     * constant Point coordinates of the default starting location of a bear.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(100, 5);
    /**
     * constant double value of the coefficient used for weight calculation.
     */
    private static final double SIZE_COEFFICIENT = 1.5;
    /**
     * constant int value of the default size of a Bear.
     */
    private static final int DEFAULT_SIZE =  (int)(DEFAULT_WEIGHT / SIZE_COEFFICIENT);
    /**
     * constant Enum FurColors value of the default fur color for a Bear.
     */
    private static final FurColors DEFAULT_FUR_COLOR = FurColors.GRAY;

    /**
     * private enum class to hold different fur colors
     */
    private enum FurColors {
        /**
         * White fur color
         */
        WHITE("WHITE"),
        /**
         * Gray fur color
         */
        GRAY("GRAY"),
        /**
         * Black fur color
         */
        BLACK("BLACK");

        /**
         * String representation of the fur color.
         */
        private final String color;

        /**
         * FurColor constructor.
         * can be initialized with a String representation of the fur color.
         * @param color String representation of the fur color.
         */
        FurColors(String color) {
            this.color = color;
        }

        /**
         * color getter
         * @return String representation of the fur color.
         */
        public String getColor() {
            return color;
        }
    }

    /**
     * Bear constructor.
     * setting default location, default fur color and diet!
     * passing name, location, size, horizontal speed, vertical speed and color to super.
     * @see com.animals.AnimalRoar
     * @param name String value of the animal's name, should contain only letters.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     */
    public Bear(String name, int size, int horSpeed, int verSpeed, String col){
        super(name,DEFAULT_STARTING_LOCATION,size,horSpeed,verSpeed,col);
        setWeight(getSize() * 1.5);
        setFurColor(String.valueOf(FurColors.GRAY));
        setDiet(new Omnivore());
        loadImages(animalShortPathName());
    }

    /**
     * Bear constructor.
     * setting default size.
     * passing name, horizontal speed and vertical speed to main constructor.
     * @param name String value of the animal's name, should contain only letters.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     */
    public Bear(String name,int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed,verSpeed, getDefaultColor());
    }

    /**
     * Bear copy constructor
     * @param other Bear object to copy.
     */
    public Bear(Bear other){
        super(other);
        this.furColor = other.getFurColor();
    }

    /**
     * class Bear static method.
     * @return Point object of the default starting location of a Bear.
     */
    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }

    /**
     * furColor getter.
     * @return furColor value of bear.
     */
    public FurColors getFurColor() {
        return furColor;
    }

    /**
     * class Bear static method.
     * @return Enum FurColors value of the default fur color of a bear(Grey).
     */
    public static FurColors getDefaultFurColor(){
        return DEFAULT_FUR_COLOR;
    }

    /**
     * class Bear static method
     * @return constant double value of the coefficient used for weight calculation.
     */
    public static double getSizeCoefficient() {
        return SIZE_COEFFICIENT;
    }

    /**
     * furColor  setter.
     * traverses the FurColors enum, if value of given param is equal it updates the furColor field.
     * leaving the field as is.
     *
     * @param furColor the color to set.
     * @return boolean value if the color was found and set or not.
     */
    public boolean setFurColor(String furColor) {
        boolean isSuccess = false;
        for (FurColors f : FurColors.values()) {
            if (f.name().equals(furColor)) {
                this.furColor = FurColors.valueOf(furColor);
                isSuccess = true;
                break;
            }
        }
        return isSuccess;
    }

    /**
     * roar implementation of a bear.
     */
    @Override
    public void roar() {
        System.out.println(getName() + " Stands on its hind legs, roars and scratches its belly");
    }

    /**
     * equals method of Bear object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bear bear = (Bear) o;

        return getFurColor() == bear.getFurColor();
    }

    /**
     * toString of bear.
     * @return String representation of Bear.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Animal clone() {
        return new Bear(this);
    }


    /**
     * override abstract class Animal animalShortPathName().
     * @return String representation of the short path name for loading Bear images.
     */
    @Override
    public String animalShortPathName() {
        return "bea";
    }

    /**
     * override interface IAnimalBehavior getAnimalName().
     * @return String representation of the animal natural name.
     */
    @Override
    public String getAnimalName() {
        return "Bear";
    }

}