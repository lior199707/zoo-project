package com.graphics;

import javax.swing.*;

/**
 * ICardinal Dialog is an interface used by Dialogs that want to be set up with cardinal positions.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface ICardinalDialog {
    /**
     * abstract method to create the north side of the panel.
     * @return JPanel containing different items each descendant can implement.
     */
    JPanel createNorthPanel();
    /**
     * abstract method to create the west side of the panel.
     * @return JPanel containing different items each descendant can implement.
     */
    JPanel createWestPanel();
    /**
     * abstract method to create the east side of the panel.
     * @return JPanel containing different items each descendant can implement.
     */
    JPanel createEastPanel();
    /**
     * abstract method to create the south side of the panel.
     * @return JPanel containing different items each descendant can implement.
     */
    JPanel createSouthPanel();

    /**
     * createDialog will call the directional panel methods and add them to dialog.
     */
    void createDialog();
}
