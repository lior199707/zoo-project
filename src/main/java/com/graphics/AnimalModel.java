package com.graphics;

import com.animals.Animal;
import com.memento.animal.AnimalCaretaker;
import com.memento.animal.AnimalMemento;
import com.memento.animal.AnimalOriginator;
import com.observer.Controller;
import com.observer.Observer;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * AnimalModel represents the model used for the zoo.
 * it consists of array list with default set size.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class AnimalModel implements Cloneable {
    /**
     * ArrayList of animals.
     */
    private ArrayList<Animal> animals;
    /**
     * integer value representing the maximum size of the animal array.
     */
    private static final int MAX_SIZE = 10;
    /**
     * integer value representing the maximum size of the queue.
     */
    private static final int MAX_QUEUE_SIZE = 5;
    /**
     * boolean value indicating if the model is changed or not.
     */
    private boolean isChanged;
    /**
     * boolean value indicating the current model sleep state.
     */
    private boolean sleepState = false;



    /**
     * Observer object used as a controller, will be applied to all animals.
     */
    private Observer controller;

    /**
     * LinkedBlockingDeque
     */
    private LinkedBlockingQueue<Runnable> animalQueue;
    /**
     * ExecutorService is the ThreadPool service provider.
     */
    private ExecutorService pool;

    /**
     * AnimalCaretaker reference.
     */
    private AnimalCaretaker caretaker = new AnimalCaretaker();
    /**
     * AnimalOriginator reference.
     */
    private AnimalOriginator animalOriginator = new AnimalOriginator();

    /**
     * AnimalModel constructor.
     * initiating the array list and default state to false.
     */
    public AnimalModel(){
        pool = Executors.newFixedThreadPool(MAX_SIZE);
        animalQueue = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
        animals = new ArrayList<>();
        isChanged = false;
        controller = new Controller();
        animalOriginator.setModel(animals);
    }

    /**
     * AnimalModel copy constructor.
     * @param other the AnimalModel object to copy.
     */
    public AnimalModel(AnimalModel other){
        this.setSleepState(other.isAsleep());
        this.setChangesState(other.getChangesState());
        this.pool = Executors.newFixedThreadPool(MAX_SIZE);
        this.animalQueue = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
        LinkedBlockingQueue<Runnable> tempQueue = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
        this.controller = other.controller;
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : other.getAnimalModel()){
            animals.add(animal.clone());
        }
        for (Runnable animal : other.animalQueue) {
            tempQueue.add(((Animal) animal).clone());
        }
        this.animalQueue = tempQueue;
        this.animals = animals;
        this.setCaretaker(other.getCaretaker().clone());
        this.setOriginator(other.getOriginator().clone());
    }

    /**
     * AnimalModel clone method.
     * @return deep copy of AnimalModel.
     */
    @Override
    public AnimalModel clone() {
        return new AnimalModel(this);
    }

    /**
     * addAnimal will add an animal if the current only if the current size does not exceed the maximum.
     * @param animal Animal object to add.
     * @return boolean value indicating if the animal addition was successful or not.
     */
    public boolean addAnimal(Animal animal){
        boolean isSuccess = false;
        animal.setObserver(controller);
        if (animals.size() < MAX_SIZE){
            animal.setThreadSuspended(isAsleep());
            animals.add(animal);
            pool.execute(animal);
            isSuccess = true;
        } else {
            animal.setThreadSuspended(true);
            animalQueue.add(animal);
        }
        return isSuccess;
    }

    /**
     * pullFromQueue activates animals after waiting in waiting queue.
     * if there are animals in the queue and the model array size is lower than max size
     * an animal object is removed from the queue, added to the model and executed.
     */
    public void pullFromQueue(){
        if (animalQueue.size() > 0 && animals.size() < MAX_SIZE){
            Animal animal = (Animal) animalQueue.remove();
            animal.setThreadSuspended(isAsleep());
            animals.add(animal);
            pool.execute(animal);
        }
    }

    /**
     * Animal queue size getter.
     * @return integer value representation of the animalQueue size.
     */
    public int getAnimalQueueSize(){
        return animalQueue.size();
    }

    /**
     * Animal max queue size getter.
     * @return integer value representation of the animalQueue max size.
     */
    public static int getMaxQueueSize() {
        return MAX_QUEUE_SIZE;
    }

    /**
     * AnimalModel getter
     * @return ArrayList of Animals.
     */
    public ArrayList<Animal> getAnimalModel(){
        return animals;
    }

    /**
     * Model size getter.
     * @return integer representation of the animal model size.
     */
    public int getAnimalModelSize() {
        return animals.size();
    }

    /**
     * the sleep method, setting all animals in model to suspended state
     * sleep state of the model is set to true.
     */
    public void sleep(){
        for (Animal animal : animals){
            animal.setSuspended();
            sleepState = true;
        }
    }

    /**
     * sleepState setter.
     * @param state boolean value representing the sleepState.
     */
    public void setSleepState(boolean state){
        sleepState = state;
    }

    /**
     * Animal names getter
     * @return String array of the animal names of all the animals in the animal model.
     */
    public String[] getAnimalNames() {
        String[] names = new String[animals.size()];

        for (int i = 0; i < getAnimalModelSize(); i++){
            names[i] = animals.get(i).toString();
        }

        return names;
    }

    /**
     * the wakeUp method, setting all animals in model to resumed state
     * sleep state of the model is set to false.
     */
    public void wakeUp(){
        for (Animal animal : animals){
            animal.setResumed();
            animal.start();
        }
        sleepState = false;
    }

    /**
     * stopping all animal threads in the model.
     * allowing safe thread termination.
     */
    public void stopAll() {
        for (Animal animal : animals){
            animal.stop();
        }
        pool.shutdown();
    }

    /**
     * sleepState getter.
     * @return boolean representation of the sleepState, true if sleep is on false otherwise.
     */
    public boolean isAsleep() {
        return sleepState;
    }

    /**
     * containsAnimalName evaluates if the animal model contains a given name or not.
     * @param name String representation of the animal name.
     * @return boolean value indicating if the animal name is contained in the animal model or not.
     */
    public boolean containsAnimalName(String name){
        for (Animal animal: animals){
            if (animal.getName().equals(name))
                return true;
        }
        return false;
    }

    /**
     * MAX_SIZE getter
     * @return integer value representing the model maximum size.
     */
    public static int getModelMaxSize() {
        return MAX_SIZE;
    }

    /**
     * isChanged getter.
     * @return boolean value indicating the state of isChanged.
     */
    public boolean getChangesState(){
        return isChanged;
    }

    /**
     * isChanged setter.
     * @param state boolean value of the model state.
     */
    public void setChangesState(boolean state){
        isChanged = state;
    }


    /**
     * save the current model state by creating a memento and adding it to the caretaker memento stack.
     */
    public void saveModelState() {
        animalOriginator.setModel(animals);
        AnimalMemento animalMemento = animalOriginator.createMemento();
        caretaker.addMemento(animalMemento);
        System.out.println("Saving current state");
    }

    /**
     * restore last state from the caretaker memento stack.
     */
    public void restoreModelState() {
        this.stopAll(); // stopping all working threads in the model.
        AnimalMemento animalMemento = caretaker.getMemento();
        animalOriginator.setModel(animalMemento.getModel());
        animals = new ArrayList<>();
        pool = Executors.newFixedThreadPool(MAX_SIZE);
        for (int i = 0; i < animalMemento.getModel().size(); i++){
            this.addAnimal(animalMemento.getModel().get(i));
        }
        System.out.println("Restoring current state");
    }

    /**
     * Caretaker getter.
     * @return AnimalCaretaker object of this model.
     */
    public AnimalCaretaker getCaretaker() {
        return caretaker;
    }

    /**
     * Originator getter.
     * @return AnimalOriginator object of this model.
     */
    public AnimalOriginator getOriginator() {
        return animalOriginator;
    }

    /**
     * Caretaker setter.
     * @param caretaker AnimalCaretaker object to set for this model.
     */
    public void setCaretaker(AnimalCaretaker caretaker) {
        this.caretaker = caretaker;
    }

    /**
     * Originator setter.
     * @param animalOriginator AnimalOriginator object to set for this model.
     */
    public void setOriginator(AnimalOriginator animalOriginator) {
        this.animalOriginator = animalOriginator;
    }

}
