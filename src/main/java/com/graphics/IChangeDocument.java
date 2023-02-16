package com.graphics;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * interface IChangeDocument extends document listener and set default implementation to avoid code duplication.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface IChangeDocument extends DocumentListener {
    /**
     * uses the document event to perform changes upon listener events.
     * @param e DocumentEvent object.
     */
    void changeDocument(DocumentEvent e);

    /**
     * invokes upon insertion to a document.
     * @param e DocumentEvent object.
     */
    @Override
    default void insertUpdate(DocumentEvent e){
        changeDocument(e);
    }
    /**
     * invokes upon deleting from a document.
     * @param e DocumentEvent object.
     */
    @Override
    default void removeUpdate(DocumentEvent e){
        changeDocument(e);
    }
    /**
     * invokes upon changing a document.
     * @param e DocumentEvent object.
     */
    @Override
    default void changedUpdate(DocumentEvent e){
        changeDocument(e);
    }
}
