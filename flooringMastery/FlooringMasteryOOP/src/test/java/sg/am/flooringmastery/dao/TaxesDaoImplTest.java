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
import sg.am.flooringmastery.dto.Taxes;

/**
 *
 * @author afsanamiji
 */
public class TaxesDaoImplTest {

    ApplicationContext ctx;
    TaxesDao instance;

    public TaxesDaoImplTest() {
        this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.instance = ctx.getBean("myTaxesDao", TaxesDao.class);
    }

    @Test
    public void testReadAll() {
        //Act
        List<Taxes> taxesList = instance.readAll();
        int size = taxesList.size();
        
        
        //Arrange
        int expSize = 4;
        
        //Assert
        assertEquals(size, expSize);
    }

    /**
     * Test of readByName method, of class TaxesDaoImpl.
     */
    // @Test
    public void testReadByName() {
        //Act
        Taxes myTax = instance.readByName("OH");
        
        //Arrange
        Taxes expTax = new Taxes();
        expTax.setState("OH");
        expTax.setTaxRate(new BigDecimal("6.25"));
        //Assert
        
        assertTrue(myTax.equals(expTax));
    }

}
