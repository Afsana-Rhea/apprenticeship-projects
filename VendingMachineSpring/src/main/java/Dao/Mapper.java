/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DTO.Item;
import static Dao.ItemDaoFileImpl.DELIMITER;

/**
 *
 * @author afsanamiji
 */
public class Mapper {
 private static final String DELIMITER = "::";
    public static String MapFromItem(Item item) {
       return item.getItemID() + DELIMITER + item.getItemName() + DELIMITER + item.getItemCost() + DELIMITER + item.getItemQty();
    }

}
