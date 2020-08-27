/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import java.time.LocalDate;
import java.util.List;
import sg.am.flooringmastery.dto.Order;
import sg.am.flooringmastery.service.InvalidDateException;

/**
 *
 * @author afsanamiji
 */
public interface OrderDao {
     Order create(LocalDate date, Order order);
    List<Order> readAll(LocalDate date);
    List<Order> readByDate(LocalDate date)throws InvalidDateException;
    Order readById(LocalDate date, int OrderNum)throws PersistenceException;
    void update(Order order, LocalDate date)throws PersistenceException;
    void delete(LocalDate date, int orderNum)throws PersistenceException;
    void save();
    void load(LocalDate date);
    
}
