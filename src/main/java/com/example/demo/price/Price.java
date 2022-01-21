package com.example.demo.price;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private double lprice;
    private String curr1;
    private String curr2;

    private Date createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    public Price() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLprice() {
        return lprice;
    }

    public void setLprice(double lprice) {
        this.lprice = lprice;
    }

    public String getCurr1() {
        return curr1;
    }

    public void setCurr1(String curr1) {
        this.curr1 = curr1;
    }

    public String getCurr2() {
        return curr2;
    }

    public void setCurr2(String curr2) {
        this.curr2 = curr2;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", lprice=" + lprice +
                ", curr1='" + curr1 + '\'' +
                ", curr2='" + curr2 + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
