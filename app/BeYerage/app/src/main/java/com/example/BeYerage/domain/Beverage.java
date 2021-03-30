package com.example.BeYerage.domain;

public class Beverage {

    private Long id;
    private String name;
    private int price;
    private String type;
    private int size;

    public Beverage(Long id, String name, int price, String type, int size) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.size = size;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
