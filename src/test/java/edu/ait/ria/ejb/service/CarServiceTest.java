package edu.ait.ria.ejb.service;

import edu.ait.ria.ejb.jpa.CarDao;
import edu.ait.ria.ejb.model.Car;
import edu.ait.ria.ejb.model.Engine;
import edu.ait.ria.ejb.utils.CarUtils;
import edu.ait.ria.rest.RestApplication;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.List;

import static edu.ait.ria.ejb.utils.CarUtils.createCar;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(Arquillian.class)
public class CarServiceTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap
                .create(JavaArchive.class, "ria-app.jar")
                .addClasses(Car.class, CarDao.class,
                        CarService.class, Engine.class, CarUtils.class, RestApplication.class)
                //	.addPackage(EventCause.class.getPackage())
                //	.addPackage(EventCauseDAO.class.getPackage())
                //this line will pick up the production db
                .addAsManifestResource("META-INF/persistence.xml",
                        "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @EJB
    private CarService carService;


    @Test
    public void testCreateCar() {
        String model = "TEST_CREATE_CAR";
        Car car = createCar(model);
        carService.addCar(car);
        List<Car> testCars = carService.getCarsByFieldValue("model", model);
        assertTrue(testCars.size() == 1);
        assertEquals(testCars.get(0), car);
        carService.deleteCar(testCars.get(0).getId());
    }

    @Test
    public void testDeleteCar() {
        String model = "TEST_DELETE_CAR";
        Car car = createCar(model);
        carService.addCar(car);
        List<Car> testCars = carService.getCarsByFieldValue("model", model);
        assertTrue(testCars.size() == 1);
        assertEquals(testCars.get(0), car);
        carService.deleteCar(testCars.get(0).getId());
        testCars = carService.getCarsByFieldValue("model", model);
        assertTrue(testCars.size() == 0);
    }

    @Test
    public void testGetCar() {
        String model = "TEST_GET_CAR";
        Car car = createCar(model);
        carService.addCar(car);
        List<Car> testCars = carService.getCarsByFieldValue("model", model);
        assertTrue(testCars.size() == 1);
        assertEquals(testCars.get(0), car);
        Car getCar = carService.getCar(testCars.get(0).getId());
        assertEquals(getCar, testCars.get(0));
        carService.deleteCar(testCars.get(0).getId());
        testCars = carService.getCarsByFieldValue("model", model);
        assertTrue(testCars.size() == 0);
    }

    @Test
    public void testGetCars() {
        String model = "TEST_GET_CAR";
        String model1 = "TEST_GET_CAR1";
        Car car = createCar(model);
        Car car1 = createCar(model1);
        carService.addCar(car);
        carService.addCar(car1);
        List<Car> testCars = carService.getCars();
        assertTrue(testCars.size() > 0);
        Car foundCar = null;
        Car foundCar1 = null;
        for(Car c : testCars){
            if(c.getModel().equals(model)){
                foundCar = c;
            }
            if(c.getModel().equals(model1)){
                foundCar1 = c;
            }
        }
        assertNotNull(foundCar);
        assertNotNull(foundCar1);
        carService.deleteCar(foundCar.getId());
        carService.deleteCar(foundCar1.getId());
    }
}
