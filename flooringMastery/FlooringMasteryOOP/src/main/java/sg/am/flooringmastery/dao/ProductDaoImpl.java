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
import sg.am.flooringmastery.dto.Order;
import sg.am.flooringmastery.dto.Product;

/**
 *
 * @author afsanamiji
 */
public class ProductDaoImpl implements ProductDao{
        private Map< String, Product> productList = new HashMap<>();
    public static final String PRODUCTS_FILE= "Products.txt";
    public static final String DELIMITER = ",";
            

    public ProductDaoImpl() {
        load();
    }

    @Override
    public List<Product> readAll() {
        return new ArrayList(productList.values());
    }

    @Override
    public Product readByName(String productName) {
        return productList.get(productName);
    }
    
    private void load(){
        List<Product> myProductList = new ArrayList<Product>();
        Scanner sc;
        try{
            sc = new Scanner(new FileReader("Products.txt"));
        }catch (FileNotFoundException ex){
            throw new RuntimeException("Persistence Error - contact IT");
        }
        while(sc.hasNextLine()){
            String currentLine = sc.nextLine();
            String[] fields = currentLine.split(DELIMITER);
            Product product = new Product();
            product.setProductType(fields[0]);
            product.setCostPerSquareFoot(new BigDecimal(fields[1]));
            product.setLaborCostPerSquareFoot(new BigDecimal(fields[2]));
            productList.put(product.getProductType(), product);
        }
        sc.close();
    }
}
