package by.project.pharmases_system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "disease_table")
public class Disease implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String treatment;

    public Disease() {
    }

    public Disease(String name, String treatment) {
        this.name = name;
        this.treatment = treatment;
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

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disease disease = (Disease) o;
        return id == disease.id && Objects.equals(name, disease.name) && Objects.equals(treatment, disease.treatment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, treatment);
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }
}
