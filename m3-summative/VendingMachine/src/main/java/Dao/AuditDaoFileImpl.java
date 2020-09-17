/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author afsanamiji
 */
public class AuditDaoFileImpl implements AuditDao{
    
    //implement this in service layer when item is succesfuly vended.
    //use constructor to instantiate auditdao to servicelayerimpl, done

    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws Exception {
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (Exception e){
            throw new Exception("Could not persist audit information.", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString()+ " : " + entry);
        out.flush();
    }
    
}
