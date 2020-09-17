/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools :: Templates
 * and open the template in the editor.
 */
package com.af.booklibrary.dao;

import com.af.booklibrary.dto.Book;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author afsana
 */
public class BookDAOFileImpl implements BookDAO {

    public static final String BOOK_FILE = "books.txt";
    public static final String DELIMITER = "::";

    private List<Book> bookList = new ArrayList<Book>();
    private Map<String, Book> myBooks = new HashMap<>();

    public BookDAOFileImpl() {
        this.Load();
    }

    @Override
    public Book create(String bookTitle, Book myBook) throws Exception {
        loadLibrary();
        Book newBook = myBooks.put(bookTitle, myBook);
        writeLibrary();
        return myBook;
    }

    @Override
    public List<Book> readAll() throws Exception{
        loadLibrary();
        return new ArrayList(myBooks.values());
        //returns arraylist of Book books
    }

    @Override
    public Book readByTitle(String bookTitle)  throws Exception {
    loadLibrary();
        return myBooks.get(bookTitle);
    }

    @Override
    public Book edit(Book book)  throws Exception {
        loadLibrary();
        Book newBook = myBooks.put(book.getTitle(), book);
        writeLibrary();
        return newBook;
    }

    //this.Save();
    @Override
    public Book Delete(String title) throws Exception {
        loadLibrary();
        Book deletedBook = myBooks.remove(title);
        writeLibrary();
        return deletedBook;
    }

    private void Save() {

        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("books.txt"));
        } catch (IOException ex) {
            throw new RuntimeException("Persistance Error - Contact IT");
        }
//        pw.println("Whatever");
        for (Book myBook : this.bookList) {
            pw.println(myBook.getTitle() + "::"
                    + myBook.getAuthor() + "::"
                    + myBook.getGenre() + "::"
                    + myBook.getRating() + "::"
                    + myBook.getPages() + "::"
                    + myBook.getPublisher() + "::"
                    + myBook.isIsBorrowed());

            pw.flush();
        }
        pw.close();
    }

    private void Load() {
        this.bookList = new ArrayList<Book>();
        Scanner sc;

        try {
            sc = new Scanner(new FileReader("books.txt"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Persistance Error - Contact IT" + ex.getMessage());
        }

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] fields = currentLine.split("::");
            Book myBook = new Book();

            myBook.setTitle(fields[0]);
            myBook.setAuthor(fields[1]);
            myBook.setGenre(fields[2]);
            myBook.setRating(Double.parseDouble(fields[3]));
            myBook.setPages(Integer.parseInt(fields[4]));
            myBook.setPublisher(fields[5]);
            myBook.setIsBorrowed(Boolean.parseBoolean(fields[6]));
            bookList.add(myBook);
        }
        sc.close();

    }

    private Book unmarshallBook(String bookAsText) {
        String[] bookTokens = bookAsText.split(DELIMITER);
        Book bookFromFile = new Book();
        bookFromFile.setTitle(bookTokens[0]);
        bookFromFile.setAuthor(bookTokens[1]);
        bookFromFile.setGenre(bookTokens[2]);
        bookFromFile.setRating(Double.parseDouble(bookTokens[3]));
        bookFromFile.setPages(Integer.parseInt(bookTokens[4]));
        bookFromFile.setPublisher(bookTokens[5]);
        bookFromFile.setIsBorrowed(Boolean.parseBoolean(bookTokens[6]));
        return bookFromFile;
    }

    private void loadLibrary() throws Exception {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(BOOK_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception(
                    "-_- Could not load book data into memory.", e);
        }

        String currentLine;

        Book currentBook;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentBook = unmarshallBook(currentLine);

            myBooks.put(currentBook.getTitle(), currentBook);
        }
    }
    // close scanner

    private String marshallBook(Book aBook) {
        String bookAsText = aBook.getTitle() + DELIMITER;
        bookAsText += aBook.getAuthor() + DELIMITER;
         bookAsText += aBook.getGenre() + DELIMITER;
        bookAsText += aBook.getRating() + DELIMITER;
        bookAsText += aBook.getPages() + DELIMITER;
        bookAsText += aBook.getPublisher() + DELIMITER;
        bookAsText += aBook.isIsBorrowed();

        return bookAsText;

    }

    private void writeLibrary() throws Exception {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(BOOK_FILE));
        } catch (IOException e) {
            throw new Exception(
                    "Could not save book data.", e);
        }
        String bookAsText;
        List<Book> bookList = this.readAll();
        for (Book currentBook : bookList) {

            bookAsText = marshallBook(currentBook);
            out.println(bookAsText);
            out.flush();
        }
        out.close();
    }
}


