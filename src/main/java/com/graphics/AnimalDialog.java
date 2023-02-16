package com.graphics;

import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * AnimalDialog is an abstract JDialog class, instantiating default behaviors for different
 * interactive dialogs. implementing the cardinal dialog interface as it will use cardinal positions
 * for each panel.
 * @see com.graphics.AddAnimalDialog
 *
 * @author Sagie Baram
 * @author Lior Shilon
 */
public abstract class AnimalDialog extends JDialog implements ICardinalDialog {
    /**
     * Composed ZooPanel object which is the parent panel of the dialog window.
     */
    private final ZooPanel zooPanel;
    /**
     * Composed AnimalModel object which holds the animal ArrayList and a set of methods within it.
     */
    private final AnimalModel model;

    /**
     * AnimalDialog constructor.
     * It sets default modality, close operation, centers the location of the dialog, size and resizability
     * to descendant classes.
     *
     * Both params are set here for separation of concerns purposes.
     * @param model AnimalModel object representing the animal model.
     * @param zooPanel ZooPanel object representing the parent panel.
     * @param dimension Dimension object representing the default dimension to set for each descendant class.
     */
    public AnimalDialog(AnimalModel model, ZooPanel zooPanel, Dimension dimension){
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLocation(PrivateGraphicUtils.centerWindow((int) dimension.getWidth(), (int) dimension.getHeight()));
        this.addWindowListener(new AnimalDialogWindowAdapter());
        this.setSize(dimension);
        this.setResizable(false);

        this.model = model;
        this.zooPanel = zooPanel;
    }

    /**
     * passing a JTextField and range of values to set placeholders indicating the value range.
     * when focus is gained the listener will confirm the input is not changed from the placeholder,
     * if so, it will empty the text field and set the foreground to black.
     * when focus is lost, if the text field is empty it will set the text to the placeholder value,
     * and set foreground to gray.
     *
     * @param validRangeTextField JTextField object to add a focus listener to.
     * @param MIN_RANGE int value representing the minimum value acceptable in the text field.
     * @param MAX_RANGE int value representing the maximum value acceptable in the text field.
     */
    protected void addValidRangeFocusListener(JTextField validRangeTextField,int MIN_RANGE, int MAX_RANGE) {
        validRangeTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (validRangeTextField.getText().equals(MIN_RANGE + "-" + MAX_RANGE)) {
                    validRangeTextField.setText("");
                    validRangeTextField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (validRangeTextField.getText().isEmpty()) {
                    validRangeTextField.setText(MIN_RANGE + "-" + MAX_RANGE);
                    validRangeTextField.setForeground(Color.GRAY);
                }
            }
        });
    }

    /**
     * AnimalDialogWindowAdapter is a private inner class, extending WindowAdapter.
     * once the user attempts to close the window a confirm dialog window will appear.
     * if the user presses OK it shall dispose of the parent dialog window i.e. the Animal Dialog.
     */
    private class AnimalDialogWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            int result = JOptionPane.showConfirmDialog(
                    getRootPane().getParent(), "Are you sure?");
            if (result == JOptionPane.OK_OPTION) {
                // NOW we change it to dispose on close.
                setDefaultCloseOperation(
                        JFrame.DISPOSE_ON_CLOSE);
                setVisible(false);
                dispose();
            }
        }
    }

    /**
     * setGridBackConstraints attempts to simplify and reduce the amount code necessary to set
     * the position constraints of an element.
     * @param gbc GridBackConstraints object to modify.
     * @param gridX the grid x value.
     * @param gridY the grid y value.
     */
    protected void setGridBagConstraintPosition(GridBagConstraints gbc, int gridX, int gridY){
        gbc.gridx = gridX;
        gbc.gridy = gridY;
    }

    /**
     * setValidTextField shifts the text field foreground based on state.
     * @param textField JTextField object to modify.
     * @param state boolean value representing if the current state is valid or invalid.
     */
    public void setValidTextField(JTextField textField, boolean state){
        if (state)
            textField.setForeground(Color.BLACK);
        else
            textField.setForeground(Color.RED);
    }

    /**
     * animalModel getter.
     * @return AnimalModel object of the AnimalDialog.
     */
    public AnimalModel getModel() {
        return model;
    }

    /**
     * zooPanel getter.
     * @return ZooPanel object of the AnimalDialog.
     */
    public ZooPanel getZooPanel() {
        return zooPanel;
    }
}
