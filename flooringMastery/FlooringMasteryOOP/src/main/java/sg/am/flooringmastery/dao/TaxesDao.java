/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import java.util.List;
import sg.am.flooringmastery.dto.Taxes;

/**
 *
 * @author afsanamiji
 */
public interface TaxesDao {
    List<Taxes> readAll();
    Taxes readByName(String state);
    
}
