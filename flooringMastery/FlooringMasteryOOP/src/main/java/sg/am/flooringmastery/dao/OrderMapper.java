/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.dao;

import java.math.BigDecimal;
import static sg.am.flooringmastery.dao.OrderDaoImpl.DELIMITER;
import sg.am.flooringmastery.dto.Order;

/**
 *
 * @author afsanamiji
 */
public class OrderMapper {
    public static final String DELIMITER = "::";
    
    public static Order mapToOrder(String currentLine) {
        String[] fields = currentLine.split(DELIMITER);
        Order order = new Order();
        order.setOrderNumber(Integer.parseInt(fields[0]));
        order.setCustomerName(fields[1]);
        order.setState(fields[2]);
        order.setTaxRate(BigDecimal.valueOf(Double.parseDouble(fields[3])));
        order.setProductType(fields[4]);
        order.setArea(BigDecimal.valueOf(Double.parseDouble(fields[5])));
        order.setCostPerSquareFoot(new BigDecimal(fields[6]));
        order.setLaborCostPerSquareFoot(BigDecimal.valueOf(Double.parseDouble(fields[7])));
        order.setMaterialCost(BigDecimal.valueOf(Double.parseDouble(fields[8])));
        order.setLaborCost(BigDecimal.valueOf(Double.parseDouble(fields[9])));
        order.setTax(BigDecimal.valueOf(Double.parseDouble(fields[10])));
        order.setTotal(BigDecimal.valueOf(Double.parseDouble(fields[11])));
        return order;
    }
    
    public static String mapToString(Order order) {
        return order.getOrderNumber() + DELIMITER
                        + order.getCustomerName() + DELIMITER
                        + order.getState() + DELIMITER
                        + order.getTaxRate() + DELIMITER
                        + order.getProductType() + DELIMITER
                        + order.getArea() + DELIMITER
                        + order.getCostPerSquareFoot() + DELIMITER
                        + order.getLaborCostPerSquareFoot() + DELIMITER
                        + order.getMaterialCost() + DELIMITER
                        + order.getLaborCost() + DELIMITER
                        + order.getTax() + DELIMITER
                        + order.getTotal();
        
    }
    
}
