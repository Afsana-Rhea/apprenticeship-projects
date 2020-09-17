/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DTO.Item;
import Service.InsufficientFundsException;
import Service.NoItemInventoryException;
import java.util.List;

/**
 *
 * @author afsanamiji
 */
public interface ItemDao {
    
    public List<Item> readAll(); 
    public Item readById(String ItemId) throws NoItemInventoryException, InsufficientFundsException;
    public void update(Item item) throws NoItemInventoryException, InsufficientFundsException;
}
