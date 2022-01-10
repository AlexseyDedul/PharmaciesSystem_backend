package by.project.pharmases_system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "medicine_warehouse_table")
public class MedicineWarehouse implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "release_form_id")
    private ReleaseForm releaseForm;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacture;

    private double price;
    private String shelfLife;
    private String description;

    private String dosage;
    private int number;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    public MedicineWarehouse() {
    }

    public MedicineWarehouse(String name, ReleaseForm releaseForm, Manufacturer manufacture, double price, String shelfLife, String description, String dosage, int number, Disease disease) {
        this.name = name;
        this.releaseForm = releaseForm;
        this.manufacture = manufacture;
        this.price = price;
        this.shelfLife = shelfLife;
        this.description = description;
        this.dosage = dosage;
        this.number = number;
        this.disease = disease;
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

    public ReleaseForm getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(ReleaseForm releaseForm) {
        this.releaseForm = releaseForm;
    }

    public Manufacturer getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacturer manufacture) {
        this.manufacture = manufacture;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
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

    @Override
    public String toString() {
        return "MedicineWarehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", shelfLife='" + shelfLife + '\'' +
                ", description='" + description + '\'' +
                ", dosage='" + dosage + '\'' +
                ", number=" + number +
                '}';
    }
}
