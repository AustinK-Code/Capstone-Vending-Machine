//fixed display for option 1
//made new subclass for snacks
//

package com.techelevator;

import com.techelevator.backend.Audit;
import com.techelevator.backend.SalesReport;
import com.techelevator.backend.VendingMachine;
import com.techelevator.view.MenuDrivenCLI;

import java.math.BigDecimal;
import java.util.List;

public class Application {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
    private static final String SUB_MENU_FEED_MONEY = "Feed Money";
    private static final String SUB_MENU_SELECT_PRODUCT = "Select Product";
    private static final String SUB_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String MONEY_IN_ONE_DOLLAR = "$1.00";
    private static final String MONEY_IN_FIVE_DOLLAR = "$5.00";
    private static final String MONEY_IN_TEN_DOLLAR = "$10.00";
    private static final String MONEY_IN_TWENTY_DOLLAR = "$20.00";
    private static final String MONEY_IN_DOGE_COIN = "one doge coin";
    private static final String[] MONEY_IN_MENU = {MONEY_IN_ONE_DOLLAR, MONEY_IN_FIVE_DOLLAR, MONEY_IN_TEN_DOLLAR, MONEY_IN_TWENTY_DOLLAR, MONEY_IN_DOGE_COIN};
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};
    private static final String[] PURCHASE_ITEMS_SUBMENU = {SUB_MENU_FEED_MONEY, SUB_MENU_SELECT_PRODUCT, SUB_MENU_FINISH_TRANSACTION};

    private final MenuDrivenCLI ui = new MenuDrivenCLI();
    private VendingMachine machine = new VendingMachine();
    private Audit auditLog = new Audit("","");
    private SalesReport salesReport = new SalesReport();

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    public void run() {
        auditLog.logReset();
        machine.importInventory();
        while (true) {

            String selection = ui.promptForSelection(MAIN_MENU_OPTIONS);
            // Display items in the vending machine
            if (selection.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                machine.displayVendingMachineItems();
            }
            // purchase
            else if (selection.equals(MAIN_MENU_OPTION_PURCHASE)) {
                ui.output(machine.balanceDisplay());
                String subMenuSelection = ui.promptForSelection(PURCHASE_ITEMS_SUBMENU);
                subMenuSelection.equals(PURCHASE_ITEMS_SUBMENU);
                {
                    //Feed money
                    if (subMenuSelection.equals(SUB_MENU_FEED_MONEY)) {
                        String feedMoneyMenuSelection = ui.promptForSelection(MONEY_IN_MENU);
                        feedMoneyMenuSelection.equals(MONEY_IN_MENU);
                        {
                            if (feedMoneyMenuSelection.equals(MONEY_IN_ONE_DOLLAR)) {
                                ui.output(machine.addDollars(new BigDecimal("1.00")));
                            } else if (feedMoneyMenuSelection.equals(MONEY_IN_FIVE_DOLLAR)) {
                                ui.output(machine.addDollars(new BigDecimal("5.00")));
                            } else if (feedMoneyMenuSelection.equals(MONEY_IN_TEN_DOLLAR)) {
                                ui.output(machine.addDollars(new BigDecimal("10.00")));
                            } else if (feedMoneyMenuSelection.equals(MONEY_IN_TWENTY_DOLLAR)) {
                                ui.output(machine.addDollars(new BigDecimal("20.00")));
                            } else if (feedMoneyMenuSelection.equals(MONEY_IN_DOGE_COIN)) {
                                machine.addDogeCoin();
                                System.exit(0);
                            }
                        }
                    }
                    //select products you want to purchase
                    else if (subMenuSelection.equals(SUB_MENU_SELECT_PRODUCT)) {
                        String input = ui.itemToPurchasePrompt();
                        ui.output(machine.purchaseItem(input));

                    }
                    //finalize transaction
                    else if (subMenuSelection.equals(SUB_MENU_FINISH_TRANSACTION)) {
                        ui.output(machine.displayChangeGiven(machine.getChangeAmount()));
                    }
                }

            }  // exit program
            else if (selection.equals(MAIN_MENU_OPTION_EXIT)) {
                System.exit(0);

            } else if (selection.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                    machine.displaySalesScreen();
            };
        }
    }
}
