/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DTO.Change;
import DTO.Item;
import Dao.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author afsanamiji
 */
public class View {
    private UserIO io;

    public View() {
        this.io = new UserIOImpl();
    }
    

    public int getMenuChoice() {
      io.print("===================== Main Menu =================");
      io.print("Please pick an option.");
      return io.readInt("1. Display all Items and Prices\n" + "2. Enter money\n" +"3. Vend item\n" +"4. Get change\n" +"5. Exit");
    }

    public void displayError(String message) throws PersistenceException {
        throw new PersistenceException("Please contact IT."); 
    }

    public void displayItems(List<Item> allItems) {
       io.print("============== Candies ============");
       
       for (Item myItem : allItems){
           System.out.println(myItem.getItemID() + " - " + myItem.getItemName() + " - " + myItem.getItemCost()); 
       }
    }

    public BigDecimal addMoney() {
       return new BigDecimal (io.readString("How much money would you like to add?"));
    }

    public String vendItem() {
      String test = io.readString("Which candy would you like to purchase?");
        
    return test;
    }

    public void vendSuccess() {
        io.print("Your item has been vended. Enjoy your candy" ); 
    }

    public void noItem() {
        io.print("We are out of that candy. Sorry"); 
    }

    public void noMoney() {
        io.print("Insufficient Funds");
    }

    public void returnChange(Change returnChange) {
     io.print("You get back " + returnChange.getQuarters() + " :Quarters " + returnChange.getDimes() + " :Dimes " + returnChange.getNickels() + " :Nickels " + returnChange.getPenny() + " :Pennies");
     
             }

    public void exitMessage() {
     io.print("Goodbye");
    }

    public void getBalance(BigDecimal balance) {
        System.out.println("Your balance is " + balance);; //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
