package com.tts;

import java.util.ArrayList;

public class Library {

    //variables
    //hours is static because it's accessed by static method printOpeningHours
    //which does not require an instance of Library in order to be called
    private static String hours = "Libraries are open daily from 9am to 5pm.";
    private String address = "";
    private ArrayList<Book> bookList = new ArrayList<>();

    //constructors
    public Library(){
    }

    public Library(String addy) {
        setAddress(addy);
    }

    //getters and setters
    public void printAddress() {
        System.out.println(this.getAddress());
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    //methods
    private void addBook(Book thisBook) {
        bookList.add(thisBook);
    }

    //needs to be static because it is called from main() without a library instance
    private static void printOpeningHours() {
        System.out.println(hours);
    }

    private void borrowBook(String thisTitle) {
        //to check if library HAS BOOK use arrayLIst.contains would work on
        //arrayList of Strings, but I bookList is a list of objects,hmmm
        boolean hasBook = false;
        String msg = null;

        if(bookList.isEmpty()) msg = "Sorry, we have no books.";
        else {
            for (Book book : bookList) {
//                hasBook = book.title.equalsIgnoreCase(thisTitle);
                hasBook = book.getTitle().equalsIgnoreCase(thisTitle);

                if (!hasBook) msg = "Sorry, " + thisTitle + " is not in our catalog";
                else {
                    if (book.isBorrowed()) msg = "Sorry, " + thisTitle + " is already borrowed.";
                    else {
                        msg = "You successfully borrowed " + thisTitle;
                        book.borrowed();
//                        bookList.remove(book); don't want to remove book from bookList ("library") just hide it if borrowed
                        break;
                    }
                }
            }
        }
        System.out.println(msg);
    }//end borrowBook()

    private void returnBook(String thisTitle) {
        for (Book book : bookList) {
            if(book.getTitle().equalsIgnoreCase(thisTitle)) {
                book.returned();
                System.out.println("You successfully returned " + thisTitle);
            }
        }
    }

    private void printAvailableBooks() {
        if (bookList.isEmpty()){
            System.out.println("No books in catalog");
        }
        else {
            for (Book book : bookList)
                if(!book.isBorrowed()) System.out.println(book.getTitle());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

        // Add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));

        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        // I added the next 2 lines for testing purposes
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();

        // Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();

        // Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

        // I added the next 2 lines for testing purposes
        System.out.println("Borrowing The Da Vinci Code");
        firstLibrary.borrowBook("the da vinci code");
        System.out.println();

        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();

    }//end main()

}//end Library class