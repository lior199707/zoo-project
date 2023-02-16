package com.graphics;

/**
 * AddHerbivoresDialog is an extension of AddAnimalDialog
 * opens a dialog with Herbivores only.
 *
 * @author Sagie Baram
 * @author Lior Shilon
 */
public class AddHerbivoreDialog extends AddAnimalDialog {
    /**
     * String array of herbivore animal types.
     */
    private static final String[] types = {"Giraffe", "Turtle", "Elephant"};
    /**
     * String representation of the factory type.
     */
    private static final String factoryType = "Herbivore";

    /**
     * AddHerbivoreDialog constructor.
     * passes the animal types string array and the factory type to the constructor of
     * AddAnimalDialog.
     * @param model AnimalModel object, the animal container.
     * @param zooPanel ZooPanel object, the zoo panel.
     * @see AddAnimalDialog
     */
    public AddHerbivoreDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model, zooPanel, types, factoryType);
    }
}
