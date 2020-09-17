/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.af.booklibrary.dao;

import com.af.booklibrary.dto.Book;
import java.util.List;

/**
 *
 * @author afsana
 */
public interface BookDAO {

    Book Delete(String title) throws Exception ;

    Book create(String bookTitle, Book myBook) throws Exception ;

    List<Book> readAll()throws Exception ;

    Book readByTitle(String bookTitle) throws Exception ;

    Book edit(Book book) throws Exception ;
    
}
