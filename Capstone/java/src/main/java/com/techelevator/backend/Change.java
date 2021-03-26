package com.techelevator.backend;

public class Change {
    private int nickel;
    private int dime;
    private int quarter;

    public Change(int quarter, int dime, int nickel){
        this.quarter = quarter;
        this.dime = dime;
        this.nickel = nickel;
    }

    public int getNickel() {
        return nickel;
    }

    public int getDime() {
        return dime;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setNickel(int nickel) {
        this.nickel = nickel;
    }

    public void setDine(int dine) {
        this.dime = dine;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

}
