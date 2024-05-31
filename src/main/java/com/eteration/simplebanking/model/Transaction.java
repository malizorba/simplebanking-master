package com.eteration.simplebanking.model;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

// This class is a place holder you can change the complete implementation

@Entity
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date date;
    @Column
    private double amount;
    @Column
    private String approvalCode;


    public Transaction(double amount) {
        this();
        this.amount = amount;
        this.date = new Date();
        this.approvalCode = UUID.randomUUID().toString();


    }

    public Transaction() {

    }


    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalStatus) {
        this.approvalCode = approvalStatus;
    }


    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Date: " + date + ", Amount: " + amount;
    }

    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

}


