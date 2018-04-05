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

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car updateCar(@PathParam("id") long id, Car car) {
        if(car.getId() == 0){
            car.setId(id);
        }
        carDao.updateCar(car);
        return car;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCars() {
        return carDao.getCars();
    }

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCarsByFieldValue(@QueryParam("field") String field, @QueryParam("value") String value) {
        return carDao.getCarsByFieldValue(field, value);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Car addCar(Car car) {
       return carDao.addCar(car);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCar(@PathParam("id") long id) {
        carDao.deleteCar(id);
    }

}
