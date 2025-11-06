/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ordering_system;
import javax.swing.*;
/**
 *
 * @author Aldred
 */
public class Ordering_System {

    public static void main(String[] args) {
        boolean isRunning = true;
        
        String[] dessertItems = {
            "Ice Cream",
            "Donut",
            "Cookies",
            "Mango Float",
            "Cake"
        };
        int[] dessertPrices = {
            20,
            15,
            10,
            25,
            15
        };
        String[] snacksItems = {
            "Nova",
            "Bingo",
            "Wafer",
            "Rebisco",
            "Cloud9"
        };
        int[] snacksPrices = {
            18,
            8,
            5,
            7,
            10
        };
        String[] drinksItems = {
            "Ice Tea",
            "Coke",
            "Orange Juice",
            "Coconut Juice",
            "Coffee"
        };
        int[] drinksPrices = {
            20,
            15,
            10,
            10,
            10
        };

        boolean mainMenu = true;

        int choice;
        int categoryChoice;
        int dessertChoice;
        int snacksChoice;
        int drinksChoice;
        
        boolean isRegistrationRunning = true;
        boolean isLoginRunning = false;
        
        int i = 0;
        String fullNameList[] = new String[10];
        String userNameList[] = new String[10];
        String passwordList[] = new String[10];
        String contactNumberList[] = new String[10];
        String addressList[] = new String[10];
        
        int in = 0;
        String items[] = new String[15];
        int prices[] = new int[15];
        int quantity[] = new int[15];
        String viewCartMessage = "Item(s)      Quantity        Total\n";
        String searchItem = "";
        String deleteItem = "";
        String paymentType = "";
        
        while (isRunning) {
            //REGISTRATION
            while (isRegistrationRunning) {
                //CREATE ACC?
                String fullNameInp = "";
                String userNameInp = "";
                String passwordInp = "";
                String confirmPasswordInp = "";
                String contactInp = "";
                String addressInp = "";
                
                int registration = JOptionPane.showConfirmDialog(null, "Create Account?",
                    "Registration", JOptionPane.YES_NO_OPTION);

                if (registration == -1) {
                    int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Program Terminated");
                        return;
                    } else {
                        continue;
                    }
                }

                if (registration != JOptionPane.YES_NO_OPTION) {
                    isRegistrationRunning = false;
                    isLoginRunning = true;
                    break;
                }

                //FULLNAME INPUT
                do {
                    fullNameInp = JOptionPane.showInputDialog(null, "Enter your Full Name", "Registration", JOptionPane.QUESTION_MESSAGE);
                    checkInput(fullNameInp, "Full Name");
                    fullNameList[i] = fullNameInp;
                } while (fullNameInp == null || fullNameInp.trim().isEmpty());

                //USERNAME INPUT
                do {
                    userNameInp = JOptionPane.showInputDialog("Create Username");
                    checkInput(userNameInp, "Username");
                    userNameList[i] = userNameInp;
                } while (userNameInp == null || userNameInp.trim().isEmpty());

                //PASS INPUT
                do {
                    passwordInp = JOptionPane.showInputDialog(null,"Create Password", "Registration", JOptionPane.QUESTION_MESSAGE);
                    checkInput(passwordInp, "Password");
                } while (passwordInp == null || passwordInp.trim().isEmpty());

                //CONFIRM PASS INPUT
                do {
                    confirmPasswordInp = JOptionPane.showInputDialog(null, "Confirm Password", "Registration", JOptionPane.QUESTION_MESSAGE);
                    checkInput(confirmPasswordInp, "Confirm Password");
                    if (!passwordInp.equals(confirmPasswordInp)) {
                        JOptionPane.showMessageDialog(null, "Password does not match!"
                                ,"Invalid Password", JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                    passwordList[i] = confirmPasswordInp;
    //                        
                } while (confirmPasswordInp == null || confirmPasswordInp.trim().isEmpty() || !passwordInp.equals(confirmPasswordInp));

//              CONTACT INPUT
                do {
                    contactInp = JOptionPane.showInputDialog(null,"Enter your Contact Number", "Registration", JOptionPane.QUESTION_MESSAGE);
                    checkInput(contactInp, "Contact");
                    contactNumberList[i] = contactInp;                    
                } while (contactInp == null || contactInp.trim().isEmpty());

                // ADDRESS INPUT
                do {
                    addressInp = JOptionPane.showInputDialog(null, "Enter your Address", "Registration", JOptionPane.QUESTION_MESSAGE);
                    checkInput(addressInp, "Address");
                    addressList[i] = addressInp;
                } while (addressInp == null || addressInp.trim().isEmpty());
                
                JOptionPane.showMessageDialog(null, "Registered Successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                isLoginRunning = true;
                isRegistrationRunning = false;
            }
            
            
            //LOGIN
            while (isLoginRunning) {
                // USERNAME INPUT
                String loginUserInp = "";
                String loginPassInp = "";
                
                do {
                    loginUserInp = JOptionPane.showInputDialog("Enter Username:");

                    if (loginUserInp == null) {
                        int confirm = JOptionPane.showConfirmDialog(null,
                            "Don't have an account?", "Create?", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_NO_OPTION) {
//                            JOptionPane.showMessageDialog(null, "Program Terminated");
                            isLoginRunning = false;
                            isRegistrationRunning = true;
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (loginUserInp.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Username cannot be empty"
                            ,"Invalid Input", JOptionPane.WARNING_MESSAGE);
                        continue;
                    }

                    if (!loginUserInp.equals(userNameList[i])) {
                        JOptionPane.showMessageDialog(null, "Account doesn\'t exist",
                            "Invalid Account", JOptionPane.INFORMATION_MESSAGE);
                        isLoginRunning = false;
                        isRegistrationRunning = true;
                        break;
                    }

                } while (loginUserInp == null || loginUserInp.trim().isEmpty() || !loginUserInp.equals(userNameList[i]));

                if (!isLoginRunning) {
                    break;
                }
                // PASSWORD INPUT
                do {
                    loginPassInp = JOptionPane.showInputDialog("Enter Password:");
                    checkInput(loginPassInp, "Password");
                    if (!loginPassInp.equals(passwordList[i])) {
                        JOptionPane.showMessageDialog(null, "Incorrect Password! Please try again.",
                            "Invalid Password", JOptionPane.ERROR_MESSAGE);
                    }

                } while (loginPassInp == null || loginPassInp.trim().isEmpty() || !loginPassInp.equals(passwordList[i]));
                
                JOptionPane.showMessageDialog(null, "Login Successfully!", "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
                isLoginRunning = false;
                mainMenu = true;
            }
            
            String mainMessage = "Welcome " + userNameList[i] + "!\n" 
                +"Ordering Management System!\n"
                +"1 - Add item\n"
                +"2 - View Item\n"
                +"3 - Update Item\n"
                +"4 - Delete Item\n"
                +"5 - Place an Order\n"
                +"6 - Logout\n"
                +"Enter your choice";
//            i++;
            
            String categoryMessage = "Select a Food Category\n"
                +"1 - Dessert\n"
                +"2 - Snacks\n"
                +"3 - Drinks\n"
                +"4 - Back";
            String dessertMessage = "Select a Dessert\n"
                +"1 - Ice Cream (Php 20)\n"
                +"2 - Donut (Php 15)\n"
                +"3 - Cookies (Php 10)\n"
                +"4 - Mango Float (Php 25)\n"
                +"5 - Cake (Php 15)\n"
                +"6 - Back";            
            String snackMessage= "Select a Snack\n"
                +"1 - Nova (Php 18)\n"
                +"2 - Bingo (Php 8)\n"
                +"3 - Wafer (Php 5)\n"
                +"4 - Rebisco (Php 7)\n"
                +"5 - Cloud9 (Php 10)\n"
                +"6 - Back";
            String drinkMessage = "Select a Drink\n"
                +"1 - Ice Tea (Php 20)\n"
                +"2 - Coke (Php 15)\n"
                +"3 - Orange Juice (Php 10)\n"
                +"4 - Coconut Juice (Php 10)\n"
                +"5 - Coffee (Php 10)\n"
                +"6 - Back";
  
            
            while (mainMenu) {
                try {
                    choice = Integer.parseInt(JOptionPane.showInputDialog(null, mainMessage, "Main Menu",
                        JOptionPane.INFORMATION_MESSAGE));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Please input valid number 1-6",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                
                
                switch (choice) {
                    //ADD ITEM
                    case 1:
                        try {
                            categoryChoice = Integer.parseInt(JOptionPane.showInputDialog(null, categoryMessage, "Main Menu",
                                JOptionPane.INFORMATION_MESSAGE));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null,
                                    "Please input valid number 1-4",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }

                        switch (categoryChoice) {
                            //Dessert choice
                            case 1:
                                try {
                                    dessertChoice = Integer.parseInt(JOptionPane.showInputDialog(null, dessertMessage, "Main Menu",
                                        JOptionPane.INFORMATION_MESSAGE));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null,
                                            "Please input valid number 1-6",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }


                                switch (dessertChoice) {
                                    case 1:
                                        items[in] = dessertItems[dessertChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = dessertPrices[dessertChoice - 1];
                                        in++;
                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 2:
                                        items[in] = dessertItems[dessertChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = dessertPrices[dessertChoice - 1];
                                        in++;
                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;    
                                    case 3:
                                        items[in] = dessertItems[dessertChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = dessertPrices[dessertChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 4:
                                        items[in] = dessertItems[dessertChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = dessertPrices[dessertChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 5:
                                        items[in] = dessertItems[dessertChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = dessertPrices[dessertChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 6:
                                        //BACK
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Invalid Choice. Please enter a valid choice of number",
                                            "Invalid", JOptionPane.ERROR_MESSAGE);
                                }
                                break;

                            //snacks choice
                            case 2:
                                try {
                                    snacksChoice = Integer.parseInt(JOptionPane.showInputDialog(null, snackMessage, "Main Menu",
                                        JOptionPane.INFORMATION_MESSAGE));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null,
                                            "Please input valid number 1-6",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }

                                switch (snacksChoice) {
                                    case 1:
                                        items[in] = snacksItems[snacksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = snacksPrices[snacksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 2:
                                        items[in] = snacksItems[snacksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = snacksPrices[snacksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;    
                                    case 3:
                                        items[in] = snacksItems[snacksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = snacksPrices[snacksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 4:
                                        items[in] = snacksItems[snacksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = snacksPrices[snacksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 5:
                                        items[in] = snacksItems[snacksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = snacksPrices[snacksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 6:
                                        //BACK
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Invalid Choice. Please enter a valid choice of number",
                                            "Invalid", JOptionPane.ERROR_MESSAGE);
                                }
                                break;

                            //drinks choice
                            case 3:
                                try {
                                    drinksChoice = Integer.parseInt(JOptionPane.showInputDialog(null, drinkMessage, "Main Menu",
                                        JOptionPane.INFORMATION_MESSAGE));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null,
                                            "Please input valid number 1-6",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }

                                switch (drinksChoice) {
                                    case 1:
                                        items[in] = drinksItems[drinksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = drinksPrices[drinksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 2:
                                        items[in] = drinksItems[drinksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = drinksPrices[drinksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;    
                                    case 3:
                                        items[in] = drinksItems[drinksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = drinksPrices[drinksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 4:
                                        items[in] = drinksItems[drinksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = drinksPrices[drinksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 5:
                                        items[in] = drinksItems[drinksChoice - 1];
                                        quantity[in] = checkQuantityInput();
                                        prices[in] = drinksPrices[drinksChoice - 1];
                                        in++;

                                        JOptionPane.showMessageDialog(null, "Item Added Successfully!",
                                            "Add item", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    case 6:
                                        //BACK
                                        break;
                                    default:
                                       JOptionPane.showMessageDialog(null, "Invalid Choice. Please enter a valid choice of number",
                                          "Invalid", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            case 4:
                                //BACK
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Invalid Choice. Please enter a valid choice of number",
                                "Invalid", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                        
                    case 2:
                        //VIEW CART
                        if (in == 0) {
                            JOptionPane.showMessageDialog(null, "No item added!", "Empty Cart", JOptionPane.INFORMATION_MESSAGE);
                        }
                        for (int j = 0; j < in; j++) {
                            if (!items[j].equals("")) {
                                JOptionPane.showMessageDialog(null, viewCartMessage +
                                        items[j] + "         " + quantity[j] + "          " + " Php"+(prices[j] * quantity[j]));
                            }
                        }
                        break;
                    case 3:
                        //VIEW AND UPDATE CART
                        if (in == 0) {
                            JOptionPane.showMessageDialog(null, "No item added!", "Empty Cart", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        for (int j = 0; j < in; j++) {
                            if (!items[j].equals("")) {
                                JOptionPane.showMessageDialog(null, viewCartMessage +
                                        items[j] + "         " + quantity[j] + "          " + " Php"+(prices[j] * quantity[j]));
                            }
                        }
                        searchItem = JOptionPane.showInputDialog("Enter item to update:");
                        for (int ind = 0; ind < in; ind++) {
                            if (items[ind].equalsIgnoreCase(searchItem)) {
                                try {
                                    quantity[ind] = Integer.parseInt(JOptionPane.showInputDialog("Enter New Quantity:"));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null,
                                            "Invalid Input!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }
                                
                                JOptionPane.showMessageDialog(null,
                                        "Item Updated Successfully!",
                                        "Update",JOptionPane.INFORMATION_MESSAGE);
                                break;
                                
                            }            
                        }
                        break;
                    case 4:
                        // DELETE ITEM IN CART
                        if (in == 0) {
                            JOptionPane.showMessageDialog(null, "No item added!", "Empty Cart", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        deleteItem = JOptionPane.showInputDialog("Enter Item to delete",
                                JOptionPane.QUESTION_MESSAGE);
                        for (int indx = 0; indx < in; indx++) {
                            if (items[i].equalsIgnoreCase(deleteItem)) {
                               items[i] = "";
                               prices[i] = 0;
                               quantity[i] = 0;
                               JOptionPane.showMessageDialog(null,
                                       "Item deleted Successfully!",
                                       "Deleted", JOptionPane.INFORMATION_MESSAGE);
                               break;
                            }
                        }
                        break;
                    case 5:
                        //PLACE ORDER
                        if (in == 0) {
                            JOptionPane.showMessageDialog(null, "No item added!", "Empty Cart", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        int paymentOpt;
                        try {
                            paymentOpt = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "Choose a payment option:\n"
                                +"1 - Cash on Delivery(COD)\n"
                                +"2 - Gcash\n"
                                +"3 - Back",
                                "Payment Option",
                                JOptionPane.QUESTION_MESSAGE));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null,
                                    "You need to choose a payment option!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        
                        int totalAmount = 0;
                        for (int j = 0; j < in; j++) {
                            totalAmount = totalAmount + (prices[j] * quantity[j]);
                        }
                        String orders = "";
                        for (int p = 0; p < in; p++) {
                            orders = orders + (items[p] + "         " + quantity[p] + "          " + " Php"+(prices[p] * quantity[p] + "\n"));
                        }
                        
                        switch (paymentOpt) {
                            case 1:
                                paymentType = "Cash on Delivery(COD)";
                                JOptionPane.showMessageDialog(null,
                                        "Name: " + fullNameList[i] + "\n"
                                        +"Contact#: " + contactNumberList[i] + "\n"
                                        +"Delivery Address: " + addressList[i] + "\n"
                                        +"Payment Type: " + paymentType + "\n"
                                        +"Total Amount: " + totalAmount + "\n"
                                        +"Orders:\n"
                                        + "\t" + viewCartMessage
                                        + "\t" + orders,
                                        "Order Details", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 2:
                                paymentType = "Gcash";
                                try {
                                    int gcashNum = Integer.parseInt(JOptionPane.showInputDialog("Enter GCash Number:"));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null,
                                            "You need to enter your GCash number!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }
                                try {
                                    int refNum = Integer.parseInt(JOptionPane.showInputDialog("Enter Reference Number:"));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null,
                                            "You need to enter the reference number!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }
                                JOptionPane.showMessageDialog(null, "Payment Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);                                
                                JOptionPane.showMessageDialog(null,
                                        "Name: " + fullNameList[i]+ "\n"
                                        +"Contact#: " + contactNumberList[i]+ "\n"
                                        +"Delivery Address: " + addressList[i]+ "\n"
                                        +"Payment Type: " + paymentType + "\n"
                                        +"Total Amount: Php" + totalAmount + "\n"
                                        +"Orders:\n"
                                        + "\t" + viewCartMessage
                                        + "\t" + orders,
                                        "Order Details", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 3: 
                                break;
                        }
                        
                        break;
                    case 6:
                        // LOGOUT
                        int backLogin = JOptionPane.showConfirmDialog(null,
                                "Do you wish to proceed to logout?",
                                "Confirm Logout",JOptionPane.YES_NO_OPTION);
                        if (backLogin == JOptionPane.YES_NO_OPTION) {
                            mainMenu = false;
                            isRegistrationRunning = true;
                            break;
                        }
                        break;
                    
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid Choice. Please enter a valid choice of number",
                                "Invalid", JOptionPane.ERROR_MESSAGE);
                }
                
                
            }
            

        }
    }
    static void checkInput(String input, String message) {
        if (input == null) {
            int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Program Terminated");
                System.exit(0);
            }
        }
        if (input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, message + " cannot be empty"
                ,"Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }
    static int checkQuantityInput() {
       String quantityInput = "";
       int quantity = 0;
       boolean isInvalid = true;
       while (isInvalid) {
            do {
                quantityInput = JOptionPane.showInputDialog("Enter Quantity:");
                if (quantityInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                         "Quantity cannot be empty!",
                         "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (quantityInput.trim().isEmpty());
            try {
                quantity = Integer.parseInt(quantityInput);
                isInvalid = false;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                    "Invalid Quantity!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
       }
       
       return quantity;
    }
}