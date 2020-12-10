package com.pbz2.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Storege {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;

    @OneToMany(mappedBy = "storege")
    private List<Inventory> inventoryList;

    public Storege() {
    }

    public Storege(Long id, String name, String number, List<Inventory> inventoryList) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.inventoryList = inventoryList;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
