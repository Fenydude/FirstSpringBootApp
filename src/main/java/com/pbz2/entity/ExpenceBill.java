package com.pbz2.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ExpenceBill {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Date date;

    @OneToOne
    @JoinColumn(name = "exBill_id")
    private Inventory inventories;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Storege storege;

    @Transient
    private String idInv;

    @Transient
    private Integer amountInv;

    @Transient
    private Long stId;

    public Long getStId() {
        return stId;
    }

    public void setStId(Long stId) {
        this.stId = stId;
    }

    public Integer getAmountInv() {
        return amountInv;
    }

    public void setAmountInv(Integer amountInv) {
        this.amountInv = amountInv;
    }

    public String getIdInv() {
        return idInv;
    }

    public void setIdInv(String idInv) {
        this.idInv = idInv;
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

    public void setDate(String date) {
        System.out.println("Start " + date);
        String[] subStr;
        String delimeter = "-"; // Разделитель
        subStr = date.split(delimeter); // Разделения строки str с помощью метода split()
        System.out.println(subStr[0] + " " + subStr [1] + " " + subStr[2]);
        int year = Integer.parseInt (subStr[0]);
        int mounth = Integer.parseInt (subStr[1]);
        int day = Integer.parseInt (subStr[2]);
        this.date = new Date(year-1900,mounth-1,day);
        System.out.println(this.date);
    }



    public Storege getStorege() {
        return storege;
    }

    public void setStorege(Storege storege) {
        this.storege = storege;
    }

    public ExpenceBill() {
    }

    public ExpenceBill(Long id, Date date, Inventory inventories, Storege storege) {
        this.id = id;
        this.date = date;
        this.inventories = inventories;
        this.storege = storege;
    }

    public Inventory getInventories() {
        return inventories;
    }

    public void setInventories(Inventory inventories) {
        this.inventories = inventories;
    }
}
