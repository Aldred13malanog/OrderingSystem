/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ordering_system;

import javax.swing.*;

/**
 * Ordering_System1
 *
 * Basic JOptionPane-based ordering system.
 * - Keeps logic simple and easy to follow.
 * - Uses only basic Java language features (arrays, loops, methods).
 * - Messages are formal and website-like.
 *
 * Author: Refactored version for beginner-friendly structure
 */
public class OrderingSystemChatGPT {

    // === Configuration: menu items and prices ===
    // Desserts
    private static String[] dessertNames = {
        "Chocolate Cake", "Cheesecake", "Chocolate Chip Cookie", "Ice Cream (Vanilla)", "Brownie"
    };
    private static double[] dessertPrices = {
        120.00, 150.00, 40.00, 60.00, 80.00
    };

    // Snacks
    private static String[] snackNames = {
        "French Fries (Regular)", "Burger (Single)", "Chicken Nuggets (6 pcs)", "Onion Rings", "Hotdog"
    };
    private static double[] snackPrices = {
        70.00, 120.00, 95.00, 65.00, 55.00
    };

    // Drinks
    private static String[] drinkNames = {
        "Bottled Water", "Iced Tea (16 oz)", "Coffee (Hot)", "Soda (Can)", "Milkshake (Chocolate)"
    };
    private static double[] drinkPrices = {
        25.00, 45.00, 60.00, 40.00, 110.00
    };

    // === Order storage (parallel arrays) ===
    // For simplicity we use fixed-size arrays to store ordered item names and quantities.
    private static final int MAX_ORDER_ITEMS = 100;
    private static String[] orderedNames = new String[MAX_ORDER_ITEMS];
    private static double[] orderedUnitPrices = new double[MAX_ORDER_ITEMS];
    private static int[] orderedQuantities = new int[MAX_ORDER_ITEMS];
    private static int orderCount = 0; // how many distinct items have been ordered

    // === Registered user storage (simple single-user example) ===
    private static String registeredUser = null;
    private static String registeredPassword = null;

    public static void main(String[] args) {
        showWelcomeScreen();

        // registration then login flow
        boolean done = false;
        while (!done) {
            String[] options = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                null,
                "Please select an action to continue:",
                "Account Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == 0) {
                performRegistration();
            } else if (choice == 1) {
                boolean loggedIn = performLogin();
                if (loggedIn) {
                    // if login succeeds, show main menu and ordering loop
                    showMainMenu();
                }
            } else {
                // Exit
                int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to exit the application?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    done = true;
                    showGoodbye();
                }
            }
        }

        System.exit(0);
    }

    // === UI / flow methods ===

    private static void showWelcomeScreen() {
        String welcome = "Welcome to the Online Ordering System!\n\n"
                       + "This interface allows you to register an account, log in, and place an order.\n"
                       + "All prices are in Philippine Peso (PHP).";
        JOptionPane.showMessageDialog(null, welcome, "Welcome", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void performRegistration() {
        if (registeredUser != null) {
            JOptionPane.showMessageDialog(
                null,
                "An account already exists on this device.\nIf you wish to update credentials, please log in and use the account settings.",
                "Registration Unavailable",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        String username = JOptionPane.showInputDialog(null, "Create a username:", "Register", JOptionPane.PLAIN_MESSAGE);
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Registration was cancelled or invalid input provided.", "Registration Cancelled", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String password = JOptionPane.showInputDialog(null, "Create a password:", "Register", JOptionPane.PLAIN_MESSAGE);
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Registration was cancelled or invalid input provided.", "Registration Cancelled", JOptionPane.WARNING_MESSAGE);
            return;
        }

        registeredUser = username.trim();
        registeredPassword = password; // For this simple program we do not hash passwords.

        JOptionPane.showMessageDialog(
            null,
            "Registration successful!\nPlease log in using your newly created credentials.",
            "Registration Complete",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static boolean performLogin() {
        if (registeredUser == null) {
            JOptionPane.showMessageDialog(
                null,
                "No registered account found. Please register before attempting to log in.",
                "Login Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        String username = JOptionPane.showInputDialog(null, "Enter username:", "Login", JOptionPane.PLAIN_MESSAGE);
        if (username == null) {
            JOptionPane.showMessageDialog(null, "Login cancelled.", "Login", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        String password = JOptionPane.showInputDialog(null, "Enter password:", "Login", JOptionPane.PLAIN_MESSAGE);
        if (password == null) {
            JOptionPane.showMessageDialog(null, "Login cancelled.", "Login", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (registeredUser.equals(username.trim()) && registeredPassword.equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful. Welcome, " + registeredUser + "!", "Login", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private static void showMainMenu() {
        boolean exit = false;
        while (!exit) {
            String[] mainOptions = {"View Menu & Order", "View Current Order", "Checkout", "Logout"};
            int selection = JOptionPane.showOptionDialog(
                null,
                "Please select an option:",
                "Main Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                mainOptions,
                mainOptions[0]
            );

            if (selection == 0) {
                showMenuCategories();
            } else if (selection == 1) {
                displayCurrentOrder();
            } else if (selection == 2) {
                performCheckout();
            } else if (selection == 3 || selection == JOptionPane.CLOSED_OPTION) {
                // Logout -> clear order and return to login/registration screen
                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to log out and return to the login screen?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    clearOrder(); // clear saved order upon logout
                    JOptionPane.showMessageDialog(null, "You have been logged out.", "Logout", JOptionPane.INFORMATION_MESSAGE);
                    exit = true;
                }
            } else {
                // Fall back: close dialog
                exit = true;
            }
        }
    }

    private static void showMenuCategories() {
        String[] categories = {"Desserts", "Snacks", "Drinks", "Return to Main Menu"};
        int cat = JOptionPane.showOptionDialog(
            null,
            "Select a product category to browse:",
            "Product Categories",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            categories,
            categories[0]
        );

        if (cat == 0) {
            browseAndOrder(dessertNames, dessertPrices, "Desserts");
        } else if (cat == 1) {
            browseAndOrder(snackNames, snackPrices, "Snacks");
        } else if (cat == 2) {
            browseAndOrder(drinkNames, drinkPrices, "Drinks");
        } else {
            // return to main menu
        }
    }

    /**
     * Show items of a category, allow the user to choose an item and quantity, and add to order.
     *
     * @param names  array of item names
     * @param prices array of item prices
     * @param title  category title
     */
    private static void browseAndOrder(String[] names, double[] prices, String title) {
        boolean back = false;
        while (!back) {
            // build a display string of menu items
            StringBuilder sb = new StringBuilder();
            sb.append(title).append("\n\n");
            for (int i = 0; i < names.length; i++) {
                sb.append((i + 1)).append(". ").append(names[i]).append(" - PHP ").append(String.format("%.2f", prices[i])).append("\n");
            }
            sb.append("\nEnter the item number to add to your order, or press Cancel/Close to go back.");

            String input = JOptionPane.showInputDialog(null, sb.toString(), title, JOptionPane.PLAIN_MESSAGE);
            if (input == null) {
                back = true;
                continue;
            }

            input = input.trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No input detected. Please enter a valid item number.", "Input Error", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            int itemNumber = -1;
            try {
                itemNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a numeric item number.", "Input Error", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            if (itemNumber < 1 || itemNumber > names.length) {
                JOptionPane.showMessageDialog(null, "Item number out of range. Please choose a listed item.", "Input Error", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            // ask for quantity
            String qtyStr = JOptionPane.showInputDialog(null, "Enter quantity for " + names[itemNumber - 1] + ":", "Quantity", JOptionPane.PLAIN_MESSAGE);
            if (qtyStr == null) {
                // user cancelled quantity -> go back to category
                continue;
            }

            qtyStr = qtyStr.trim();
            int qty = 0;
            try {
                qty = Integer.parseInt(qtyStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a numeric value.", "Input Error", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            if (qty <= 0) {
                JOptionPane.showMessageDialog(null, "Quantity must be at least 1.", "Input Error", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            addToOrder(names[itemNumber - 1], prices[itemNumber - 1], qty);
            JOptionPane.showMessageDialog(null, "Item added to your order successfully.", "Order Update", JOptionPane.INFORMATION_MESSAGE);

            // Ask whether to continue ordering from same category
            int cont = JOptionPane.showConfirmDialog(null, "Would you like to add another item from this category?", "Continue Ordering", JOptionPane.YES_NO_OPTION);
            if (cont != JOptionPane.YES_OPTION) {
                back = true;
            }
        }
    }

    // Adds an item to the order; if already in order, increases quantity.
    private static void addToOrder(String name, double unitPrice, int quantity) {
        // check if item already exists in the order to combine quantities
        for (int i = 0; i < orderCount; i++) {
            if (orderedNames[i].equals(name)) {
                orderedQuantities[i] += quantity;
                return;
            }
        }

        // otherwise add as a new line item if capacity permits
        if (orderCount < MAX_ORDER_ITEMS) {
            orderedNames[orderCount] = name;
            orderedUnitPrices[orderCount] = unitPrice;
            orderedQuantities[orderCount] = quantity;
            orderCount++;
        } else {
            JOptionPane.showMessageDialog(null, "Order capacity reached. Cannot add more distinct items.", "Order Limit", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void displayCurrentOrder() {
        if (orderCount == 0) {
            JOptionPane.showMessageDialog(null, "Your order is currently empty.", "Current Order", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Current Order Summary\n\n");
        double subtotal = 0.0;
        for (int i = 0; i < orderCount; i++) {
            double lineTotal = orderedUnitPrices[i] * orderedQuantities[i];
            subtotal += lineTotal;
            sb.append(i + 1).append(". ").append(orderedNames[i])
              .append(" x").append(orderedQuantities[i])
              .append(" - PHP ").append(String.format("%.2f", lineTotal)).append("\n");
        }

        sb.append("\nSubtotal: PHP ").append(String.format("%.2f", subtotal)).append("\n");
        sb.append("\nWould you like to modify the order? (Yes = Modify, No = Return)");

        int modify = JOptionPane.showConfirmDialog(null, sb.toString(), "Current Order", JOptionPane.YES_NO_OPTION);
        if (modify == JOptionPane.YES_OPTION) {
            modifyOrderMenu();
        }
    }

    private static void modifyOrderMenu() {
        boolean done = false;
        while (!done) {
            String[] options = {"Change Quantity", "Remove Item", "Clear Entire Order", "Return"};
            int sel = JOptionPane.showOptionDialog(
                null,
                "Select a modification action:",
                "Modify Order",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );

            if (sel == 0) {
                changeQuantity();
            } else if (sel == 1) {
                removeItem();
            } else if (sel == 2) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the entire order?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    clearOrder();
                    JOptionPane.showMessageDialog(null, "Your order has been cleared.", "Order Cleared", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                done = true;
            }
        }
    }

    private static void changeQuantity() {
        if (orderCount == 0) {
            JOptionPane.showMessageDialog(null, "No items to modify.", "Modify Order", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Select the item number to change its quantity:\n\n");
        for (int i = 0; i < orderCount; i++) {
            sb.append(i + 1).append(". ").append(orderedNames[i]).append(" x").append(orderedQuantities[i]).append("\n");
        }

        String input = JOptionPane.showInputDialog(null, sb.toString(), "Change Quantity", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;

        int idx = -1;
        try {
            idx = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid selection.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (idx < 0 || idx >= orderCount) {
            JOptionPane.showMessageDialog(null, "Selection out of range.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String qtyStr = JOptionPane.showInputDialog(null, "Enter new quantity for " + orderedNames[idx] + ":", "New Quantity", JOptionPane.PLAIN_MESSAGE);
        if (qtyStr == null) return;

        int newQty = 0;
        try {
            newQty = Integer.parseInt(qtyStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity input.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (newQty <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity must be at least 1.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        orderedQuantities[idx] = newQty;
        JOptionPane.showMessageDialog(null, "Quantity updated successfully.", "Modify Order", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void removeItem() {
        if (orderCount == 0) {
            JOptionPane.showMessageDialog(null, "No items to remove.", "Modify Order", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Select the item number to remove:\n\n");
        for (int i = 0; i < orderCount; i++) {
            sb.append(i + 1).append(". ").append(orderedNames[i]).append(" x").append(orderedQuantities[i]).append("\n");
        }

        String input = JOptionPane.showInputDialog(null, sb.toString(), "Remove Item", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;

        int idx = -1;
        try {
            idx = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid selection.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (idx < 0 || idx >= orderCount) {
            JOptionPane.showMessageDialog(null, "Selection out of range.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // shift items down to remove the selected index
        for (int i = idx; i < orderCount - 1; i++) {
            orderedNames[i] = orderedNames[i + 1];
            orderedUnitPrices[i] = orderedUnitPrices[i + 1];
            orderedQuantities[i] = orderedQuantities[i + 1];
        }
        // clear last
        orderedNames[orderCount - 1] = null;
        orderedUnitPrices[orderCount - 1] = 0.0;
        orderedQuantities[orderCount - 1] = 0;
        orderCount--;

        JOptionPane.showMessageDialog(null, "Item removed from the order.", "Modify Order", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void clearOrder() {
        for (int i = 0; i < orderCount; i++) {
            orderedNames[i] = null;
            orderedUnitPrices[i] = 0.0;
            orderedQuantities[i] = 0;
        }
        orderCount = 0;
    }

    private static void performCheckout() {
        if (orderCount == 0) {
            JOptionPane.showMessageDialog(null, "Your order is empty. Add items before checking out.", "Checkout", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Build order summary
        StringBuilder sb = new StringBuilder();
        sb.append("Order Summary\n\n");
        double subtotal = 0.0;
        for (int i = 0; i < orderCount; i++) {
            double lineTotal = orderedUnitPrices[i] * orderedQuantities[i];
            subtotal += lineTotal;
            sb.append(orderedNames[i]).append(" x").append(orderedQuantities[i])
              .append(" - PHP ").append(String.format("%.2f", lineTotal)).append("\n");
        }

        // Example: simple tax and service calculation
        double taxRate = 0.12; // 12% VAT (example)
        double taxAmount = subtotal * taxRate;
        double total = subtotal + taxAmount;

        sb.append("\nSubtotal: PHP ").append(String.format("%.2f", subtotal));
        sb.append("\nVAT (12%): PHP ").append(String.format("%.2f", taxAmount));
        sb.append("\nTotal Amount Due: PHP ").append(String.format("%.2f", total));
        sb.append("\n\nProceed to payment?");

        int confirm = JOptionPane.showConfirmDialog(null, sb.toString(), "Checkout", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Checkout cancelled. You may continue editing your order.", "Checkout", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Payment simulation: choose payment type and request minimal info
        String[] paymentOptions = {"Cash on Pickup", "Credit/Debit Card", "Cancel"};
        int p = JOptionPane.showOptionDialog(
            null,
            "Select a payment method:",
            "Payment",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            paymentOptions,
            paymentOptions[0]
        );

        if (p == 0) {
            // Cash on Pickup
            JOptionPane.showMessageDialog(null, "You selected Cash on Pickup. Please bring exact amount upon pickup.", "Payment", JOptionPane.INFORMATION_MESSAGE);
            completeOrder(total);
        } else if (p == 1) {
            // Card - simple simulation of card entry
            String cardNumber = JOptionPane.showInputDialog(null, "Enter card number (simulation):", "Card Payment", JOptionPane.PLAIN_MESSAGE);
            if (cardNumber == null || cardNumber.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Card payment cancelled.", "Payment", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // In a real system, validate and process the payment via a secure gateway.
            JOptionPane.showMessageDialog(null, "Card processed (simulation). Payment successful.", "Payment", JOptionPane.INFORMATION_MESSAGE);
            completeOrder(total);
        } else {
            JOptionPane.showMessageDialog(null, "Payment cancelled. Returning to main menu.", "Payment", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }

    private static void completeOrder(double amountPaid) {
        String receipt = "Order Confirmed\n\n"
                       + "Thank you for your purchase.\n"
                       + "Amount Paid: PHP " + String.format("%.2f", amountPaid) + "\n"
                       + "Your order will be prepared for pickup shortly.\n\n"
                       + "Have a pleasant day!";
        JOptionPane.showMessageDialog(null, receipt, "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);

        // After checkout we clear the order
        clearOrder();
    }

    private static void showGoodbye() {
        JOptionPane.showMessageDialog(null, "Thank you for using the Online Ordering System. Goodbye!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
    }
}
