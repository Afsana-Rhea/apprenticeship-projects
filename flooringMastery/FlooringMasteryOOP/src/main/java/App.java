
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.am.flooringmaster.controller.Controller;
import sg.am.flooringmastery.dao.IdDao;
import sg.am.flooringmastery.dao.IdDaoImpl;
import sg.am.flooringmastery.dao.OrderDao;
import sg.am.flooringmastery.dao.OrderDaoImpl;
import sg.am.flooringmastery.dao.PersistenceException;
import sg.am.flooringmastery.dao.ProductDao;
import sg.am.flooringmastery.dao.ProductDaoImpl;
import sg.am.flooringmastery.dao.TaxesDao;
import sg.am.flooringmastery.dao.TaxesDaoImpl;
import sg.am.flooringmastery.service.InvalidAreaException;
import sg.am.flooringmastery.service.InvalidDateException;
import sg.am.flooringmastery.service.Service;
import sg.am.flooringmastery.service.ServiceImpl;
import sg.am.flooringmastery.view.UserIO;
import sg.am.flooringmastery.view.UserIOImpl;
import sg.am.flooringmastery.view.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author afsanamiji
 */
public class App {

    public static void main(String[] args) throws InvalidDateException, PersistenceException, InvalidAreaException {

        //OrderDao myOrderDao = new OrderDaoImpl();
        // ProductDao myProductDao = new ProductDaoImpl();
        //TaxesDao myTaxesDao = new TaxesDaoImpl();
        //IdDao myIdDao = new IdDaoImpl();
        //Service myService = new ServiceImpl(myOrderDao, myProductDao, myTaxesDao, myIdDao);
        //UserIO myIO = new UserIOImpl();
        //View myView = new View(myIO);
        //Controller myController = new Controller(myService, myView);
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        Scanner sc;
        String beanName= "myController";
        
        try {
            sc = new Scanner(new FileReader("Configuration.txt"));
        } catch (FileNotFoundException ex) {
            throw new PersistenceException("Couldn't find file.");
        }
        while (sc.hasNextLine()) {

            beanName = sc.nextLine();
            break;
        }
        sc.close();
        
        Controller myController = ctx.getBean(beanName, Controller.class);
        myController.run();
    }

}
