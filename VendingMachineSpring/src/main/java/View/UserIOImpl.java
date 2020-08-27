/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Scanner;

/**
 *
 * @author afsanamiji
 */
public class UserIOImpl implements UserIO{
    Scanner sc = new Scanner(System.in);

    @Override
    public String readString(String message) {
        print(message);
        String user = sc.nextLine();
         return user;
        
    }

    @Override
    public int readInt(String message) {
        
        System.out.println(message);
        int user = Integer.parseInt(sc.nextLine());
        return user;

    }
      

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public boolean readBoolean(String message) {
        String userInput = readString(message);

        boolean result = userInput.equals("Y");
        return result;
    }

    @Override
    public double readDouble(String message) {
        
        System.out.println(message);
        Double user = Double.parseDouble(sc.nextLine());
        return user;
    }

    
}
