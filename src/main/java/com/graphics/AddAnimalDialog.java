package com.graphics;

import com.animals.*;
import com.factory.FactoryProducer;
import com.factory.IAnimalFactory;
import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static com.privateutil.PrivateGraphicUtils.findAnimalImagePath;

/**
 * AddAnimalDialog represents a dialog for adding animal to the Zoo.
 * users can select animal types from a combobox, input name, size vertical and horizontal speed and a color.
 * the ui will present an appropriate image (based on animal type and color) dynamically.
 * weight, unique fields and default location on the zoo panel are also changed dynamically based on size and animal type.
 * @see com.graphics.AnimalDialog
 * @see com.graphics.ZooPanel
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public abstract class AddAnimalDialog extends AnimalDialog {
    /**
     * int representation of the default animal direction.
     */
    private static final int DEFAULT_DIRECTION = 1;
    /**
     * default Dimension object with width and height.
     */
    private static final Dimension DEFAULT_DIMENSION = new Dimension(500, 350);
    /**
     * array holding the size coefficients of each animal, used to calculate the animal weight dynamically.
     */
    private final static double[] coefficientArr = {
            Lion.getSizeCoefficient(),
            Bear.getSizeCoefficient(),
            Giraffe.getSizeCoefficient(),
            Elephant.getSizeCoefficient(),
            Turtle.getSizeCoefficient()
    };

    /**
     * default animal type when loading the dialog initially.
     */
    private static String INIT_ANIMAL_TYPE;

    /**
     * default animal color when loading the dialog initially.
     */
    private final static String INIT_ANIMAL_COLOR = "Natural";

    /**
     * comboBox of the animal types.
     */
    private final JComboBox<String> animalTypesCmb;

    /**
     * comboBox of the animal colors.
     */
    private final JComboBox<String> animalColorsCmb;

    /**
     * JTextField object to input animal size.
     */
    private JTextField sizeTextField;

    /**
     * JTextField object to input animal vertical speed.
     */
    private JTextField vSpeedTextField;

    /**
     * JTextField object to input animal horizontal speed.
     */
    private JTextField hSpeedTextField;

    /**
     * JTextField object to input animal name.
     */
    private JTextField nameTextField;

    /**
     * JLabel object indicating default location, changed dynamically.
     */
    private JLabel locationLabel;

    /**
     * JLabel object indicating animal weight, changed dynamically.
     */
    private JLabel weightLabel;

    /**
     * JLabel object indicating animal unique attribute, changed dynamically.
     */
    private JLabel uniqueLabel;

    /**
     * JLabel object indicating animal image, changed dynamically.
     */
    private JLabel imageLabel;

    /**
     * JButton object indicating the add animal button, pressing it will initialize the animal with the user given input.
     */
    private JButton addAnimalButton;
    /**
     * JButton object indicating the validate button, pressing it will validate the user input before enabling the add animal button.
     */
    private JButton validateButton;

    /**
     * String representation of the animal type.
     */
    private String animalType;
    /**
     * String representation of the animal color.
     */
    private String animalColor;

    /**
     * boolean representation of the name text field validity state.
     */
    private boolean nameStatus;
    /**
     * boolean representation of the size text field validity state.
     */
    private boolean sizeStatus;
    /**
     * boolean representation of the vertical speed text field validity state.
     */
    private boolean vSpeedStatus;
    /**
     * boolean representation of the horizontal speed text field validity state.
     */
    private boolean hSpeedStatus;

    /**
     * IAnimalFactory instance indicating which factory will be used.
     */
    private IAnimalFactory animalFactory;

    /**
     * AddAnimalDialog constructor.
     * @param model AnimalModel object, the animal container.
     * @param zooPanel ZooPanel object, the zoo panel.
     * @param animalTypes String array of animals of the same type, i.e {carnivores},{omnivores},{herbivores}
     * @param factoryType String representation of the AnimalDialog factory type.
     * @see com.graphics.AnimalDialog
     */
    public AddAnimalDialog(AnimalModel model, ZooPanel zooPanel, String[] animalTypes, String factoryType) {
        super(model, zooPanel, DEFAULT_DIMENSION);

        // initializing factory.
        FactoryProducer factoryProducer = new FactoryProducer();
        animalFactory = factoryProducer.getFactory(factoryType);

        // configuration
        INIT_ANIMAL_TYPE = animalTypes[0];
        animalTypesCmb = new JComboBox<>(animalTypes);

        String[] animalColors = {"Natural", "Red", "Blue"};
        animalColorsCmb = new JComboBox<>(animalColors);
        animalColor = INIT_ANIMAL_COLOR;
        animalType = INIT_ANIMAL_TYPE;

        this.setTitle("Add Animal");
        this.createDialog();
        this.setVisible(true);
        this.pack();
    }

    /**
     * createDialog adding directional panels to the AddAnimalDialog
     * @see com.graphics.AnimalDialog
     */
    @Override
    public void createDialog() {
        this.getContentPane().add(createNorthPanel(), BorderLayout.NORTH);
        this.getContentPane().add(createEastPanel(), BorderLayout.EAST);
        this.getContentPane().add(createWestPanel(), BorderLayout.WEST);
        this.getContentPane().add(createSouthPanel(), BorderLayout.SOUTH);
    }

    /**
     * createNorthPanel will add items to the north panel.
     * adding animal type combo box to the north panel.
     * @see com.graphics.AnimalDialog
     * @return JPanel object of the north panel.
     */
    @Override
    public JPanel createNorthPanel() {
        JPanel animalTypePanel = new JPanel();

        // setting border
        animalTypesCmb.setBorder(PrivateGraphicUtils.createTitledBorder("Choose Animal:",
                TitledBorder.TOP, TitledBorder.CENTER));

        // adding item listener.
        animalTypesCmb.addItemListener(new AnimalTypesHandler());

        // adding the combobox to the panel.
        animalTypePanel.add(animalTypesCmb);

        return animalTypePanel;
    }


    /**
     * createWestPanel will add items to the west panel.
     * adding name, size, vertical and horizontal speed text fields and labels.
     * adding color label and combobox.
     * @return JPanel object of the west panel.
     */
    @Override
    public JPanel createWestPanel() {
        // initializing panels.
        JPanel inputPanel = new JPanel();
        JPanel northInputPanel = new JPanel();
        JPanel southInputPanel = new JPanel();

        // setting layouts.
        inputPanel.setLayout(new BorderLayout());
        northInputPanel.setLayout(new GridBagLayout());
        southInputPanel.setLayout(new GridBagLayout());

        // create north input panel components with default placeholders.

        JLabel nameLabel = new JLabel("Name:");
        JLabel sizeLabel = new JLabel("Size:");
        JLabel vSpeedLabel = new JLabel("V_Speed:");
        JLabel hSpeedLabel = new JLabel("H_Speed:");
        JLabel colorLabel = new JLabel("Color:");

        nameTextField = new JTextField(10);
        sizeTextField = new JTextField("50-300", 10);
        vSpeedTextField = new JTextField("1-10", 10);
        hSpeedTextField = new JTextField("1-10", 10);

        animalColorsCmb.addItemListener(new AnimalColorHandler());

        // setting color for placeholder text.
        sizeTextField.setForeground(Color.GRAY);
        vSpeedTextField.setForeground(Color.GRAY);
        hSpeedTextField.setForeground(Color.GRAY);

        addSpeedInputDocumentListener(vSpeedTextField);
        addSpeedInputDocumentListener(hSpeedTextField);

        // focus listener for input. reset if lost focus & empty.
        addValidRangeFocusListener(sizeTextField, 50, 300);
        addValidRangeFocusListener(vSpeedTextField, 1, 10);
        addValidRangeFocusListener(hSpeedTextField, 1, 10);

        // document listener for size text field.
        sizeTextField.getDocument().addDocumentListener((IChangeDocument) e -> {
            try {
                String currentSizeText = sizeTextField.getText();
                // disabling animal button upon changes to the size text field.
                addAnimalButton.setEnabled(false);
                if (currentSizeText.equals("")) {
                    weightLabel.setText("Weight: ");
                } else {
                    // currently, valid - set to black foreground.
                    setValidTextField(sizeTextField, sizeStatus = true);
                    int size = Integer.parseInt(currentSizeText);
                    if (size < 50 || size > 300) {
                        throw new NumberFormatException();
                    }
                    // if the size value is between 50-300, calculate the weight of selected animal
                    // based on the size input.
                    weightLabel.setText("Weight: " + getWeightOfSelectedAnimal(size));
                }
            } catch (NumberFormatException ignored) {
                // otherwise, invalid - set to red foreground.
                setValidTextField(sizeTextField, sizeStatus = false);
            }
        });

        // document listener for name text field.
        nameTextField.getDocument().addDocumentListener((IChangeDocument) e -> {
            String currentText = nameTextField.getText();
            // disabling animal button upon changes to the name text field.
            addAnimalButton.setEnabled(false);
            // if the name text field is alphabetic set to black foreground.
            if (currentText.matches("[A-Za-z]+")) {
                setValidTextField(nameTextField, nameStatus = true);
            } else {
                // if name is not alphabetic - contains numeric value or signs i.e. ^&*(@#$.
                setValidTextField(nameTextField, nameStatus = false);
            }
        });

        // create south input panel components

        // initializing default label values, Lion is the first choice by default.
        weightLabel = new JLabel("Weight:");
        uniqueLabel = new JLabel("Scar Count: " + Lion.getDefaultScarCount());
        locationLabel = new JLabel("Location: " + Lion.getDefaultStartingLocation());


        // setting layout constraints for north panel.
        GridBagConstraints gbcNorthInputPanel = new GridBagConstraints();

        gbcNorthInputPanel.insets = new Insets(0, 10, 0, 0);
        gbcNorthInputPanel.anchor = GridBagConstraints.LINE_START;

        // label constraints
        setGridBagConstraintPosition(gbcNorthInputPanel,0,0);
        northInputPanel.add(nameLabel, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,0,1);
        northInputPanel.add(sizeLabel, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,0,2);
        northInputPanel.add(vSpeedLabel, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,0,3);
        northInputPanel.add(hSpeedLabel, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,0,4);
        northInputPanel.add(colorLabel, gbcNorthInputPanel);

        // input constraints.
        setGridBagConstraintPosition(gbcNorthInputPanel,1,0);
        northInputPanel.add(nameTextField, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,1,1);
        northInputPanel.add(sizeTextField, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,1,2);
        northInputPanel.add(vSpeedTextField, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,1,3);
        northInputPanel.add(hSpeedTextField, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,1,4);
        northInputPanel.add(animalColorsCmb, gbcNorthInputPanel);

        // setting layout constraints for south panel.
        GridBagConstraints gbcSouthInputPanel = new GridBagConstraints();

        gbcSouthInputPanel.anchor = GridBagConstraints.LINE_START;
        gbcSouthInputPanel.weightx = 1;
        gbcSouthInputPanel.insets = new Insets(0, 5, 0, 0);

        setGridBagConstraintPosition(gbcSouthInputPanel,0,5);
        southInputPanel.add(weightLabel, gbcSouthInputPanel);

        setGridBagConstraintPosition(gbcSouthInputPanel,0,6);
        southInputPanel.add(uniqueLabel, gbcSouthInputPanel);

        setGridBagConstraintPosition(gbcSouthInputPanel,0,7);
        southInputPanel.add(locationLabel, gbcSouthInputPanel);

        // setting up a titled border.
        northInputPanel.setBorder(PrivateGraphicUtils.createTitledBorder("Input:", TitledBorder.CENTER, TitledBorder.LEFT));

        // positioning the panels in north & south
        inputPanel.add(northInputPanel, BorderLayout.NORTH);
        inputPanel.add(southInputPanel, BorderLayout.SOUTH);

        // return the input panel.
        return inputPanel;
    }

    /**
     * createEastPanel will add items to the east panel.
     * add image panel, loading the image.
     * @return JPanel object of the west panel.
     */
    @Override
    public JPanel createEastPanel() {
        // initializing image panel with GridBagLayout.
        JPanel imagePanel = new JPanel(new GridBagLayout());
        // loading the image path with default values into a label.
        String path = findAnimalImagePath(INIT_ANIMAL_TYPE, INIT_ANIMAL_COLOR, DEFAULT_DIRECTION);
        imageLabel = new JLabel(PrivateGraphicUtils.resizeImage(path, 220, 180));

        // setting constraints for the panel.
        GridBagConstraints gbcImagePanel = new GridBagConstraints();
        gbcImagePanel.insets = new Insets(0, 0, 0, 10);
        setGridBagConstraintPosition(gbcImagePanel,5,5);

        // adding a border to the panel.
        imageLabel.setBorder(PrivateGraphicUtils.createTitledBorder("Picture",TitledBorder.BELOW_TOP, TitledBorder.CENTER));
        imagePanel.add(imageLabel, gbcImagePanel);

        return imagePanel;
    }

    /**
     * createSouthPanel will add items to the south panel.
     * adding validate and add animal buttons.
     * @return JPanel object of the west panel.
     */
    @Override
    public JPanel createSouthPanel() {
        // initialize the button panel.
        JPanel buttonPanel = new JPanel();

        addAnimalButton = new JButton("Add Animal");
        validateButton = new JButton("Validate");

        // disabling addAnimalButton. will enable after pressing validate - in action listener.
        addAnimalButton.setEnabled(false);

        // adding to action listener.
        validateButton.addActionListener(validationHandler -> {
            boolean validated;
            // if the model contains the animal name, it will throw an exception leading to prompting an error message.
            if (getModel().containsAnimalName(nameTextField.getText())) {
                try {
                    String message = "Name already used, please choose another name";
                    throw new PrivateGraphicUtils.ErrorDialogException(this, message);
                } catch (PrivateGraphicUtils.ErrorDialogException ignored) {}
            } else {
                // if all status indicators are true, the add animal button will be enabled.
                validated = (nameStatus && sizeStatus && vSpeedStatus && hSpeedStatus);
                if (!validated){
                    try {
                        throw new PrivateGraphicUtils.ErrorDialogException(this,"Invalid input!\nPlease ensure there are no red flags before validating the input.");
                    } catch (PrivateGraphicUtils.ErrorDialogException ignored) { }
                }
                addAnimalButton.setEnabled(validated);
            }
        });

        addAnimalButton.addActionListener(new AddAnimalHandler());

        // adding buttons to button panel.
        buttonPanel.add(validateButton);
        buttonPanel.add(addAnimalButton);
        return buttonPanel;
    }

    /**
     * getWeightOfSelectedAnimal calculates the weight of the selected animal (from the combobox),
     * based ln the size of the animal given as input times the coefficient of the animal.
     * @param sizeOfAnimal integer value representing the size of the animal.
     * @return double value representing the weight of the selected animal.
     */
    public double getWeightOfSelectedAnimal(int sizeOfAnimal) {
        return sizeOfAnimal * coefficientArr[animalTypesCmb.getSelectedIndex()];
    }

    /**
     * addSpeedInputDocumentListener is a utility method, adding a document listener
     * to a given text field object. It sets the vertical or horizontal speed validity status and changes
     * the foreground color upon changes.
     * @param speedTextField JTextField object to listen.
     */
    private void addSpeedInputDocumentListener(JTextField speedTextField) {
        speedTextField.getDocument().addDocumentListener((IChangeDocument) e -> {
            try {
                addAnimalButton.setEnabled(false);
                String currentText = speedTextField.getText();
                if (speedTextField == vSpeedTextField){
                    setValidTextField(speedTextField, vSpeedStatus = true);
                } else {
                    setValidTextField(speedTextField, hSpeedStatus = true);
                }
                int speed = Integer.parseInt(currentText);
                if (speed < 1 || speed > 10){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ignored){
                if (speedTextField == vSpeedTextField){
                    setValidTextField(speedTextField, vSpeedStatus = false);
                } else {
                    setValidTextField(speedTextField, hSpeedStatus = false);
                }
            }
        });
    }


    /**
     * AnimalTypesHandler is a utility class implementing ItemListener.
     * it listens to a JComboBox holding the animal types and setting the appropriate unique attribute,
     * location and picture based on the selected event (item) and repainting the label.
     */
    private class AnimalTypesHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // disable add animal button upon selecting a new item.
                addAnimalButton.setEnabled(false);
                String item = (String) e.getItem();
                switch (item) {
                    case "Lion" -> {
                        uniqueLabel.setText("Scar Count: " + Lion.getDefaultScarCount());
                        locationLabel.setText("Location: " + Lion.getDefaultStartingLocation());
                    }
                    case "Bear" -> {
                        uniqueLabel.setText("Fur Color: " + Bear.getDefaultFurColor());
                        locationLabel.setText("Location: " + Bear.getDefaultStartingLocation());
                    }
                    case "Giraffe" -> {
                        uniqueLabel.setText("Neck Length: " + Giraffe.getDefaultNeckLength());
                        locationLabel.setText("Location: " + Giraffe.getDefaultStartingLocation());
                    }
                    case "Turtle" -> {
                        uniqueLabel.setText("Age: " + Turtle.getDefaultAge());
                        locationLabel.setText("Location: " + Turtle.getDefaultStartingLocation());
                    }
                    case "Elephant" -> {
                        uniqueLabel.setText("Trunk Length: " + Elephant.getDefaultTrunkLength());
                        locationLabel.setText("Location: " + Elephant.getDefaultStartingLocation());
                    }
                }
                // loading the appropriate image.
                animalType = item;
                String path = findAnimalImagePath(animalType, animalColor, DEFAULT_DIRECTION);
                imageLabel.setIcon(PrivateGraphicUtils.resizeImage(path, 220, 180));
                imageLabel.repaint();
            }
        }
    }

    /**
     * AddAnimalHandler is a utility class implementing ActionListener.
     * upon clicking on the add animal button (enabled only when the inputs are valid),
     * a new animal will be added to the animal model and a set of commands will occur.
     */
    private class AddAnimalHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // assigning current input to variables.
            String animalName = nameTextField.getText();
            String animalColor = String.valueOf(animalColorsCmb.getSelectedItem());
            int animalSize = Integer.parseInt(sizeTextField.getText());
            int animalVSpeed = Integer.parseInt(vSpeedTextField.getText());
            int animalHSpeed = Integer.parseInt(hSpeedTextField.getText());

            // if the model size is yet to reach the maximum size allowed, instantiate an animal
            if (getModel().getAnimalQueueSize() < AnimalModel.getMaxQueueSize()) {
                Animal animal = animalFactory.createAnimal(animalType, animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);

                assert animal != null;
                animal.setChanges(true);
                animal.setPan(getZooPanel());

                getModel().addAnimal(animal);
                getZooPanel().repaint();

                // reset fields to default placeholders.
                nameTextField.setText("");
                sizeTextField.setText("50-300");
                vSpeedTextField.setText("1-10");
                hSpeedTextField.setText("1-10");
                sizeTextField.setForeground(Color.GRAY);
                vSpeedTextField.setForeground(Color.GRAY);
                hSpeedTextField.setForeground(Color.GRAY);
                animalColorsCmb.setSelectedIndex(0);
                // setting focus to the animal type combo box.
                animalTypesCmb.requestFocusInWindow();

                // if the info table is open, it will update it dynamically upon adding a new animal.
                if (InfoTableDialog.getIsOpen())
                    getZooPanel().getInfoTable().updateTable();

            } else {
                try {
                    String message = "You cannot add more than 15 animals";
                    throw new PrivateGraphicUtils.ErrorDialogException(getContentPane().getParent(), message);
                } catch (PrivateGraphicUtils.ErrorDialogException ignored) {}
            }

        }
    }

    /**
     * AnimalColorHandler is a utility class implementing ItemListener.
     * it changes the animal image label dynamically when selecting different colors from the combobox.
     */
    private class AnimalColorHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                animalColor = (String) e.getItem();
                String path = findAnimalImagePath(animalType, animalColor, DEFAULT_DIRECTION);
                imageLabel.setIcon(PrivateGraphicUtils.resizeImage(path, 220, 180));
                imageLabel.repaint();
            }
        }
    }
}
