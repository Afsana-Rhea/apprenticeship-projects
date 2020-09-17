/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author afsanamiji
 */
public class Change {
    private int penny;
    private int quarters;
    private int dimes;
    private int nickels;

    public int getPenny() {
        return penny;
    }
   
   
    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setPenny(int penny) {
        this.penny = penny;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }
    
}
