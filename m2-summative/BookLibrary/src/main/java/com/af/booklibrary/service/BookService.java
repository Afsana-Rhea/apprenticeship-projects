/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.af.booklibrary.service;

import com.af.booklibrary.dao.BookDAO;

import com.af.booklibrary.dto.Book;
import java.util.List;

/**
 *
 * @author afsana
 */
public class BookService {

    private BookDAO myBookDao;

    public BookService(BookDAO myBookDao){
        this.myBookDao = myBookDao;
    }

    public List<Book> getAllBooks() throws Exception{
        return myBookDao.readAll();
        //asks the dao for all books. 
    }

    public Book addNewBook(Book myBook) throws Exception{
        if (myBook.getTitle().equals("")) {
            throw new RuntimeException("Title is Required");
        } //elseifs for other critera ir author, pages
        

        myBook = myBookDao.create(myBook.getTitle(), myBook);

        return myBook;
    }


    public boolean removeBook(String bookTitle) throws Exception{
       Book myBook = this.myBookDao.readByTitle(bookTitle);
        // step 2 check if book is not checkout
        if (myBook == null) {
            throw new RuntimeException("No book found by title : " + bookTitle);
        }
        
        myBookDao.Delete(bookTitle);
        return true;
    
    }

    
    
   public Book readTitle(String book) throws Exception{
       Book myBook = this.myBookDao.readByTitle(book);
       
       if (myBook == null) {
            throw new RuntimeException("No book found by title : " + book);
        }
       return myBook;
   } 
   public Book edit(Book book) throws Exception{
       Book myBook = this.myBookDao.edit(book);
       
       if (myBook == null) {
            throw new RuntimeException("No book found by title : " + book);
        }
       return myBook;
   }
}
