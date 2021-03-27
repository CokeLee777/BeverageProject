package com.example.BeYerage.shop;

public class Beverage {

    private String location;
    private String name;
    private BottleType bottleType;

    public Beverage(String location, String name, BottleType bottleType) {
        this.location = location;
        this.name = name;
        this.bottleType = bottleType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BottleType getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleType bottleType) {
        this.bottleType = bottleType;
    }
}
