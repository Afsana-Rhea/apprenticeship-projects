/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.flooringmastery.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import sg.am.flooringmastery.dao.IdDao;
import sg.am.flooringmastery.dao.OrderDao;
import sg.am.flooringmastery.dao.PersistenceException;
import sg.am.flooringmastery.dao.ProductDao;
import sg.am.flooringmastery.dao.TaxesDao;
import sg.am.flooringmastery.dto.Order;

public class ServiceTrainingImpl implements Service {

    private OrderDao myOrderDao;
    private ProductDao myProductDao;
    private TaxesDao myTaxesDao;
    private IdDao myIdDao;

    public ServiceTrainingImpl(OrderDao myOrderDao, ProductDao myProductDao, TaxesDao myTaxesDao, IdDao myIdDao) {
        this.myOrderDao = myOrderDao;
        this.myProductDao = myProductDao;
        this.myTaxesDao = myTaxesDao;
        this.myIdDao = myIdDao;
    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws InvalidDateException, OrderNotFoundException {
        if (myOrderDao.readByDate(date) == null) {
            throw new OrderNotFoundException("Order with that Date not found");
        }
        return myOrderDao.readByDate(date);
    }

    @Override
    public Order addAnOrder(Order order) throws PersistenceException, InvalidProductException, InvalidStateException {
        int oldId = myIdDao.loadId();
        int newId = oldId + 1;
        order.setOrderNumber(newId);
        myIdDao.writeId(newId);

        if (order.getProductType() == null || myProductDao.readByName(order.getProductType()) == null) {
            throw new InvalidProductException("Invalid Product Type: " + order.getProductType());
        }

        if (order.getState() == null || myTaxesDao.readByName(order.getState()) == null) {
            throw new InvalidStateException("Invalid State: " + order.getState());
        }

        order.setTaxRate(myTaxesDao.readByName(order.getState()).getTaxRate());
        order.setCostPerSquareFoot((myProductDao.readByName(order.getProductType())).getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(myProductDao.readByName(order.getProductType()).getLaborCostPerSquareFoot());
        order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setMaterialCost(order.getCostPerSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        BigDecimal taxRate100 = new BigDecimal("0");
        taxRate100 = order.getTaxRate().divide(new BigDecimal("100"));
        BigDecimal totalBeforeTax = new BigDecimal("0");
        totalBeforeTax = order.getMaterialCost().add(order.getLaborCost());
        order.setTax(totalBeforeTax.multiply(taxRate100).setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getTax().add(totalBeforeTax).setScale(2, RoundingMode.HALF_UP));
        return order;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNum) throws PersistenceException, OrderNotFoundException {
        if (myOrderDao.readById(date, orderNum) == null) {
            throw new OrderNotFoundException("No order exists with that date");
        }

        return myOrderDao.readById(date, orderNum);
    }

    @Override
    public Order editOrder(LocalDate date, Order order) throws PersistenceException, InvalidProductException, InvalidStateException {
        if (order.getProductType() == null || myProductDao.readByName(order.getProductType()) == null) {
            throw new InvalidProductException("Invalid Product Type: " + order.getProductType());
        }

        if (order.getState() == null || myTaxesDao.readByName(order.getState()) == null) {
            throw new InvalidStateException("Invalid State: " + order.getState());
        }

        order.setOrderNumber(order.getOrderNumber());
        order.setTaxRate(myTaxesDao.readByName(order.getState()).getTaxRate());
        order.setCostPerSquareFoot((myProductDao.readByName(order.getProductType())).getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(myProductDao.readByName(order.getProductType()).getLaborCostPerSquareFoot());
        order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setMaterialCost(order.getCostPerSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        BigDecimal taxRate100 = new BigDecimal("0");
        taxRate100 = order.getTaxRate().divide(new BigDecimal("100"));
        BigDecimal totalBeforeTax = new BigDecimal("0");
        totalBeforeTax = order.getMaterialCost().add(order.getLaborCost());
        order.setTax(totalBeforeTax.multiply(taxRate100).setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getTax().add(totalBeforeTax).setScale(2, RoundingMode.HALF_UP));
        myOrderDao.update(order, date);
        return order;
    }

    @Override
    public boolean deleteOrder(Order order, LocalDate date) throws InvalidDateException, PersistenceException {
        if (myOrderDao.readById(date, order.getOrderNumber()) == null) {
            throw new InvalidDateException("No Order with that date found");
        }
        myOrderDao.delete(date, order.getOrderNumber());
        return true;
    }

    @Override
    public void saveOrder(boolean saveOrder) throws UnsupportedServiceException {
        //catch in controller
        throw new UnsupportedServiceException("Training Mode. Save Not Allowed");
    }

    @Override
    public Order validateOrder(Order order) throws InvalidNameException, InvalidProductException, InvalidStateException {
        if (order.getCustomerName() == null || order.getCustomerName().length() == 0) {
            throw new InvalidNameException("Name must be given.");
        }

        if (order.getProductType() == null || myProductDao.readByName(order.getProductType()) == null) {
            throw new InvalidProductException("Invalid Product Type: " + order.getProductType());
        }

        if (order.getState() == null || myTaxesDao.readByName(order.getState()) == null) {
            throw new InvalidStateException("Invalid State: " + order.getState());
        }
        order.setTaxRate(myTaxesDao.readByName(order.getState()).getTaxRate());
        order.setCostPerSquareFoot((myProductDao.readByName(order.getProductType())).getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(myProductDao.readByName(order.getProductType()).getLaborCostPerSquareFoot());
        order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setMaterialCost(order.getCostPerSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        BigDecimal taxRate100 = new BigDecimal("0");
        taxRate100 = order.getTaxRate().divide(new BigDecimal("100"));
        BigDecimal totalBeforeTax = new BigDecimal("0");
        totalBeforeTax = order.getMaterialCost().add(order.getLaborCost());
        order.setTax(totalBeforeTax.multiply(taxRate100).setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getTax().add(totalBeforeTax).setScale(2, RoundingMode.HALF_UP));
        return order;
    }
    }


