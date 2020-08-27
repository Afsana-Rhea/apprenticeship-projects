/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmaster.controller;

import sg.am.flooringmastery.dao.PersistenceException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.am.flooringmastery.dto.Order;
import sg.am.flooringmastery.service.InvalidAreaException;
import sg.am.flooringmastery.service.InvalidDateException;
import sg.am.flooringmastery.service.InvalidNameException;
import sg.am.flooringmastery.service.InvalidProductException;
import sg.am.flooringmastery.service.InvalidStateException;
import sg.am.flooringmastery.service.OrderNotFoundException;
import sg.am.flooringmastery.service.Service;
import sg.am.flooringmastery.service.UnsupportedServiceException;
import sg.am.flooringmastery.view.View;

/**
 *
 * @author afsanamiji
 */
public class Controller {

    private Service myService;
    private View myView;

    public Controller(Service myService, View myView) {
        this.myService = myService;
        this.myView = myView;
    }

    public void run() throws PersistenceException {
        boolean keepGoing = true;
        while (keepGoing == true) {

            int userChoice = myView.getMenuChoice();
            switch (userChoice) {
                case 1:
                    this.displayOrders();
                    break;
                case 2:
                    this.addOrder();
                    break;
                case 3:
                    this.editOrder();
                    break;
                case 4:
                    this.removeOrder();
                    break;
                case 5:
                    this.saveOrder();
                    break;
                case 6:
                    this.saveOrder();
                    myView.exitMessage();
                    keepGoing = false;
                    break;
                case 7:
                default:
                    myView.displayError("Invalid Choice");

            }
        }
    }

    private void displayOrders() {
        try {
            myView.displayListOrder(myService.getAllOrders(myView.getDate()));
        } catch (InvalidDateException | OrderNotFoundException e) {
            myView.displayError(e.getMessage());
        }

    }

    private void addOrder() throws PersistenceException {
        try {
            Order order = myService.validateOrder(myView.addOrder());
            myView.displayOrder(order);
            if(myView.saveOrder()){
                myService.addAnOrder(order);
            }

        } catch (InvalidProductException | InvalidStateException | InvalidNameException ex) {
            myView.displayError(ex.getMessage());
        }
    }

    private void editOrder() throws PersistenceException {

        try {
            LocalDate date = myView.getDate();
            myView.displayListOrder(myService.getAllOrders(date));
            Order order = myService.getOrder(date, myView.getID());
            order = myService.validateOrder(myView.editOrder(order));
            if(myView.saveOrder()){
                myView.displayOrder(myService.editOrder(date, order));
            }
            
        } catch (InvalidProductException | InvalidStateException | OrderNotFoundException | InvalidDateException | InvalidNameException ex) {
            myView.displayError(ex.getMessage());
        }

    }

    private void removeOrder() throws PersistenceException {
        try {
            LocalDate date = myView.getDate();
            myView.displayListOrder(myService.getAllOrders(date));
            Order order = myService.getOrder(date, myView.getID());
            myView.displayOrder(order);

            if (myView.deleteOrder()) {
                if (myService.deleteOrder(order, date)) {
                    myView.displayMessage("Delete Success: " + order.getCustomerName() + " " + order.getOrderNumber());
                }
            }

        } catch (InvalidDateException | OrderNotFoundException e) {
            myView.displayError(e.getMessage());
        }
    }

    private void saveOrder() {
        try {
            boolean saveOrder = myView.saveOrder();
            myService.saveOrder(saveOrder);
            if (saveOrder) {
                myView.displayMessage("Order(s) Save Success!");
            } else {
                myView.displayMessage("Order(s) were not saved!");
            }
        } catch (UnsupportedServiceException e) {
            myView.displayError(e.getMessage());
        }
    }
}
