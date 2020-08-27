/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import sg.am.flooringmastery.dto.Order;
import sg.am.flooringmastery.service.InvalidDateException;

/**
 *
 * @author afsanamiji
 */
public class OrderDaoImplTest {

    ApplicationContext ctx;
    OrderDao instance;
    Map<LocalDate, HashMap<Integer, Order>> myDates = new HashMap<>();

    ;

    public OrderDaoImplTest() {
        this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.instance = ctx.getBean("myOrderDao", OrderDao.class);
    }

    @Before
    public void setUp() {
        this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.instance = ctx.getBean("myOrderDao", OrderDao.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String date = "11111111";
        LocalDate inputDate = LocalDate.parse(date, formatter);
        instance.load(inputDate);
        for (LocalDate myDate : myDates.keySet()) {
            for (int id : myDates.get(date).keySet()) {
                myDates.remove(id);
            }
        }

    }

    /**
     * Test of create method, of class OrderDaoImpl.
     */
    @Test
    public void testCreate() throws InvalidDateException {
        this.setUp();

        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Wise");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setProductType("Wood");
        order.setState("OH");
        order.setTax(new BigDecimal("61.88"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setTotal(new BigDecimal("1051.88"));
        HashMap<Integer, Order> myMap = new HashMap<>();
        myMap.put(order.getOrderNumber(), order);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String date = "11111111";
        LocalDate inputDate = LocalDate.parse(date, formatter);
        myDates.put(inputDate, myMap);
        //act
        instance.create(inputDate, order);

        int expSize = 1;

        int size = instance.readByDate(inputDate).size();

        //assert
        assertEquals(expSize, size);
    }

    /**
     * Test of readAll method, of class OrderDaoImpl.
     */
    @Test
    public void testReadAll() {
        this.setUp();
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Wise");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setProductType("Wood");
        order.setState("OH");
        order.setTax(new BigDecimal("61.88"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setTotal(new BigDecimal("1051.88"));
        HashMap<Integer, Order> myMap = new HashMap<>();
        myMap.put(order.getOrderNumber(), order);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String date = "11111111";
        LocalDate inputDate = LocalDate.parse(date, formatter);
        instance.load(inputDate);
        myDates.put(inputDate, myMap);
        //act
        instance.create(inputDate, order);
instance.save();
instance = this.instance = ctx.getBean("myOrderDao", OrderDao.class);
        Order newOrder = new Order();
        newOrder.setOrderNumber(2);
        newOrder.setCustomerName("Afsana");
        newOrder.setArea(new BigDecimal("100.00"));
        newOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        newOrder.setLaborCost(new BigDecimal("475.00"));
        newOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("515.00"));
        newOrder.setProductType("Wood");
        newOrder.setState("OH");
        newOrder.setTax(new BigDecimal("61.88"));
        newOrder.setTaxRate(new BigDecimal("6.25"));
        newOrder.setTotal(new BigDecimal("1051.88"));
        HashMap<Integer, Order> newMap = new HashMap<>();
        myMap.put(order.getOrderNumber(), order);
        myDates.put(inputDate, newMap);
        //act
        instance.create(inputDate, newOrder);

        //act
        int expSize = 2;
        //arrange
        int size = instance.readAll(inputDate).size();

        //assert
        assertEquals(expSize, size);
    }

    /**
     * Test of readByDate method, of class OrderDaoImpl.
     */
    @Test
    public void testReadByDate() throws Exception {
        this.setUp();
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Wise");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setProductType("Wood");
        order.setState("OH");
        order.setTax(new BigDecimal("61.88"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setTotal(new BigDecimal("1051.88"));
        HashMap<Integer, Order> myMap = new HashMap<>();
        myMap.put(order.getOrderNumber(), order);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String date = "11111111";
        LocalDate inputDate = LocalDate.parse(date, formatter);
        instance.load(inputDate);
        myDates.put(inputDate, myMap);
        //act
        instance.create(inputDate, order);

        int expSize = 1;
        int size = instance.readByDate(inputDate).size();

        //assert
        assertEquals(expSize, size);

    }

    /**
     * Test of readById method, of class OrderDaoImpl.
     */
    @Test
    public void testReadById() throws Exception {
        //arrange
        this.setUp();
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Wise");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.0"));
        order.setProductType("Wood");
        order.setState("OH");
        order.setTax(new BigDecimal("61.88"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setTotal(new BigDecimal("1051.88"));
        HashMap<Integer, Order> myMap = new HashMap<>();
        myMap.put(order.getOrderNumber(), order);
        myDates.put(LocalDate.now(), myMap);
        instance.create(LocalDate.now(), order);

        //act
        order = instance.create(LocalDate.now(), order);
        Order newOrder = instance.readById(LocalDate.now(), order.getOrderNumber());

        //
        assertEquals(order.getCustomerName(), newOrder.getCustomerName());
    }

    /**
     * Test of update method, of class OrderDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        this.setUp();

        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Wise");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setProductType("Wood");
        order.setState("OH");
        order.setTax(new BigDecimal("61.88"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setTotal(new BigDecimal("1051.88"));
        HashMap<Integer, Order> myMap = new HashMap<>();
        myMap.put(order.getOrderNumber(), order);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String date = "11111111";
        LocalDate inputDate = LocalDate.parse(date, formatter);
        myDates.put(inputDate, myMap);
        //act
        instance.create(inputDate, order);
        Order myOrder = new Order();
        myOrder.setOrderNumber(1);
        myOrder.setCustomerName("Wise");
        myOrder.setArea(new BigDecimal("100.00"));
        myOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        myOrder.setLaborCost(new BigDecimal("475.00"));
        myOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        myOrder.setMaterialCost(new BigDecimal("515.00"));
        myOrder.setProductType("Wood");
        myOrder.setState("OH");
        myOrder.setTax(new BigDecimal("61.88"));
        myOrder.setTaxRate(new BigDecimal("6.25"));
        myOrder.setTotal(new BigDecimal("1051.88"));
        //myMap.put(order.getOrderNumber(), order);
        //myDates.put(inputDate, myMap);
        instance.update(order, inputDate);
        
        int expSize = 1;
        int size = instance.readAll(inputDate).size();
    }

    /**
     * Test of delete method, of class OrderDaoImpl.
     * 
     */
    @Test
    public void testDelete() throws Exception {
        //act
        this.setUp();
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Wise");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setProductType("Wood");
        order.setState("OH");
        order.setTax(new BigDecimal("61.88"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setTotal(new BigDecimal("1051.88"));
        HashMap<Integer, Order> myMap = new HashMap<>();
        myMap.put(order.getOrderNumber(), order);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String date = "11111111";
        LocalDate inputDate = LocalDate.parse(date, formatter);
        myDates.put(inputDate, myMap);
        //act
        instance.create(inputDate, order);

        //arrange
        instance.delete(inputDate, 1);
        int expSize = 0;
        int size = instance.readAll(inputDate).size();

        //assert
        assertEquals(expSize, size);
    }

    

}
