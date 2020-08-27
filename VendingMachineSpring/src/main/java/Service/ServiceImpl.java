/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DTO.Change;
import DTO.Item;
import Dao.AuditDao;
import Dao.ItemDao;
import Dao.PersistenceException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author afsanamiji
 */
public class ServiceImpl implements Service {

    private BigDecimal _balance = new BigDecimal("0.00");
    private ItemDao _myDao;
    private AuditDao _auditDao;

    public ServiceImpl(ItemDao myDao, AuditDao auditDao) {
        this._myDao = myDao;
        this._auditDao = auditDao;
    }

    @Override
    public BigDecimal addMoney(BigDecimal amount) {
        return _balance = amount;

    }

    @Override
    public Change returnChange() {
        Change change = new Change();
        int money = _balance.multiply(new BigDecimal("100.00")).intValue();
        if (money >= 25) {
            change.setQuarters(money / 25);
            money = money - (change.getQuarters() * 25);
        }
        if (money >= 10) {
            change.setDimes(money / 10);
            money = money - (change.getDimes() * 10);
        }
        if (money >= 5) {
            change.setNickels(money / 5);
            money = money - (change.getNickels() * 5);
        }
        change.setPenny(money);
        return change;

    }

    @Override
    public BigDecimal getBalance() {
        return _balance;
    }

    @Override
    public List<Item> getAllItems() throws PersistenceException {
        return _myDao.readAll();

    }

    @Override
    public boolean vendItem(String itemId) throws NoItemInventoryException, InsufficientFundsException {

        Item newItem = _myDao.readById(itemId);

        if ((newItem == null) || newItem.getItemQty() < 1) {
            throw new NoItemInventoryException();
        } else if (newItem.getItemCost().compareTo(_balance) > 0) {
            throw new InsufficientFundsException();
        } else {
            newItem.setItemQty(newItem.getItemQty() - 1);
            _myDao.update(newItem);
            _balance = _balance.subtract(newItem.getItemCost());
        }
        try {
            _auditDao.writeAuditEntry(itemId + " has been vended.");
        } catch (Exception ex) {
            throw new RuntimeException("error");
        }
        return true;
    }

}
