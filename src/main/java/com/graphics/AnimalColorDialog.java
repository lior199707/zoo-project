package com.graphics;

import com.animals.Animal;
import com.decorator.AnimalBlueDecorator;
import com.decorator.AnimalNaturalDecorator;
import com.decorator.AnimalRedDecorator;
import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * AnimalColorDialog is a dialog allowing users to change existing animal at runtime implementing
 * the Decorator design pattern.
 *
 * @author Sagie Baram
 * @author Lior Shilon
 */
public class AnimalColorDialog extends AnimalDialog{
    /**
     * default Dimension object with width and height.
     */
    private static final Dimension DEFAULT_DIMENSION = new Dimension(350,400);
    /**
    /**
     * combobox of animal names - taken from the AnimalModel.
     */
    private JComboBox<String> animalNamesCmb;
    /**
     * JLabel object indicating animal image, changed dynamically.
     */
    private JLabel imageLabel;
    /**
     * MoveAnimalDialog constructor
     * @param model AnimalModel object, the animal container.
     * @param zooPanel ZooPanel object, the zoo panel.
     * @see com.graphics.AnimalDialog
     */
    public AnimalColorDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model,zooPanel, DEFAULT_DIMENSION);
        // configurations
        this.setTitle("Change Animal Color");
        this.createDialog();
        this.setVisible(true);
        this.pack();
    }

    /**
     * createDialog adding directional panels to the MoveAnimalDialog
     * @see com.graphics.AnimalDialog
     */
    @Override
    public void createDialog() {
        this.getContentPane().add(createNorthPanel(), BorderLayout.NORTH);
        this.getContentPane().add(createCenterPanel(), BorderLayout.CENTER);
        this.getContentPane().add(createSouthPanel(), BorderLayout.SOUTH);
    }

    /**
     * createNorthPanel will add items to the north panel.
     * adding animal name combo box to the north panel.
     * @see com.graphics.AnimalDialog
     * @return JPanel object of the north panel.
     */
    @Override
    public JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout());
        animalNamesCmb = new JComboBox<>(getModel().getAnimalNames());
        animalNamesCmb.setSelectedItem(0);
        animalNamesCmb.addItemListener(new AnimalNamesHandler());

        northPanel.add(animalNamesCmb);
        return northPanel;
    }

    /**
     * createWestPanel does nothing.
     * @see com.graphics.AnimalDialog
     */
    @Override
    public JPanel createWestPanel() {
        throw new UnsupportedOperationException();
    }

    /**
     * createEastPanel does nothing.
     * @see com.graphics.AnimalDialog
     */
    @Override
    public JPanel createEastPanel() {
        throw new UnsupportedOperationException();
    }

    /**
     * createSouthPanel will add items to the south panel.
     * adding the coordinates x and y text fields to a northern panel.
     * adding the move animal and validate buttons to a southern panel.
     * adding the mentioned panels above to the south panel of the dialog.
     * @see com.graphics.AnimalDialog
     * @return JPanel object of the south panel.
     */
    @Override
    public JPanel createSouthPanel() {
        // initializing panels.
        JPanel panel = new JPanel();
        JButton redButton = new JButton("Red");
        JButton blueButton =new JButton("Blue");
        JButton naturalButton =new JButton("Natural");


        // adding to listeners
        AnimalColorDecoratorHandler handler = new AnimalColorDecoratorHandler();
        naturalButton.addActionListener(handler);
        redButton.addActionListener(handler);
        blueButton.addActionListener(handler);


        // adding components to the north panel. note it's using FlowLayout by default.
        panel.add(naturalButton);
        panel.add(redButton);
        panel.add(blueButton);
        panel.setBorder(PrivateGraphicUtils.createTitledBorder("Please choose color", TitledBorder.TOP, TitledBorder.CENTER));
        return panel;
    }


    /**
     * refreshes the combobox, image and current location labels upon changes.
     * generally used if the moving animal was eaten.
     */
    public void refreshUI(){
        // updating the combobox with the current names in the model.
        animalNamesCmb.setModel(new DefaultComboBoxModel<>(getModel().getAnimalNames()));
        animalNamesCmb.setSelectedIndex(0);
        // if the moved animal was eaten, set the current selected index animal image
        Animal current = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
        imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(current));
        imageLabel.repaint();
    }

    /**
     * utility method to create a center panel.
     * adding image and current location labels to the panel.
     * @return JPanel object of the center panel.
     */
    private JPanel createCenterPanel(){
        JPanel centerPanel = new JPanel();

        imageLabel = new JLabel();
        imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(getModel().getAnimalModel().get(0)));

        GridBagConstraints centerPanelGbc = new GridBagConstraints();
        centerPanelGbc.anchor = GridBagConstraints.LINE_START;

        // setting up the image label.
        setGridBagConstraintPosition(centerPanelGbc, 0,0);
        imageLabel.setBorder(PrivateGraphicUtils.createTitledBorder("Picture", TitledBorder.BELOW_TOP, TitledBorder.CENTER));
        centerPanel.add(imageLabel, centerPanelGbc);

        return centerPanel;
    }

    /**
     * AnimalNamesHandler is a utility class implementing ItemListener.
     * it is used to update the current location label and the image label upon selection of a new animal.
     * @see ItemListener
     */
    private class AnimalNamesHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED){
                if(getModel().getChangesState()){
                    refreshUI();
                    getModel().setChangesState(false);
                }
                Animal animal = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
                imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(animal));
            }
         }
    }

    /**
     * AnimalColorDecoratorHandler is a utility class implementing ActionListener.
     * it is used to decorate an animal's color at runtime.
     */
    private class AnimalColorDecoratorHandler implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            try {
                if (getModel().getChangesState()) {
                    String message = "An animal was eaten\nPlease select animal again";
                    throw new PrivateGraphicUtils.ErrorDialogException(getContentPane(), message);
                }
                int selected = animalNamesCmb.getSelectedIndex();
                ArrayList<Animal> model = getModel().getAnimalModel();

                switch (action) {
                    case "Red" ->{
                        Animal redDecorated = new AnimalRedDecorator(model.get(selected)).decorateAnimal();
                        model.set(selected, redDecorated);
                    }
                    case "Blue" -> {
                        Animal blueDecorated = new AnimalBlueDecorator(model.get(selected)).decorateAnimal();
                        model.set(selected, blueDecorated);
                    }
                    case "Natural" -> {
                        Animal naturalDecorated = new AnimalNaturalDecorator(model.get(selected)).decorateAnimal();
                        model.set(selected, naturalDecorated);
                    }
                }

                imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(model.get(selected)));
                imageLabel.repaint();
                getZooPanel().repaint();
            } catch (PrivateGraphicUtils.ErrorDialogException ignored){}
        }
    }
}
