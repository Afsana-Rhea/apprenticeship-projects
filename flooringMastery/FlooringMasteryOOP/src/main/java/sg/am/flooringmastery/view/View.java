/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import sg.am.flooringmastery.dto.Order;
import sg.am.flooringmastery.service.InvalidDateException;

/**
 *
 * @author afsanamiji
 */
public class View {

    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int getMenuChoice() {
        io.print("===================== Main Menu =================");
        io.print("Please pick an option.");
        return io.readInt("1. Display All Orders\n"
                + "2. Add an Order\n"
                + "3. Edit an Order\n"
                + "4. Remove an Order\n"
                + "5. Save Work\n"
                + "6. Exit");
    }

    public void displayError(String message) {
        io.print(message);
    }

    public void exitMessage() {
        io.print("Goodbye");
    }

    public void displayMessage(String message) {
        io.print(message);
    }

    public LocalDate getDate() throws InvalidDateException {
        String date = io.readString("What date was the order taken? MMDDYYYY");
        LocalDate localDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
            localDate = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new InvalidDateException("Not a valid date");
        }
        return localDate;
    }

    public void displayOrder(Order order) {
        //make it nice 
        io.print("Customer Name: " + order.getCustomerName());
        io.print("Order Number: " + order.getOrderNumber() + "");
        io.print("Product Type: " + order.getProductType());
        io.print("Order Cost Total: " + order.getTotal().toString());
    }

    public Order addOrder() {
        io.print("==================== Create an Order ============");
        Order order = new Order();
        order.setCustomerName(io.readString("What is Customer's Name?"));
        order.setProductType(io.readString("Which flooring type would you like to order? \n1) Carpet\n 2) Laminate\n 3) Tile\n 4) Wood"));
        order.setState(io.readString("What is the state? \n1) OH \n2) PA \n3) MI \n4) IN"));
        String area= io.readString("What is the area?");
        if (Integer.parseInt(area) < 100){
            area = io.readString("What is the area? Please enter an area larger than 100 sq ft");
        }else {
            order.setArea(new BigDecimal(area));
              
            }
         return order;

    }

    public void displayListOrder(List<Order> allOrders) {

        for (Order order : allOrders) {

            displayOrder(order);
        }

    }

    public boolean saveOrder() {
        return io.readBoolean("Would you like to commit? y/n");
    }

    public int getID() {
        int date = 0;

        try {
            date = io.readInt("What is your order number?");
        } catch (Exception e) {
            io.print("Not a number");
        }
        return date;
    }

    public Order editOrder(Order order) {

        io.print("==================== Now Editing Order Number " + order.getOrderNumber() + " ============");
        String custName = io.readString("What is Customer's Name?");
        if (custName == null || custName.length() == 0) {
            order.setCustomerName(order.getCustomerName());
        } else {
            order.setCustomerName(custName);
        }

        String productType = io.readString("Which flooring type would you like to order? \n1) Carpet \n2) Laminate \n3) Tile \n4) Wood");
        if (productType == null || custName.length() == 0) {
            order.setProductType(order.getProductType());
        } else {
            order.setProductType(productType);
        }

        String stateType = io.readString("What is the state?");
        if (stateType == null || custName.length() == 0) {
            order.setState(order.getState());
        } else {
            order.setState(stateType);
        }
        BigDecimal area = new BigDecimal("0.00");
        String areaString = io.readString("What is the area?");
        if (areaString == null || areaString.length() == 0) {
            order.setArea(order.getArea());

        } else if(Integer.parseInt(areaString) < 100){
            areaString = io.readString("What is the area? Please enter an area larger than 100 sq ft");
        }else {
            area = new BigDecimal(areaString);
            order.setArea(area);
               
            }
        
        return order;
    }

    public boolean deleteOrder() {
        String delete = io.readString("Are you sure you would like to delete? 1) yes 2) no");
        boolean answer = false;
        switch (Integer.parseInt(delete)) {
            case 1:
                answer = true;
                break;
            case 2:
                answer = false;
                break;
            case 3:
            default:
                delete = io.readString("Please enter 1 to delete or 2 to exit. 1) yes 2) no");
                break;

        }
        return answer;
    }
}
