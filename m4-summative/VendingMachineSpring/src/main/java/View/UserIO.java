/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author afsanamiji
 */
public interface UserIO {
     String readString(String message);
    int readInt(String message);
    void print(String message);
    boolean readBoolean(String message);
    double readDouble(String message);
    
}
