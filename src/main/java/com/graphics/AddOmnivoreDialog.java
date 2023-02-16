package com.graphics;

/**
 * AddOmnivoreDialog is an extension of AddAnimalDialog
 * opens a dialog with Omnivores only.
 *
 * @author Sagie Baram
 * @author Lior Shilon
 */
public class AddOmnivoreDialog extends AddAnimalDialog {
    /**
     * String array of omnivore animal types.
     */
    private static final String[] types = {"Bear"};
    /**
     * String representation of the factory type.
     */
    private static final String factoryType = "Omnivore";

    /**
     * AddOmnivoreDialog constructor.
     * passes the animal types string array and the factory type to the constructor of
     * AddAnimalDialog.
     * @param model AnimalModel object, the animal container.
     * @param zooPanel ZooPanel object, the zoo panel.
     */
    public AddOmnivoreDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model, zooPanel, types, factoryType);
    }
}
