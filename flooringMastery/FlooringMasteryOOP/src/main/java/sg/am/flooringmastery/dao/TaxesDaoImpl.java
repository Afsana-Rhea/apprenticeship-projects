/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import sg.am.flooringmastery.dto.Taxes;

/**
 *
 * @author afsanamiji
 */
public class TaxesDaoImpl implements TaxesDao{
     private Map< String, Taxes> taxesList = new HashMap<>();
    public static final String TAXES_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

    public TaxesDaoImpl() {
        load();
    }

    @Override
    public List<Taxes> readAll() {
        return new ArrayList(taxesList.values());
    }

    @Override
    public Taxes readByName(String state) {
    return   taxesList.get(state);
        
    }

    private void load(){
        List<Taxes> myTaxList = new ArrayList<Taxes>();
        Scanner sc;
        try{
            sc = new Scanner(new FileReader("Taxes.txt"));
        }catch (FileNotFoundException ex){
            throw new RuntimeException("Persistence Error - contact IT");
        }
        while(sc.hasNextLine()){
            String currentLine = sc.nextLine();
            String[] fields = currentLine.split(DELIMITER);
            Taxes tax = new Taxes();
            tax.setState(fields[0]);
            tax.setTaxRate(BigDecimal.valueOf(Double.parseDouble(fields[1])));
            taxesList.put(tax.getState(), tax);
        }
        sc.close();

    }
}
