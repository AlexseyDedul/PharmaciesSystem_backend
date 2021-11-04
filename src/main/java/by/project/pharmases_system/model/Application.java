package by.project.pharmases_system.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "application_table")
public class Application implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String reason;
    private double allPrice;

    public Application() {
    }

    public Application(Date date, String reason, double allPrice) {
        this.date = date;
        this.reason = reason;
        this.allPrice = allPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return id == that.id && Double.compare(that.allPrice, allPrice) == 0 && Objects.equals(date, that.date) && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, reason, allPrice);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", date=" + date +
                ", reason='" + reason + '\'' +
                ", allPrice=" + allPrice +
                '}';
    }
}
