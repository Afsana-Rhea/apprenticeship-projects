/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.service;

import java.time.LocalDate;
import java.util.List;
import sg.am.flooringmastery.dao.PersistenceException;
import sg.am.flooringmastery.dto.Order;

/**
 *
 * @author afsanamiji
 */
public interface Service {

    public List<Order> getAllOrders(LocalDate date) throws InvalidDateException, OrderNotFoundException;

    public Order addAnOrder(Order order) throws PersistenceException, InvalidProductException, InvalidStateException, InvalidNameException;

    public Order getOrder(LocalDate date, int orderNum) throws PersistenceException, OrderNotFoundException;

    public Order editOrder(LocalDate date, Order order) throws PersistenceException, InvalidProductException, InvalidStateException;

    public boolean deleteOrder(Order order, LocalDate date) throws InvalidDateException, PersistenceException;

    public void saveOrder(boolean saveOrder) throws UnsupportedServiceException;
    public Order validateOrder(Order order) throws InvalidNameException, InvalidProductException, InvalidStateException;

}
