package edu.ait.ria.ejb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String size;
    private String fuel;
    private String transmission;
    private int cylinders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return id == engine.id &&
                cylinders == engine.cylinders &&
                Objects.equals(size, engine.size) &&
                Objects.equals(fuel, engine.fuel) &&
                Objects.equals(transmission, engine.transmission);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, size, fuel, transmission, cylinders);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", fuel='" + fuel + '\'' +
                ", transmission='" + transmission + '\'' +
                ", cylinders=" + cylinders +
                '}';
    }
}
