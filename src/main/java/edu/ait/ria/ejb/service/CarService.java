package edu.ait.ria.ejb.service;

import edu.ait.ria.ejb.jpa.CarDao;
import edu.ait.ria.ejb.model.Car;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cars")
@Stateless
@LocalBean
public class CarService {
    @EJB
    private CarDao carDao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCar(@PathParam("id") long id) {
        return carDao.getCar(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCars() {
        return carDao.getCars();
    }

    @GET
    @Path("/models/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCarsByModel(@PathParam("model") String model) {
        return carDao.getCarsByModel(model);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addCar(Car car) {
        carDao.addCars(car);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCar(@PathParam("id") long id) {
        carDao.deleteCar(id);
    }

}
