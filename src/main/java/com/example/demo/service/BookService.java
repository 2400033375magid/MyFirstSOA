package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

    private static final Logger logger =
            LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    // =========================
    // Get All Books
    // =========================
    public List<Book> getAllBooks() {

        logger.info("Fetching all books");

        return bookRepository.findAll();
    }

    // =========================
    // Get Book By ID
    // =========================
    public Book getBookById(Long id) {

        logger.info("Fetching book with ID: {}", id);

        return bookRepository.findById(id).orElse(null);
    }

    // =========================
    // Add Book
    // =========================
    public Book addBook(Book book) {

        logger.info("Adding new book: {}", book.getTitle());

        return bookRepository.save(book);
    }

    // =========================
    // Update Book
    // =========================
    public Book updateBook(Long id, Book newBook) {

        logger.info("Updating book ID: {}", id);

        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {

            logger.warn("Book ID {} not found", id);

            return null;
        }

        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setIsbn(newBook.getIsbn());

        logger.info("Book updated successfully.");

        return bookRepository.save(book);
    }

    // =========================
    // Delete Book
    // =========================
    public String deleteBook(Long id) {

        logger.info("Deleting book ID: {}", id);

        if (!bookRepository.existsById(id)) {

            logger.warn("Book ID {} not found", id);

            return "Book Not Found";
        }

        bookRepository.deleteById(id);

        logger.info("Book deleted successfully.");

        return "Book Deleted Successfully";
    }

    // =========================
    // Search
    // =========================
    public List<Book> searchByTitle(String title) {

        logger.info("Searching books by title: {}", title);

        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByAuthor(String author) {

        logger.info("Searching books by author: {}", author);

        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public Book searchByIsbn(String isbn) {

        logger.info("Searching book by ISBN: {}", isbn);

        return bookRepository.findByIsbn(isbn);
    }

    // =========================
    // Sorting
    // =========================
    public List<Book> sortByTitle() {

        logger.info("Sorting books by title");

        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    public List<Book> sortByAuthor() {

        logger.info("Sorting books by author");

        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "author"));
    }

    // =========================
    // Pagination
    // =========================
    public Page<Book> pagination(int page, int size) {

        logger.info("Pagination - Page: {} Size: {}", page, size);

        return bookRepository.findAll(
                PageRequest.of(page, size, Sort.by("title"))
        );
    }

    // =========================
    // Count Books
    // =========================
    public long countBooks() {

        logger.info("Counting books");

        return bookRepository.count();
    }

    // =========================
    // Check Book Exists
    // =========================
    public boolean exists(Long id) {

        logger.info("Checking if book exists with ID: {}", id);

        return bookRepository.existsById(id);
    }

    // =========================
    // Latest Book
    // =========================
    public Book latestBook() {

        logger.info("Fetching latest book");

        return bookRepository.findTopByOrderByIdDesc();
    }

    // =========================
    // Statistics
    // =========================
    public Map<String, Object> statistics() {

        logger.info("Generating statistics");

        Map<String, Object> statistics = new HashMap<>();

        statistics.put("Total Books", bookRepository.count());
        statistics.put("Latest Book", latestBook());
        statistics.put("Books Sorted By Title", sortByTitle().size());
        statistics.put("Server Status", "Running");
        statistics.put("Database", "PostgreSQL");

        return statistics;
    }

}