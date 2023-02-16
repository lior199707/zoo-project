package com.memento.model;

import com.graphics.AnimalModel;

/**
 * ModelMemento used as a state of AnimalModel objects, stores a state of the animal model
 * (the animal and the model sleep state).
 * responsible for storing states of the model.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class ModelMemento {
    /**
     * AnimalModel object of the memento.
     */
    private AnimalModel model;

    /**
     * ModelMemento constructor.
     * @param model AnimalModel object representing the model state to store.
     */
    public ModelMemento(AnimalModel model){
        this.model = model;
    }

    /**
     * Model getter.
     * @return AnimalModel object representing the model state.
     */
    public AnimalModel getModel() { return model; }
}
