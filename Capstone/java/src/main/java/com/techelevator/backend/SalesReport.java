package com.techelevator.backend;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class SalesReport extends Audit {

    public SalesReport(){

    }

    @Override
    public void auditWriter(String toWrite) {
        try (FileOutputStream stream = new FileOutputStream("SalesReport.txt", true);
             PrintWriter writer = new PrintWriter(stream)) {
            writer.println(toWrite);
        } catch (Exception e) {
            System.out.println("Failed to make an Audit log");
        }
    }
    public void salesReportGet(VendingMachine machine){
        for (String key: machine.getInventory().keySet()){
            String toWrite = machine.getInventory().get(key).getName() +
                    " | " + machine.getInventory().get(key).getNumberOfSales();
            auditWriter(toWrite);
        }
        String end = "**TOTAL SALES** \n" + machine.getTotalSales().toString();
        auditWriter(end);
    }

}
