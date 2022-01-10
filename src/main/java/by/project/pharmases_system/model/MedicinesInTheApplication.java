package by.project.pharmases_system.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "medicines_in_the_application_table")
public class MedicinesInTheApplication implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "medicine_warehouse_id")
    private MedicineWarehouse medicineWarehouse;
    private int number;

    public MedicinesInTheApplication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public MedicinesInTheApplication(Application application, MedicineWarehouse medicineWarehouse, int number) {
        this.application = application;
        this.medicineWarehouse = medicineWarehouse;
        this.number = number;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public MedicineWarehouse getMedicineWarehouse() {
        return medicineWarehouse;
    }

    public void setMedicineWarehouse(MedicineWarehouse medicineWarehouse) {
        this.medicineWarehouse = medicineWarehouse;
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
        MedicinesInTheApplication that = (MedicinesInTheApplication) o;
        return id == that.id && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return "MedicinesInTheApplication{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
