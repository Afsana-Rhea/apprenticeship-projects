/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.vendingmachine;

import Controller.Controller;
import Dao.AuditDao;
import Dao.AuditDaoFileImpl;
import Dao.ItemDao;
import Dao.ItemDaoFileImpl;
import Dao.PersistenceException;
import Service.Service;
import Service.ServiceImpl;
import View.View;

/**
 *
 * @author afsanamiji
 */
public class App {
    public static void main(String[] args) throws PersistenceException {
    ItemDao myItemDao = new ItemDaoFileImpl();
    AuditDao myAuditDao = new AuditDaoFileImpl();
    Service myService = new ServiceImpl(myItemDao, myAuditDao);
    View myView = new View();
    Controller myController = new Controller(myService, myView);
    myController.run();
}
}