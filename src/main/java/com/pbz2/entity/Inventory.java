package com.pbz2.entity;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    private Integer amount;

    @ManyToOne(targetEntity = Storege.class)
    private Storege storege;

    public Storege getStorege() {
        return storege;
    }

    public void setStorege(Storege storege) {
        this.storege = storege;
    }

    public Inventory(){

    }
    public Inventory(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
