/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import DTO.Change;
import DTO.Item;
import Dao.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author afsanamiji
 */
public interface Service {
    //private methods in controller should match service
    public BigDecimal addMoney(BigDecimal amount) throws PersistenceException ;
    public Change returnChange()throws PersistenceException;
    public BigDecimal getBalance()throws NoItemInventoryException, InsufficientFundsException;
    public List<Item> getAllItems() throws PersistenceException;
    public boolean vendItem(String itemId)throws NoItemInventoryException, InsufficientFundsException;
    
    
    
}
