/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DTO.Item;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author afsanamiji
 */
public class ItemDaoFileImpl implements ItemDao {

    private Map<String, Item> myItems = new HashMap<>();
    private List<Item> itemList = new ArrayList<Item>();
    public static final String ITEMS_FILE = "items.txt";
    public static final String DELIMITER = "::";

    public ItemDaoFileImpl() {
        this.load();
    }

    @Override
    public List<Item> readAll() {

        return itemList;
    }

    @Override
    public Item readById(String ItemId) {
        for (Item myItem : itemList) {
            if (ItemId.equals(myItem.getItemID())) {
                return myItem;
            }
        }
        return null;

    }

    @Override
    public void update(Item item) {

        Item newItem = myItems.put(item.getItemID(), item);
        save();

    }

    private void save() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("items.txt"));
        } catch (IOException e) {
            throw new RuntimeException("Persistance Error - Contact IT");
        }
        for (Item item : myItems.values()) {
            pw.println(item.getItemID() + DELIMITER + item.getItemName() + DELIMITER + item.getItemCost() + DELIMITER + item.getItemQty());
            pw.flush();
        }
        pw.close();
    }

    private void load() {
        this.itemList = new ArrayList<Item>();
        Scanner sc;
        try {
            sc = new Scanner(new FileReader("items.txt"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Persistance Error - Contact IT " + ex.getMessage());
        }

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] fields = currentLine.split(DELIMITER);
            Item myItem = new Item();
            myItem.setItemID(fields[0]);
            myItem.setItemName(fields[1]);
            BigDecimal b = BigDecimal.valueOf(Double.parseDouble(fields[2]));
            myItem.setItemCost(b);
            myItem.setItemQty(Integer.parseInt(fields[3]));
            myItems.put(myItem.getItemID(), myItem);
            itemList.add(myItem);
        }
        sc.close();
    }
}
