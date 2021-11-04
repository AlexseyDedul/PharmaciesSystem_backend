package by.project.pharmases_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pharmacy_table")
@JsonIgnoreProperties(value = {"userSet"}, allowSetters = true)
public class Pharmacy implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String stateNumber;
    private String country;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pharmacy_id")
    @JsonProperty("userSet")
    private Set<User> userSet;

    public Pharmacy() {
    }

    public Pharmacy(String name, String stateNumber, String country, String address) {
        this.name = name;
        this.stateNumber = stateNumber;
        this.country = country;
        this.address = address;
    }

    public Long getId() {
        return id;
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

    public Set<User> getUserSet() {
        return userSet;
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

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stateNumber='" + stateNumber + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
