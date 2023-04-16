package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LibraryManagementSystem {
    private Library library;

    public LibraryManagementSystem() {
        this.library = new Library();
    }

    public void addBook(String title, String authorName) {
        Author author = new Author(authorName);
        Book book = new Book(title, author);
        this.library.addBook(book);
        System.out.println("Book added: " + book.getTitle() + " by " + book.getAuthor().getName());
    }

    public void removeBook(String title) {
        Optional<Book> book = this.library.searchBooks(title);
        if (book.isPresent()) {
            this.library.removeBook(book.get());
            System.out.println("Book removed: " + book.get().getTitle());
        } else {
            System.out.println("Book not found.");
        }
    }

    public List<Book> searchBooks(String title) {
        List<Book> books = new ArrayList<>();
        for (Book book : this.library.getBooks()) {
            if (book.getTitle().equals(title)) {
                books.add(book);
            }
        }
        return books;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add book");
            System.out.println("2. Remove book");
            System.out.println("3. Search books");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter book title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter author name:");
                    String authorName = scanner.nextLine();
                    addBook(title, authorName);
                    break;
                case 2:
                    System.out.println("Enter book title:");
                    title = scanner.nextLine();
                    removeBook(title);
                    break;
                case 3:
                    System.out.println("Enter book title:");
                    title = scanner.nextLine();
                    List<Book> books = searchBooks(title);
                    if (books.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        System.out.println("Books found:");
                        for (Book book : books) {
                            System.out.println(book.getTitle() + " by " + book.getAuthor().getName());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        lms.run();
    }
}
