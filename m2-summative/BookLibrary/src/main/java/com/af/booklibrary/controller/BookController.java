/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.af.booklibrary.controller;

import com.af.booklibrary.dto.Book;
import com.af.booklibrary.service.BookService;
import com.af.booklibrary.view.BookView;
import java.util.List;

/**
 *
 * @author afsana
 */
public class BookController {

    private BookService myService;
    private BookView myView;

    public BookController(BookService myService, BookView myView) {
    this.myService = myService;
    this.myView = myView;
    //constructor that arguments get passed in from the app.
}

    public void run(){
        boolean keepGoing = true;
        try{
        while (keepGoing == true) {
            int userChoice = myView.getMenuChoice();
            switch (userChoice) {
                case 1: // Adding a new book
                    this.createBook();
                    break;
                case 2: // Display all books
                   this.displayAllBooks();
                    break;
                case 3: // editt a book
                    this.editBook();
                    break;
                case 4: //search by title
                    this.searchBook();
                    break;
                case 5: // Remove a book
                   this.removeBook();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    myView.displayError("Invalid Choice");
            }
        }
        }catch (Exception e) {
        myView.displayError(e.getMessage());
    }

    }

    private void displayAllBooks(){
        try{
        myView.displayBooks(myService.getAllBooks());
        } catch(Exception ex) {
         myView.displayError(ex.getMessage()); 
         displayAllBooks();
        }
    }

    private void createBook() {
        Book myBook = new Book();
        myBook = myView.getNewBookInfo();
        
        try {
            myBook = myService.addNewBook(myBook);
            myView.displayBook(myBook);
        } catch (Exception ex) {
            myView.displayError(ex.getMessage());
            createBook();
        }

    }

    private void searchBook() throws Exception{
        
        myView.displayBookTitles(myService.getAllBooks());
        Book userSelection = myService.readTitle(myView.displayTitle());
        // getting the user input from the view and getting the book from myservice
        try {
             myView.displayBook(userSelection);
             //displaying book
        } catch (Exception e) {
            myView.displayError(e.getMessage());
           searchBook();
        }
         
    }

    private void removeBook() throws Exception{
        //Get all the books
        myView.displayBookTitles(myService.getAllBooks());
        // Display the books to the user
        // get the book id the user would like to checkout
       String userSelection = myView.displayTitle();
        // checkout the book (service)
        try {
            boolean bookExists = myService.removeBook(userSelection);
            // let the user know they have checked it out
            if (bookExists == true) {
                myView.displayRemoveSuccess();
            }
        } catch (Exception e) {
            myView.displayError(e.getMessage());
            removeBook();
        }
    }

     private void editBook() throws Exception{
    //Get all the books
    myView.displayBookTitles(myService.getAllBooks());
    // Display the books to the user
    // which book would the user like to edit
    String userSelection = myView.displayTitle();
    
    
    try {
    Book editedBook = myService.readTitle(userSelection);
    
    
    myService.edit(myView.getBookEdit(editedBook));
    //get new book info and commit it to file
    myView.displayUpdateSuccess();
    
    } catch (Exception e) {
    myView.displayError(e.getMessage());
    }
    }
    
    
}