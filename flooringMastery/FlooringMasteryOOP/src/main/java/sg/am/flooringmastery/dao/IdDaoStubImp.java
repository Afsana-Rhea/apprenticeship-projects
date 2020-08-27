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
public class IdDaoStubImp implements IdDao{

      int iD = 10;

    @Override
    public void writeId(int iD) throws PersistenceException {
        this.iD = iD;
    }

    @Override
    public int loadId() throws PersistenceException {
        return iD;
    }

    
}
