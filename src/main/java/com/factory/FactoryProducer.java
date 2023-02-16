package com.factory;

/**
 * Factory Producer is used to fetch a factory.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
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
