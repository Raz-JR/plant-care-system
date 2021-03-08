package com.jsip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    //@JsonProperty("Name")
    private String name;
    //@JsonProperty("Last Watered Date")
    private String lastWaterDate;
    //@JsonProperty("Watering frequency")
    private String wateringFrequency;

    //@Transient
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastWaterDate() {
        return lastWaterDate;
    }

    public void setLastWaterDate(String lastWaterDate) {
        this.lastWaterDate = lastWaterDate;
    }

    public String getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(String wateringFrequency) {
        this.wateringFrequency = wateringFrequency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
