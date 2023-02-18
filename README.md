# Zoo Project

Manages a zoo using java GUI application, the user can add differnet animals to the zoo which move on the screen and can eat other animals, the user can give them food which will appear at the center of the screen and will cause the animals that eat said food to move towards it until it's eaten, The user can view information about the animals currently on the screen, the information is presented in a table that updates in real time.

## Description

* This project was made in the second semester of the degree's second year.

### Add Animal Button

The animals are divded into 3 categories:

1) Carnivore - can eat all other animals accept other carnivores
* Lion
  
2) Herbivore - can't eat other animals
* Turtle
* Giraffe
* Elephant

3) Omnivore (using Carnivore and Herbivore as delegators) - Can't eat carnivores
* Bear

Animals can be added to the screen using the add animal button: 

![image](https://user-images.githubusercontent.com/40609600/219538426-b5a049cb-4a94-45ec-9b43-73a971cd7e26.png)

The animals are created using an Abstract Factory design pattern.
there is a factory for each of the 3 animals types that spaws animals of said type and there is one factory that is responsible to creating the right factory for initializng the wanted animal.
For example here is the code of the Factory of Factories:

```java
public class FactoryProducer {
    /**
     * getFactory
     * @param factoryType String representation of the factory type.
     * @return instance of a Factory matching to the factoryType.
     */
    public IAnimalFactory getFactory(String factoryType) {
        switch (factoryType){
            case "Herbivore" -> {
                return new HerbivoreFactory();
            }
            case "Omnivore" -> {
                return new OmnivoreFactory();
            }
            case "Carnivore" -> {
                return new CarnivoreFactory();
            }
        }
        return null;
    }
}
```

And the code of the herbivores factory:

```java
public class HerbivoreFactory implements IAnimalFactory {
    /**
     * @param animalType String representation of the animal type ("lion","bear", etc..)
     * @param name       String value of the animal's name, should contain only letters.
     * @param size       Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed   Int value indicates animal's horizontal speed.
     * @param verSpeed   Int value indicates animal's vertical speed.
     * @param col        String representing animal's color, "BLUE", "RED", "NATURAL".
     * @return Animal object (who's Diet is Herbivore) of the wanted animalType.
     */
    @Override
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col) {
        switch (animalType) {
            case "Giraffe" -> { return new Giraffe(name, size, horSpeed, verSpeed, col); }
            case "Elephant" -> { return new Elephant(name, size, horSpeed, verSpeed, col); }
            case "Turtle" -> { return new Turtle(name, size, horSpeed, verSpeed, col); }
        }
        return null;
    }
}
```

After choosign the animal's type (in this case Herbivore) a window for selecting the animal will appear:

![image](https://user-images.githubusercontent.com/40609600/219540999-32e73413-e2df-4b47-85b1-fecc8b36f79a.png)

You can change the animal in the combobox on the top of the window.
changing the animal itlesf or it's color will effect the image(for example in the above picture we see a giraffe in a natural color).
The animals size will affect the animals ability to eat other animals.
The V_speed and H_speed represents the vertical and horizontal speed of the animal which it will move by.
Each animal is a thread and all the movement of the animals is managed by a thread pool and a blocking queue:

```java
    /**
     * LinkedBlockingDeque
     */
    private LinkedBlockingQueue<Runnable> animalQueue;
    /**
     * ExecutorService is the ThreadPool service provider.
     */
    private ExecutorService pool;
    
    pool = Executors.newFixedThreadPool(MAX_SIZE);
    animalQueue = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
```
There can be 10 animals at most on the screen but the waiting list can hold 5 more animals, so let's say you choose to add 15 animals the first 10 will appear and move on the screen and the other 5 will wait in the queue and when an animal gets eaten an animal from the queue will pop out and start moving on the screen.

### Sleep Button

![image](https://user-images.githubusercontent.com/40609600/219543627-bc9aeeea-c7c6-4984-a56d-a27d461520bf.png)

The sleep button stops all animals threads, meaning the animals will freeze on the screen.
 
 ### Wake Up Button
 
 ![image](https://user-images.githubusercontent.com/40609600/219543780-68f2a4a2-6eb9-4450-b844-604b7bf157f6.png)

Remove the animals from sleep mode and makes them move again.

### Clear All Button

Deletes all the animals from the screen and from the information table

### Food Button

![image](https://user-images.githubusercontent.com/40609600/219544178-305ef72d-a446-475a-85d9-064b05fbcaeb.png)

There are 3 food types: letuce, cabbage and meat.
After clicking the food button a pop up window appears

![image](https://user-images.githubusercontent.com/40609600/219898926-bed6e807-5303-46ef-b83f-1ee0acbb9343.png)

After selecting the desired food, the food will apear at the center of the panel and animals that can eat the selected food will start moving towards it untill the food is eaten.

![image](https://user-images.githubusercontent.com/40609600/219900428-75707f22-10b5-4935-8ea0-45a39fbe2401.png)

The food is implemented as a singelton, for example the cabbage class:

```java
public class Cabbage extends Plant {
    /**
     * Singleton Cabbage instance.
     */
    private static Cabbage instance = null;
    /**
     * Cabbage constructor.
     */
    private Cabbage() { }

    /**
     * getInstance
     * @return Cabbage object if there is no cabbage instance.
     */
    public static Cabbage getInstance() {
        if (instance == null)
            instance = new Cabbage();
        return instance;
    }
}
```

### Change Color Button

![image](https://user-images.githubusercontent.com/40609600/219899732-28512f96-4994-4139-ab57-44c77fbca493.png)

After pressing the change color button a panle with a cobobox containing all the animals on the screen will appear

![image](https://user-images.githubusercontent.com/40609600/219899834-6e4bb182-0f16-4fb8-a5d1-956bb18ca5ca.png)

The picture of the animal selected in the combobox will appear in the panle and the buttons Red, Green and Blue will change the animal's color in real time.

The color of the animals is implemented using decorator DP.

The interface:
```java
public interface IColorDecorator {
    /**
     * Color setter.
     * @param color String representation of a color to decorate the object with.
     */
    public void setColor(String color);
}
```

The abstract class:
```java
    public AnimalColorDecorator(Animal animal) {
        this.animal = animal;
    }

    /**
     * Animal getter.
     * @return Animal object.
     */
    public Animal getAnimal() {
        return this.animal;
    }

    /**
     * Color setter.
     * sets the animal color and reload the animal's image.
     * @param color String representation of the animal's new color.
     */
    @Override
    public void setColor(String color) {
        animal.setColor(color);
        animal.loadImages(animal.animalShortPathName());
    }

    /**
     * Animal decorator.
     * @return the decorated Animal object.
     */
    public abstract Animal decorateAnimal();
}
```

The actual classes (for example the red decorator)
```java
public class AnimalRedDecorator extends AnimalColorDecorator {

    /**
     * AnimalRedDecorator constructor.
     * @param animal the animal to decorate.
     */
    public AnimalRedDecorator(Animal animal) {
        super(animal);
    }

    /**
     * decorate the animal with the red color.
     * @return the Animal decorated object with the red color.
     */
    @Override
    public Animal decorateAnimal() {
        setColor("RED");
        return getAnimal();
    }
}
```

### Info Button

![image](https://user-images.githubusercontent.com/40609600/219900488-867fe3da-ee52-46cb-a3ef-fe1f61c48d6b.png)

Clicking on the info button will open a table that updates in real time containing details and data about the animals in the zoo

![image](https://user-images.githubusercontent.com/40609600/219900560-a0ef7ba3-fe0d-434c-a21d-767a09960e59.png)

### Background JMenu Button

![image](https://user-images.githubusercontent.com/40609600/219900816-ef38a78b-7e98-401c-b1d1-0e256d518e3f.png)

The background JMenu Item lets the user cange the background of the zoo, None is the default background, Green is a green background and Image is a savana image.

### States JMenu Button

![image](https://user-images.githubusercontent.com/40609600/219900955-8cd030c5-bb66-445c-9986-bf8fefbe3803.png)

Let's the user save and load previous models of the zoo. 
Clicking the "Save State" button will save all the animals in the zoo with all their attributes, the bacground of the panel, the food that was or was't on the screen and the state of the animals(sleep or awake).
Clicking the "Restore State" button will reload the last saved instane of the zoo (killing all current threads and wakes up all the saved threads)

Uses the Memento DP to achieve above behaviour. (uses Memento, Caretaker and originator)

* The code can be seen under src\main\java\com\memento.

## Usage

Clone the project or copy the files and open the project using intelliJ.
Make sure the JDK is set to Oracle openJDK version 17.
run the ZooFrame file (src\main\java\com\graphichs\ZooFrame).
Watch the description for a detailed explanation about the actions you can perform.


## Summary and DP

The main focus of the project was advanced object oriented programming, threads, parallel programming and synchronized in java.

The design patters in use are:

* Memento - for saving and loading states of the zoo.
* Decorator- for the animals' colors.
* Singelton - for the food calsses.
* Observer - for making checks regarding the animals, after an animal made a change (moved for example).
* ThreadPool - to manage all the animals(threads).
* Abstract Factory - for instantiating the animals.
