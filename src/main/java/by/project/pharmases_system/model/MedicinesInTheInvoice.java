package by.project.pharmases_system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "medicines_in_the_invoice_table")
public class MedicinesInTheInvoice implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "medicine_warehouse_id")
    private MedicineWarehouse medicineWarehouse;
    private int number;

    public MedicinesInTheInvoice(Invoice invoice, MedicineWarehouse medicineWarehouse, int number) {
        this.invoice = invoice;
        this.medicineWarehouse = medicineWarehouse;
        this.number = number;
    }

    public MedicinesInTheInvoice(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setMedicineWarehouse(MedicineWarehouse medicineWarehouse) {
        this.medicineWarehouse = medicineWarehouse;
    }

    public MedicineWarehouse getMedicineWarehouse() {
        return medicineWarehouse;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicinesInTheInvoice that = (MedicinesInTheInvoice) o;
        return id == that.id && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return "MedicinesInTheInvoice{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
