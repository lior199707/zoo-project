package com.graphics;

import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ZooFrame is the main frame for the view aspect of the zoo project.
 * it extends JFrame and holds several components in it such as a menu bar,
 * the zoo panel and acts as the main class.
 * @see JFrame
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class ZooFrame extends JFrame{
    /**
     * JMenuBar object to hold different menu items.
     */
    private final JMenuBar menuBar;
    /**
     * ZooPanel object where the animals and buttons will take place.
     */
    private final ZooPanel zooPanel;

    /**
     * ZooFrame constructor.
     * configuration of the frame window.
     */
    ZooFrame(){
        super("Zoo");
        menuBar = constructJMenuBar();
        zooPanel = ZooPanel.getInstance();

        this.add(zooPanel);
        this.setJMenuBar(menuBar);

        int frameX = 1000, frameY = 850;
        this.setSize(frameX,frameY);
        this.setLocation(PrivateGraphicUtils.centerWindow(frameX,frameY));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * utility method to construct the menu bar.
     * @return JMenuBar with JMenu's and JMenuItems set up.
     */
    private JMenuBar constructJMenuBar( ){
        JMenuBar menuBar = new JMenuBar();
        // initiating JMenu fields.

        JMenu file = new JMenu("File");
        JMenu background = new JMenu("Background");
        JMenu states = new JMenu("States");
        JMenu help = new JMenu("Help");

        // initiating items for file menu.
        JMenuItem exit = new JMenuItem("Exit");

        // initiating items for background menu.
        JMenuItem image = new JMenuItem("Image");
        JMenuItem green = new JMenuItem("Green");
        JMenuItem none = new JMenuItem("None");

        // initiating items for states menu.
        JMenuItem saveState = new JMenuItem("Save State");
        JMenuItem restoreState = new JMenuItem("Restore State");

        // initiating items for help menu.
        JMenuItem helpMenuItem = new JMenuItem("Help");
        JMenuItem manualMenuItem = new JMenuItem("Manual");


        file.add(exit);

        background.add(image);
        background.add(green);
        background.add(none);

        states.add(saveState);
        states.add(restoreState);

        help.add(helpMenuItem);
        help.add(manualMenuItem);

        // adding menus to the menu bar.
        menuBar.add(file);
        menuBar.add(background);
        menuBar.add(states);
        menuBar.add(help);

        MenuBarHandler listener = new MenuBarHandler();
        exit.addActionListener(listener);
        image.addActionListener(listener);
        green.addActionListener(listener);
        none.addActionListener(listener);
        saveState.addActionListener(listener);
        restoreState.addActionListener(listener);
        helpMenuItem.addActionListener(listener);
        manualMenuItem.addActionListener(listener);

        return menuBar;
    }

    /**
     * MenuBarHandler is a utility class implementing ActionListener.
     * it listens to events coming from the menu bar.
     */
    private class MenuBarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            switch (actionCommand) {
                case  "Exit" -> System.exit(1);
                case "Image" -> zooPanel.setImageBackground();
                case "Green" -> zooPanel.setGreenBackground();
                case "None" -> zooPanel.setNoBackground();
                case "Save State" -> zooPanel.saveState();
                case "Restore State" -> zooPanel.restoreState();
                case "Help" -> {
                    String message = "Home Work 4\nDP Implementation";
                    PrivateGraphicUtils.popInformationDialog(null,message);
                }
                case "Manual" -> {
                    String message = """
                            OPTIONS:
                            1.Add Animal - allows you to add animals to the zoo, you may add up to 10 animals to the zoo at a time.
                            2.Move Animal - allows you to move a selected animals to another location.
                            3.Clear All - removes all the animals in the zoo panel, does not affect food.
                            4.Add Food - allows you to add food to the zoo panel, type of food: Lettuce, Meat, Cabbage.
                            5.Info - allows you view the zoo's information in a tabular form dynamically.
                            6.Exit - exits the program.
                            
                            IMPORTANT SIDE NOTES:
                            * When adding a new animal, please confirm there are no predators on the same coordinates, otherwise the added animal will be instantly eaten.
                            * When adding a new animal, red text fields indicate invalid input and black text fields indicate valid input.
                            * Food spawns in the middle of the zoo panel, it's coordinates are: (400, 300).
                            * A predator can eat more than a single prey at a time.
                            * We recommend to leave the info table open, it will exhibit every information changes about the animals dynamically.""";
                    PrivateGraphicUtils.popInformationDialog(null,message);

                }
            }
        }
    }

    /**
     * main method
     * @param args arguments input from cli.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZooFrame();
            }
        });
    }
}
