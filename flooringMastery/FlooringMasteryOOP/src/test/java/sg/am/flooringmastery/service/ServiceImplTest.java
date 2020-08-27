/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.am.flooringmastery.dao.IdDao;
import sg.am.flooringmastery.dao.OrderDao;
import sg.am.flooringmastery.dao.ProductDao;
import sg.am.flooringmastery.dao.TaxesDao;
import sg.am.flooringmastery.dto.Order;

/**
 *
 * @author afsanamiji
 */
public class ServiceImplTest {

    ApplicationContext ctx;
    Service instance;
    OrderDao daoInstance;

    public ServiceImplTest() {
        this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.instance = ctx.getBean("myService", Service.class);
        this.daoInstance = ctx.getBean("myOrderDao", OrderDao.class);
    }

    @Before
    public void setUp() {
        this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.instance = ctx.getBean("myService", Service.class);

    }

    @Test
    public void testGetAllOrders() throws Exception {
        setUp();
        //act
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String date = "11111111";
        LocalDate inputDate = LocalDate.parse(date, formatter);
        int size = instance.getAllOrders(inputDate).size();
        
        //arrange
        int expSize = 0;
        
        //assert
        
        assertEquals(size, expSize);
    }

    /**
     * Test of addAnOrder method, of class ServiceImpl.
     */
    @Test
    public void testAddAnOrder() throws Exception {
        this.setUp();
        
        //arrange
        Order expOrder = new Order();
        expOrder.setOrderNumber(1);
        expOrder.setCustomerName("Wise");
        expOrder.setArea(new BigDecimal("1000.00"));
        expOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        expOrder.setLaborCost(new BigDecimal("4750.00"));
        expOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        expOrder.setMaterialCost(new BigDecimal("5150.00"));
        expOrder.setProductType("Wood");
        expOrder.setState("OH");
        expOrder.setTax(new BigDecimal("618.75"));
        expOrder.setTaxRate(new BigDecimal("6.25"));
        expOrder.setTotal(new BigDecimal("10518.75"));
        
        //act
        
        //Order order = instance.getOrder(LocalDate.now(), 1);
        Order myOrder = instance.addAnOrder(expOrder);
        
        //assert
       // assertTrue(myOrder.equals(expOrder));
        assertEquals(expOrder.getTotal(), myOrder.getTotal());
        

    }

    /**
     * Test of getOrder method, of class ServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrder() throws Exception {
        setUp();
        

        //arrange
        Order expOrder = new Order();
        expOrder.setOrderNumber(1);
        expOrder.setCustomerName("Wise");
        expOrder.setArea(new BigDecimal("100.0"));
        expOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        expOrder.setLaborCost(new BigDecimal("475.00"));
        expOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        expOrder.setMaterialCost(new BigDecimal("515.0"));
        expOrder.setProductType("Wood");
        expOrder.setState("OH");
        expOrder.setTax(new BigDecimal("61.88"));
        expOrder.setTaxRate(new BigDecimal("6.25"));
        expOrder.setTotal(new BigDecimal("1051.88"));
       Order newOrder = instance.addAnOrder(expOrder);
        
        
        assertEquals(expOrder.getCustomerName(), newOrder.getCustomerName());
        assertEquals(expOrder.getOrderNumber(), newOrder.getOrderNumber());
        assertEquals(expOrder.getArea(), newOrder.getArea());
        assertEquals(expOrder.getCostPerSquareFoot(), newOrder.getCostPerSquareFoot());
        assertEquals(expOrder.getLaborCost(), newOrder.getLaborCost());
        assertEquals(expOrder.getLaborCostPerSquareFoot(), newOrder.getLaborCostPerSquareFoot());
        assertEquals(expOrder.getMaterialCost(), newOrder.getMaterialCost());
        assertEquals(expOrder.getProductType(), newOrder.getProductType());
        assertEquals(expOrder.getState(), newOrder.getState());
        assertEquals(expOrder.getTotal(), newOrder.getTotal());
    }

    /**
     * Test of editOrder method, of class ServiceImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        
        this.setUp();
        
        //arrange
        Order expOrder = new Order();
        expOrder.setOrderNumber(1);
        expOrder.setCustomerName("Wise");
        expOrder.setArea(new BigDecimal("1000.00"));
        expOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        expOrder.setLaborCost(new BigDecimal("4750.00"));
        expOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        expOrder.setMaterialCost(new BigDecimal("5150.00"));
        expOrder.setProductType("Wood");
        expOrder.setState("OH");
        expOrder.setTax(new BigDecimal("618.75"));
        expOrder.setTaxRate(new BigDecimal("6.25"));
        expOrder.setTotal(new BigDecimal("10518.75"));
        
        //act
        
        //Order order = instance.getOrder(LocalDate.now(), 1);
        Order myOrder = instance.editOrder(LocalDate.now(), expOrder);
        
        //assert
       // assertTrue(myOrder.equals(expOrder));
        assertEquals(expOrder.getTotal(), myOrder.getTotal());
    }

    /**
     * Test of deleteOrder method, of class ServiceImpl.
     */
    @Test
    public void testDeleteOrder() throws Exception {
        setUp();
        //act
       Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Wise");
        order.setArea(new BigDecimal("1000.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCost(new BigDecimal("4750.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("5150.00"));
        order.setProductType("Wood");
        order.setState("OH");
        order.setTax(new BigDecimal("618.75"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setTotal(new BigDecimal("10518.75"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String date = "11111111";
        LocalDate inputDate = LocalDate.parse(date, formatter);
       daoInstance.create(inputDate, instance.addAnOrder(order));
       
       instance.deleteOrder(order, inputDate);
       instance.getAllOrders(inputDate);
       
       //arrange
       int expSize = 0;
       
       //assert
       assertNull(instance.getOrder(inputDate, order.getOrderNumber()));
       
    }

    /**
     * Test of saveOrder method, of class ServiceImpl.
     */
}
