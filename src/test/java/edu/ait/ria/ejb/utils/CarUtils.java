package edu.ait.ria.ejb.utils;

import edu.ait.ria.ejb.model.Car;
import edu.ait.ria.ejb.model.Engine;

public class CarUtils {

    public static Car createCar(String model) {
        Car car = new Car();
        car.setModel(model);
        car.setBody("SUV");
        car.setDoors(5);
        car.setYear(2018);
        Engine engine = new Engine();
        engine.setCylinders(4);
        engine.setFuel("Petrol");
        engine.setSize("1.8");
        engine.setTransmission("Manual");
        car.setEngine(engine);
        return car;
    }
}
