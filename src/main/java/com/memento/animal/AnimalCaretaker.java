package com.memento.animal;

import java.util.Stack;

/**
 * AnimalCaretaker contains a stack of AnimalMemento's.
 * it can push or pop AnimalMemento's into the stack.
 * used to save animal states during the software's lifecycle.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class AnimalCaretaker implements Cloneable {
    /**
     * Stack of AnimalMemento's indicating the amount of saved states.
     * responsible for restoring the model state.
     */
    private Stack<AnimalMemento> stateList = new Stack<>();
    /**
     * integer value representing the maximum size of the stack - the amount of saved states.
     */
    private static final int MAX_SIZE = 3;

    /**
     * pushing mementos to the stack.
     * @param m AnimalMemento object to push.
     */
    public void addMemento(AnimalMemento m) {
        stateList.push(m);
    }

    /**
     * popping memento from the stack.
     * @return AnimalMemento indicating the last saved state.
     */
    public AnimalMemento getMemento() {
        return stateList.pop();
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
     */
    public boolean isFull(){
        return  stateList.size() == MAX_SIZE;
    }

    /**
     * AnimalCaretaker clone method.
     * @return AnimalCaretaker cloned object.
     */
    @Override
    public AnimalCaretaker clone() {
        AnimalCaretaker clone = new AnimalCaretaker();
        Stack<AnimalMemento> tempStack = new Stack<>();
        // initializes a new stack with the same mementos this model has.
        for(AnimalMemento m : this.stateList){
            tempStack.push(m);
        }
        clone.stateList = tempStack;
        return clone;
    }

}
