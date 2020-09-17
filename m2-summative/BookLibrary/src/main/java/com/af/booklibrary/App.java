/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.af.booklibrary;

import com.af.booklibrary.controller.BookController;
import com.af.booklibrary.dao.BookDAO;
import com.af.booklibrary.dao.BookDAOFileImpl;
import com.af.booklibrary.service.BookService;
import com.af.booklibrary.view.BookView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author afsana
 */
public class App {
    public static void main(String[] args) {
        /*        BookDAO myBookDAO = new BookDAOFileImpl();
        //instance of my dao
        BookService myService = new BookService(myBookDAO);
        //passing in my dao to my service so it has access to it.
        BookView myView = new BookView();
        // instance of my view
        BookController myController = new BookController(myService, myView);*/
        //passing in my service and view to my controller
        
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        BookController myController = 
           ctx.getBean("myController", BookController.class);
        
        myController.run();
        //calling my method in my controller.
    }
}
