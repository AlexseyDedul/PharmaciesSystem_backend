package by.project.pharmases_system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class MedicineWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private double price;
    private String shelfLife;
    private String description;

    private String dosage;
    private int number;

    public MedicineWarehouse(Long id, String name, double price, String shelfLife, String description, String dosage, int number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shelfLife = shelfLife;
        this.description = description;
        this.dosage = dosage;
        this.number = number;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineWarehouse that = (MedicineWarehouse) o;
        return id == that.id && Double.compare(that.price, price) == 0 && number == that.number && Objects.equals(name, that.name) && Objects.equals(shelfLife, that.shelfLife) && Objects.equals(description, that.description) && Objects.equals(dosage, that.dosage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, shelfLife, description, dosage, number);
    }
}
