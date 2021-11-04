package by.project.pharmases_system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "medicines_in_the_application_table")
public class MedicinesInTheApplication implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int number;

    public MedicinesInTheApplication() {
    }

    public MedicinesInTheApplication(int number) {
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
