package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
@Tag(
    name = "Book Management API",
    description = "REST API for Managing Books in the Library"
)
public class BookController {

    @Autowired
    private BookService bookService;

    // =========================
    // Get All Books
    // =========================
    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // =========================
    // Get Book By ID
    // =========================
    @Operation(summary = "Get book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {

        Book book = bookService.getBookById(id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    // =========================
    // Add Book
    // =========================
    @Operation(summary = "Add a new book")
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {

        Book savedBook = bookService.addBook(book);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // =========================
    // Update Book
    // =========================
    @Operation(summary = "Update book information")
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody Book book) {

        Book updatedBook = bookService.updateBook(id, book);

        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    // =========================
    // Delete Book
    // =========================
    @Operation(summary = "Delete a book")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {

        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    // =========================
    // Search By Title
    // =========================
    @Operation(summary = "Search books by title")
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Book>> search(@PathVariable String title) {

        return ResponseEntity.ok(bookService.searchByTitle(title));
    }

    // =========================
    // Search By Author
    // =========================
    @Operation(summary = "Search books by author")
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> author(@PathVariable String author) {

        return ResponseEntity.ok(bookService.searchByAuthor(author));
    }

    // =========================
    // Search By ISBN
    // =========================
    @Operation(summary = "Search book by ISBN")
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> isbn(@PathVariable String isbn) {

        return ResponseEntity.ok(bookService.searchByIsbn(isbn));
    }

    // =========================
    // Sort By Title
    // =========================
    @Operation(summary = "Sort books by title")
    @GetMapping("/sort/title")
    public ResponseEntity<List<Book>> sortTitle() {

        return ResponseEntity.ok(bookService.sortByTitle());
    }

    // =========================
    // Sort By Author
    // =========================
    @Operation(summary = "Sort books by author")
    @GetMapping("/sort/author")
    public ResponseEntity<List<Book>> sortAuthor() {

        return ResponseEntity.ok(bookService.sortByAuthor());
    }

    // =========================
    // Pagination
    // =========================
    @Operation(summary = "Get books with pagination")
    @GetMapping("/page")
    public ResponseEntity<Page<Book>> page(
            @RequestParam int page,
            @RequestParam int size) {

        return ResponseEntity.ok(bookService.pagination(page, size));
    }

    // =========================
    // Count Books
    // =========================
    @Operation(summary = "Get total number of books")
    @GetMapping("/count")
    public ResponseEntity<Long> count() {

        return ResponseEntity.ok(bookService.countBooks());
    }

    // =========================
    // Check Book Exists
    // =========================
    @Operation(summary = "Check if a book exists")
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {

        return ResponseEntity.ok(bookService.exists(id));
    }

    // =========================
    // Latest Book
    // =========================
    @Operation(summary = "Get the latest added book")
    @GetMapping("/latest")
    public ResponseEntity<Book> latest() {

        return ResponseEntity.ok(bookService.latestBook());
    }

    // =========================
    // Statistics
    // =========================
    @Operation(summary = "Get library statistics")
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> statistics() {

        return ResponseEntity.ok(bookService.statistics());
    }

}