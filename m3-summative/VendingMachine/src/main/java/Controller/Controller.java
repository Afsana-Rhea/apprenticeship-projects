/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PersistenceException;
import Service.InsufficientFundsException;
import Service.NoItemInventoryException;
import Service.Service;
import View.View;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            while (keepGoing == true) {
                int userChoice = myView.getMenuChoice();
                switch (userChoice) {
                    case 1:
                        this.getAllItems();
                        break;
                    case 2:
                        this.addMoney();
                        break;
                    case 3:
                        this.vendItem();
                        break;
                    case 4:
                        this.returnChange();
                        break;
                    case 5:
                        myView.exitMessage();
                        keepGoing = false;
                        break;
                    default:
                        myView.displayError("Invalid Choice");
                }

            }
        } catch (PersistenceException e) {
            myView.displayError(e.getMessage());
        }
    }

    private void getAllItems() throws PersistenceException {
        myView.displayItems(myService.getAllItems());

    }

    private void addMoney() throws PersistenceException {

        myService.addMoney(myView.addMoney());

    }

    private void vendItem() throws PersistenceException {
        try {
            if (myService.vendItem(myView.vendItem())) {
                myView.vendSuccess();
                myView.returnChange(myService.returnChange());
            }
        } catch (NoItemInventoryException ex) {
            myView.noItem();
        } catch (InsufficientFundsException ex) {
            myView.noMoney();
        }
    }

    private void returnChange() throws PersistenceException {
        myView.returnChange(myService.returnChange());

    }
}
