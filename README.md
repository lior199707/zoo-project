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

4) Omnivore (using Carnivore and Herbivore as delegators) - Can't eat carnivores
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

The sleep button stops all animals threads, meaning the will freeze on the screen.
 
 ### Wake Up Button
 
 ![image](https://user-images.githubusercontent.com/40609600/219543780-68f2a4a2-6eb9-4450-b844-604b7bf157f6.png)

Remove the animals from sleep mode and makes them move again.

### Clear All Button

Deletes all the animals form the screen and from the information table

### Food Button

![image](https://user-images.githubusercontent.com/40609600/219544178-305ef72d-a446-475a-85d9-064b05fbcaeb.png)



