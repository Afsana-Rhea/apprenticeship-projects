/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.math.BigDecimal;

/**
 *
 * @author afsanamiji
 */
public class Item {
    private String itemID;
    private String itemName;
    private BigDecimal itemCost;
    private int ItemQty;
    
    public Item(String itemName){
        this.itemName = itemName;
    }

    public Item() {
         
    }
        
    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public int getItemQty() {
        return ItemQty;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public void setItemQty(int ItemQty) {
        this.ItemQty = ItemQty;
    }
    
}
