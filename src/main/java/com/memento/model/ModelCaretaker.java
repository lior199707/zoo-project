package com.memento.model;

import java.util.Stack;

/**
 * ModelCareTaker contains a stack of ModelMemento's.
 * it can push or pop ModelMemento's into the stack.
 * used to save animal model states during the software's lifecycle.
 * (each animal model contains animalArrayList that uses AnimalCareTaker to store the animal ArrayList state).
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class ModelCaretaker implements Cloneable {

    /**
     * Stack of ModelMemento's indicating the amount of saved states.
    */
    private Stack<ModelMemento> stateList = new Stack<>();

    /**
     * integer value representing the maximum size of the stack - the amount of saved states.
     */
    private static final int MAX_SIZE = 3;

    /**
     * pushing mementos to the stack(saving the state of the AnimalModel).
     * @param m ModelMemento object to push.
     */
    public void addMemento(ModelMemento m) {
        stateList.push(m);
    }

    /**
     * popping memento from the stack.
     * @return ModelMemento indicating the last saved state.
     */
    public ModelMemento getMemento() {
        ModelMemento tempModel = stateList.pop();
        // makes all the AnimalModel threads run, activates the model.
        tempModel.getModel().restoreModelState();
        return tempModel;
    }

    /**
     * isEmpty
     * @return boolean value indicating if the stack is empty or not, means there are no stores states.
     */
    public boolean isEmpty(){
        return stateList.empty();
    }

    /**
     * isFull
     * @return boolean value indicating if the stack is full or not, means the stack can't store more states.
     * responsible for restoring the model state.
     */
    public boolean isFull(){
        return  stateList.size() == MAX_SIZE;
    }

    /**
     * ModelCaretaker clone method.
     * @return AnimalCaretaker cloned object.
     */
    @Override
    public ModelCaretaker clone() {
        ModelCaretaker clone = new ModelCaretaker();
        // initializes a new stack with the same mementos this model has.
        Stack<ModelMemento> tempStack = new Stack<>();
        for(ModelMemento m : this.stateList){
            tempStack.push(m);
        }
        clone.stateList = tempStack;
        return clone;
    }

}
