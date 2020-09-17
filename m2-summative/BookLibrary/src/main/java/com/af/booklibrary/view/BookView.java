/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.af.booklibrary.view;

import com.af.booklibrary.dto.Book;
import java.util.List;

/**
 *
 * @author afsana
 */
public class BookView {

    private UserIO io;

    public BookView() {
        this.io = new UserIOConsoleImpl();
    }

    public void displayBooks(List<Book> allBooks) {
        io.print("==================== Books ============");

        for (Book myBook : allBooks) {
            System.out.println(myBook.getTitle() + "-" + myBook.getAuthor() + "-" + myBook.getGenre() + "-" + myBook.getPublisher() + "-" + myBook.getRating() + "-" + myBook.getPages());
        }
    }

    public void displayBookTitles(List<Book> allBooks) {
        io.print("==================== Books ============");

        for (Book myBook : allBooks) {
            System.out.println(myBook.getTitle());
        }
    }

    public Book getNewBookInfo() {

        io.print("==================== Create a Book ============");
        Book myBook = new Book();
        myBook.setTitle(io.readString("What is your new book title"));
        myBook.setAuthor(io.readString("Who is the author?"));
        myBook.setGenre(io.readString("What genre does this book fit into?"));
        myBook.setRating(io.readDouble("How many stars have users given this book?"));
        myBook.setPages(io.readInt("How many pages does this edition have?"));
        myBook.setPublisher(io.readString("Who is the publisher of your book?"));

        return myBook;
    }

    public Book getBookEdit(Book myBook) {
        io.print("==================== Edit a Book ============");
        if (myBook != null) {

            myBook.setAuthor(io.readString("Who is the correct author?"));
            myBook.setGenre(io.readString("What is the correct genre?"));
            myBook.setRating(io.readDouble("How many stars have users given this book?"));
            myBook.setPages(io.readInt("How many pages does this edition actually have?"));
            myBook.setPublisher(io.readString("Who is the real publisher of your book?"));
        } else {
            io.print("No such Book.");
        }
        return myBook;
    }

    public void displayBook(Book myBook) {
        io.print("==================== Display Book ============");

        if (myBook != null) {

            io.print(myBook.getTitle() + "- Author: " + myBook.getAuthor() + "- Genre : " + myBook.getGenre() + "- Publisher : " + myBook.getPublisher() + "- Rating : " + myBook.getRating() + "- Pages :" + myBook.getPages());
        } else {
            io.print("No such Book.");
        }
    }

    public void displayError(String message) {
        io.print("===================== Error =================");

        io.print(message);

        io.print("=============================================");

    }

    public int getMenuChoice() {
        io.print("===================== Main Menu =================");
        io.print("Please pick an option.");
        return io.readInt("1. Add a Book\n2. Display All Books\n3. Edit a Book \n4. Search By Title\n5. Remove a Book \n6. Exit");
    }

    public void displayUpdateSuccess() {
        io.print("Your book has edited!!!");
    }

    public void displayRemoveSuccess() {

        io.print("Your Book has been removed!!");
    }

    public String displayTitle() {
        return io.readString("Please pick a book.");
    }

}
