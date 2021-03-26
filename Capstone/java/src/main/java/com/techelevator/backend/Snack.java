package com.techelevator.backend;

import java.math.BigDecimal;

public class Snack {

    private String identifier;
    private String name;
    private BigDecimal price;
    private String type;
    private int numberOfItems;
    private int numberOfSales;


    public Snack(String identifier, String name, BigDecimal price, String type, int numberOfItems) {
        this.identifier = identifier;
        this.name = name;
        this.price = price;
        this.type = type;
        this.numberOfItems = numberOfItems;
    }


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public String getSound(String type){
        if(type.equals("Chip")){
           return "Crunch Crunch, Yum!";
        }
        else if(type.equals("Drink")){
            return "Glug Glug, Yum!";
        }
        else if(type.equals("Gum")){
            return "Chew Chew, Yum!";
        }
        else if(type.equals("Candy")){
            return "Munch Munch, Yum!";
        }
        return null;
    }
}
