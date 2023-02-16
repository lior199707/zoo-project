package com.food.plants;

/**
 * Lettuce class, a Plant descendant.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Lettuce extends Plant {
	/**
	 * Singleton lettuce instance.
	 */
	private static Lettuce instance = null;
	/**
	 * Lettuce constructor.
	 */
	private Lettuce() { }

	/**
	 * getInstance method
	 * @return if an object exists, it returns the object, otherwise creates a new Lettuce object.
	 */
	public static Lettuce getInstance() {
		if (instance == null){
			instance = new Lettuce();
		}
		return instance;
	}

	/**
	 * override abstract class Food animalShortPathName().
	 * @return String representation of the short path name for loading Lettuce image.
	 */
	@Override
	public String foodShortPathName() {
		return "Lettuce";
	}

}