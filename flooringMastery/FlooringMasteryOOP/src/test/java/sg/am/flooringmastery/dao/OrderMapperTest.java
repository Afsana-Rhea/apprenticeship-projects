/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.am.flooringmastery.dto.Order;

/**
 *
 * @author afsanamiji
 */
public class OrderMapperTest {
    
    public OrderMapperTest() {
    }
    
    
    @Before
    public void setUp() {
    }
    
    
    /**
     * Test of mapToOrder method, of class OrderMapper.
     */
    @Test
    public void testMapToOrder() {
        String data = "1::Wise::OH::6.25::Wood::100.00::5.15::4.75::515.00::475.00::61.88::1051.88";
        Order expResult = new Order();
        expResult.setOrderNumber(1);
        expResult.setCustomerName("Wise");
        expResult.setState("OH");
        expResult.setTaxRate(new BigDecimal("6.25"));
        expResult.setProductType("Wood");
        expResult.setArea(new BigDecimal("100.0"));
        expResult.setCostPerSquareFoot(new BigDecimal("5.15"));
        expResult.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        expResult.setMaterialCost(new BigDecimal("515.0"));
        expResult.setLaborCost(new BigDecimal("475.0"));
        expResult.setTax(new BigDecimal("61.88"));
        expResult.setTotal(new BigDecimal("1051.88"));
        Order result = OrderMapper.mapToOrder(data);
        
        assertEquals(expResult.getOrderNumber(), result.getOrderNumber());
        assertEquals(expResult.getCustomerName(), result.getCustomerName());
        assertEquals(expResult.getState(), result.getState());
        assertEquals(expResult.getTaxRate(), result.getTaxRate());
        assertEquals(expResult.getProductType(), result.getProductType());
        assertEquals(expResult.getArea(), result.getArea());
        assertEquals(expResult.getCostPerSquareFoot(), result.getCostPerSquareFoot());
        assertEquals(expResult.getLaborCostPerSquareFoot(), result.getLaborCostPerSquareFoot());
        assertEquals(expResult.getMaterialCost(), result.getMaterialCost());
        assertEquals(expResult.getLaborCost(), result.getLaborCost());
        assertEquals(expResult.getTax(), result.getTax());
        assertEquals(expResult.getTotal(), result.getTotal());
        
    }

    /**
     * Test of mapToString method, of class OrderMapper.
     */
    @Test
    public void testMapToString() {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Wise");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.0"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.0"));
        order.setLaborCost(new BigDecimal("475.0"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));
        
        String expResult = "1::Wise::OH::6.25::Wood::100.0::5.15::4.75::515.0::475.0::61.88::1051.88";
        String result = OrderMapper.mapToString(order);
        
        assertEquals(expResult, result);
    }
    
}
