package edu.ait.ria.ejb.jpa;

import edu.ait.ria.ejb.model.Car;
import edu.ait.ria.ejb.model.Engine;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class CarDao {
    @PersistenceContext
    private EntityManager em;

    public Car getCar(long id) {
        return em.find(Car.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCar(Car car) {
        em.persist(car.getEngine());
        em.persist(car);
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCar(Car car) {
        Car carUpdated = em.find(Car.class, car.getId());
        carUpdated.setModel(car.getModel());
        carUpdated.setDoors(car.getDoors());
        carUpdated.setYear(car.getYear());
        carUpdated.setBody(car.getBody());
        Engine engine = em.find(Engine.class, car.getEngine().getId());
        engine.setCylinders(car.getEngine().getCylinders());
        engine.setFuel(car.getEngine().getFuel());
        engine.setSize(car.getEngine().getSize());
        engine.setTransmission(car.getEngine().getTransmission());
    }

    public void deleteCar(long id) {
        Car car = getCar(id);
        em.remove(car);
    }

    public List<Car> getCars() {
        List<Car> listPersons = em.createQuery(
                "SELECT p FROM Car p").getResultList();
        return listPersons;
    }

    public List<Car> getCarsByFieldValue(String field, String value) {
        try {
            String param;
            if(field.contains(".")){
                param = field.substring(0, field.indexOf("."));
            }else {
                param = field;
            }
            List<Car> listPersons;
            if(!isStringInteger(value)) {
                listPersons = em.createQuery(
                        "SELECT p FROM Car p where p." + field + " = :" + param).setParameter(param, value).getResultList();
            }else {
                listPersons = em.createQuery(
                        "SELECT p FROM Car p where p." + field + " = :" + param).setParameter(param, Integer.parseInt(value)).getResultList();
            }
            return listPersons;
        }catch (Exception e){
            //todo log an error
            return new ArrayList<Car>();
        }

    }

    private boolean isStringInteger(String number ){
        try{
            Integer.parseInt(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }


}
