package by.project.pharmases_system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "invoice_table")
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reason;
    private double fullPrice;

    public Invoice(String reason, double fullPrice) {
        this.reason = reason;
        this.fullPrice = fullPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && Double.compare(invoice.fullPrice, fullPrice) == 0 && Objects.equals(reason, invoice.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reason, fullPrice);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", fullPrice=" + fullPrice +
                '}';
    }
}
