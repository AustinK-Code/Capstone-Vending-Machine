package com.techelevator.backend;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Audit {
    private String dateTime;
    private String money;
    private String machineBalance;
    private String itemNamePurchase;
    private String itemSlotPurchase;
    private String itemPricePurchase;
    private String remainingBalancePurchase;

    public Audit(String money, String machineBalance) {
        this.money = money;
        this.machineBalance = machineBalance;
        setDateTimeFeed();
    }

    public Audit(String itemNamePurchase, String itemSlotPurchase, String itemPricePurchase, String remainingBalancePurchase) {
        this.itemNamePurchase = itemNamePurchase;
        this.itemSlotPurchase = itemSlotPurchase;
        this.itemPricePurchase = itemPricePurchase;
        this.remainingBalancePurchase = remainingBalancePurchase;
        setDateTimeFeed();
    }

    public Audit(){
    }

    public void setDateTimeFeed() {
        dateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public String printFeedOutput() {
        return "**FEED MONEY** \n" + dateTime + "\n Money in:" + money + "\n Machine balance: " + machineBalance + "\n\n";
    }

    public String printPurchaseOutput() {
        return "**PURCHASE** \n" + dateTime + "\n Item: " + itemNamePurchase + "\n Item slot: " + itemSlotPurchase + "\n Item price: "
                + itemPricePurchase + "\n Machine balance: " + remainingBalancePurchase + "\n\n";
    }

    public String printChangeOutput() {
        return "**GIVE CHANGE** \n" + dateTime + "\n " + money + "Machine balance: " + machineBalance + "\n\n";
    }

    public void auditWriter(String toWrite) {
        try (FileOutputStream stream = new FileOutputStream("Audit.txt", true);
             PrintWriter writer = new PrintWriter(stream)) {
            writer.println(toWrite);
        } catch (Exception e) {
            System.out.println("Failed to make an Audit log");
        }
    }

    public void logReset() {
        try (PrintWriter writer = new PrintWriter("Audit.txt")) {
            writer.println("**************AUDIT-LOG****************");
            try (PrintWriter writer1 = new PrintWriter("SalesReport.txt")) {
                writer1.println("**************SALES-REPORT****************");
            } catch (Exception e) {
                System.out.println("Failed to make Sales Report file");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to make an Audit log");
        }
    }
}

