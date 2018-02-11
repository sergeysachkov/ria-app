package edu.ait.ria.ejb.jpa;

import edu.ait.ria.ejb.model.Car;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void addCars(Car car) {
        em.persist(car.getEngine());
        em.persist(car);
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


}
