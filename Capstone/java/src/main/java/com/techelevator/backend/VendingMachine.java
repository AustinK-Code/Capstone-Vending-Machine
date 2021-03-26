package com.techelevator.backend;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class VendingMachine {
    public BigDecimal balanceInMachine = BigDecimal.valueOf(0.00);
    private final Map<String, Snack> inventory = new TreeMap<>();
    private BigDecimal receipt = new BigDecimal("0.00");
    private BigDecimal totalSales = BigDecimal.valueOf(0.00);


    public Map<String, Snack> getInventory() {
        return inventory;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void importInventory() {
        Path source = Path.of("inventory.txt");
        try (Scanner input = new Scanner(source)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineItem = line.split("\\|");
                BigDecimal itemPrice = new BigDecimal(lineItem[2]);
                Snack snackInMachine = new Snack(lineItem[0], lineItem[1], itemPrice, lineItem[3], 5);
                inventory.put(lineItem[0], snackInMachine);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void displayVendingMachineItems() {

        for (Map.Entry<String, Snack> entry : inventory.entrySet()) {
            if (entry.getValue().getNumberOfItems() < 1) {
                System.out.println("SOLD OUT");
            } else
                System.out.println(entry.getValue().getName() + " -- " + entry.getValue().getIdentifier() + " -- " + entry.getValue().getPrice() + " -- " + entry.getValue().getNumberOfItems());

        }
    }
    public void displaySalesScreen(){
        BigDecimal salesTotal = new BigDecimal("0.00");
        for(Map.Entry<String, Snack> entry : inventory.entrySet()){
            String salesRepo = entry.getValue().getName() + " | " + entry.getValue().getNumberOfSales();
            System.out.println(salesRepo);
        }
        System.out.println("\n**TOTAL SALES**" + " | " + totalSales);
    }

    public String addDollars(BigDecimal amount) {
        balanceInMachine = balanceInMachine.add(amount);
        Audit audit = new Audit(amount.toString(), balanceInMachine.toString());
        audit.auditWriter(audit.printFeedOutput());
        return balanceDisplay();
    }

    public void addDogeCoin() {
        System.out.println("The Vendo-Matic 600 explodes. You hear someone whisper \"whoof\" in your ear but there is nobody there");
    }

    public String purchaseItem(String input) {
        String output = "";
        if (inventory.get(input).getNumberOfItems() == 0) {
            output = itemIsSoldOut();
        } else if (inventory.get(input).getPrice().compareTo(balanceInMachine) > 0) {
            output = needMoreMoneyError();
        } else if (inventory.get(input).getPrice().compareTo(balanceInMachine) <= 0) {
            int numOfItems = inventory.get(input).getNumberOfItems();
            //subtract one inventory item
            inventory.get(input).setNumberOfItems(numOfItems - 1);
            //subtract money from machine for purchase;
            balanceInMachine = balanceInMachine.subtract(inventory.get(input).getPrice());
            //prints sound
            output = inventory.get(input).getSound(inventory.get(input).getType());
        }
        //add 1 to number of sales
        inventory.get(input).setNumberOfSales(inventory.get(input).getNumberOfSales()+1);
        //add to total gross sales
        totalSales = inventory.get(input).getPrice().add(totalSales);
        //sets up and makes an audit
        Audit purchaseAudit = new Audit(inventory.get(input).getName(), input,
                inventory.get(input).getPrice().toString(), balanceInMachine.toString());
        purchaseAudit.auditWriter(purchaseAudit.printPurchaseOutput());
        return output;
    }

    public String itemIsSoldOut() {
        return "Item is SOLD OUT";
    }

    public String needMoreMoneyError() {
        return "You must deposit money into machine.";
    }

    public String balanceDisplay() {
        return "You have $ " + balanceInMachine + " in the machine.";
    }

    public Change getChangeAmount() {

        BigDecimal[] first = balanceInMachine.divideAndRemainder(new BigDecimal("0.25"));
        int quarters = first[0].intValue();
        BigDecimal[] second = first[1].divideAndRemainder(new BigDecimal("0.10"));
        int dimes = second[0].intValue();
        BigDecimal[] third = second[1].divideAndRemainder(new BigDecimal("0.05"));
        int nickels = third[0].intValue();
        receipt = balanceInMachine;
        balanceInMachine = new BigDecimal("0.00");
        return new Change(quarters, dimes, nickels);
    }

    public String displayChangeGiven(Change change) {
        Audit audit = new Audit(changeAudit(change), balanceInMachine.toString());
        audit.auditWriter(audit.printChangeOutput());
        return "You receive: \n" + change.getQuarter() + " Quarters\n" + change.getDime() + " Dimes\n"
                + change.getNickel() + " Nickels\n" + receipt + " $ is your change.";
    }

    public String changeAudit(Change change) {
        return "Customer received: \n " + change.getQuarter() + " Quarters\n " + change.getDime() + " Dimes\n "
                + change.getNickel() + " Nickels\n ";
    }

}