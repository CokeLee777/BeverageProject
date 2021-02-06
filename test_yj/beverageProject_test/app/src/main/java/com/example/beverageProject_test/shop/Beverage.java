package com.example.beverageProject_test.shop;

public class Beverage {

    private String location;
    private String name;
    private com.example.beverageProject_test.shop.BottleType bottleType;

    public Beverage(String location, String name, com.example.beverageProject_test.shop.BottleType bottleType) {
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

    public com.example.beverageProject_test.shop.BottleType getBottleType() {
        return bottleType;
    }

    public void setBottleType(com.example.beverageProject_test.shop.BottleType bottleType) {
        this.bottleType = bottleType;
    }
}
