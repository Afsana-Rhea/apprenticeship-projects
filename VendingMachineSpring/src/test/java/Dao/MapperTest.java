/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DTO.Item;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author afsanamiji
 */
public class MapperTest {

    public MapperTest() {
    }

    public void should_Give_String_From_DTO() {
        Item myItem = new Item();
        myItem.setItemID("B5");
        myItem.setItemName("mounds");
        myItem.setItemCost(new BigDecimal("2.50"));
        myItem.setItemQty(11);
        String expected = "B5::mounds::2.50::11";
        String actual = Mapper.MapFromItem(myItem);
        assertEquals(expected, actual);
    }

}
