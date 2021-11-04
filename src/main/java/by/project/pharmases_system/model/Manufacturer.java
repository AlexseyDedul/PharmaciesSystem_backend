package by.project.pharmases_system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "manufacturer_table")
public class Manufacturer implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String country;
    private String city;
    private String address;
    private String phoneNumb;

    public Manufacturer() {
    }

    public Manufacturer(String name, String country, String city, String address, String phoneNumb) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phoneNumb = phoneNumb;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(address, that.address) && Objects.equals(phoneNumb, that.phoneNumb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, city, address, phoneNumb);
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumb='" + phoneNumb + '\'' +
                '}';
    }
}
