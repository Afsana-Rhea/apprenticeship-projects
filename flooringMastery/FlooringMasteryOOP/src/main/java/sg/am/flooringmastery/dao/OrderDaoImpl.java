       /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import sg.am.flooringmastery.dto.Order;
import sg.am.flooringmastery.service.InvalidDateException;

/**
 *
 * @author afsanamiji
 */
public class OrderDaoImpl implements OrderDao {

    private Map<LocalDate, HashMap<Integer, Order>> myDateList = new HashMap<>();
    public static String ORDER_FILE = "";
    public static final String DELIMITER = "::";

    public OrderDaoImpl() {

    }

    @Override
    public Order create(LocalDate date, Order myOrder) {
        if (!myDateList.containsKey(date)) {
            load(date);
        }

        myDateList.get(date).put(myOrder.getOrderNumber(), myOrder);

        return myOrder;
    }

    @Override
    public List<Order> readAll(LocalDate date) {
        if (!myDateList.containsKey(date)) {
            load(date);
        }

        return new ArrayList(myDateList.get(date).values());
    }

    @Override
    public List<Order> readByDate(LocalDate date) throws InvalidDateException {
        if (!myDateList.containsKey(date)) {
            load(date);
        }
        if (myDateList.get(date).values() == null) {
            throw new InvalidDateException("File with that date does not exist");
        }
        return new ArrayList(myDateList.get(date).values());

    }

    @Override
    public Order readById(LocalDate date, int orderNum) throws PersistenceException {
        if (!myDateList.containsKey(date)) {
            load(date);
        }

        return myDateList.get(date).get(orderNum);
    }

    @Override
    public void update(Order order, LocalDate date) throws PersistenceException {
        if (!myDateList.containsKey(date)) {
            load(date);
        }
        myDateList.get(date).put(order.getOrderNumber(), order);

    }

    @Override
    public void delete(LocalDate date, int orderNum) throws PersistenceException {
        if (!myDateList.containsKey(date)) {
            load(date);

        }
        myDateList.get(date).remove(orderNum);
    }

    public void load(LocalDate date) {
        HashMap<Integer, Order> orderList = new HashMap<Integer, Order>();
        Scanner sc;
        try {
            String textDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));

            sc = new Scanner(new FileReader("Orders_" + textDate + ".txt"));
            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();
                Order currentOrder = OrderMapper.mapToOrder(currentLine);
                orderList.put(currentOrder.getOrderNumber(), currentOrder);
            }
            sc.close();
        } catch (FileNotFoundException ex) {

        }

        myDateList.put(date, orderList);

    }

    public void save() {
        for (LocalDate localDate : myDateList.keySet()) {
            PrintWriter pw;

            try {
                String textDate = localDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
                pw = new PrintWriter(new FileWriter("Orders_" + textDate + ".txt"));
            } catch (IOException ex) {
                throw new RuntimeException("Persistance Error - Contact IT");
            }
            for (Order order : myDateList.get(localDate).values()) {
                
                pw.println(OrderMapper.mapToString(order));
                pw.flush();

            }
            pw.close();
        }
    }

    

}
