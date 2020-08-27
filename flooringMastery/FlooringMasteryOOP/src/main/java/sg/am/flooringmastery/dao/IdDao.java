/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;



/**
 *
 * @author afsanamiji
 */
public interface IdDao {
    public void writeId(int iD) throws PersistenceException;
    public int loadId() throws PersistenceException;
    
}
