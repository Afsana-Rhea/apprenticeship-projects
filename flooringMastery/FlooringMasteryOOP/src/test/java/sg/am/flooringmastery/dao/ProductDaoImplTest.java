/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.am.flooringmastery.dto.Order;
import sg.am.flooringmastery.dto.Product;

/**
 *
 * @author afsanamiji
 */
public class ProductDaoImplTest {

    ApplicationContext ctx;
    ProductDao instance;

    public ProductDaoImplTest() {
        this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.instance = ctx.getBean("myProductDao", ProductDao.class);
    }

    @Test
    public void testReadAll() {
        //ACT

        List<Product> list = instance.readAll();
        int size = list.size();

        //ARRANGE
        int expSize = 4;

        //ASSERT
        assertEquals(size, expSize);
    }

    /**
     * Test of readByName method, of class ProductDaoImpl.
     */
    @Test
    public void testReadByName() {
        //act
        Product myProduct = instance.readByName("Wood");
        
        
        //arrange
        Product expProduct = new Product();
        expProduct.setProductType("Wood");
        expProduct.setCostPerSquareFoot(new BigDecimal("5.15"));
        expProduct.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        
        //assert
        assertTrue(myProduct.equals(expProduct));
        

    }

}
