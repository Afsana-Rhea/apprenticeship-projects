/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import sg.am.flooringmastery.dao.IdDao;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author afsanamiji
 */
public class IdDaoImpl implements IdDao {

    public static final String ID_FILE = "ID.txt";

    @Override
    public void writeId(int iD) throws PersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ID_FILE, true));
        } catch (IOException e) {
            throw new PersistenceException("Could not persist ID information.", e);
        }
        out.println(iD);

        out.flush();
    }

    @Override
    public int loadId() throws PersistenceException {
        Scanner sc;
        int iD = 0;
        try {
            sc = new Scanner(new FileReader(ID_FILE));
        } catch (FileNotFoundException ex) {
            throw new PersistenceException("Couldn't find file.");
        }
        while (sc.hasNextLine()) {

            iD = Integer.parseInt(sc.nextLine());
        }
        sc.close();
        return iD;
    }
}
