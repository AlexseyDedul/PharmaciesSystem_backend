package by.project.pharmases_system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String stateNumber;
    private String country;
    private String address;

    public Pharmacy(Long id, String name, String stateNumber, String country, String address) {
        this.id = id;
        this.name = name;
        this.stateNumber = stateNumber;
        this.country = country;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return id == pharmacy.id && Objects.equals(name, pharmacy.name) && Objects.equals(stateNumber, pharmacy.stateNumber) && Objects.equals(country, pharmacy.country) && Objects.equals(address, pharmacy.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stateNumber, country, address);
    }
}
