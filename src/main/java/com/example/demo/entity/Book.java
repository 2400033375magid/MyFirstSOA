package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Book Entity")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Book ID",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Schema(
            description = "Book Title",
            example = "Clean Code"
    )
    private String title;

    @NotBlank(message = "Author cannot be empty")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    @Schema(
            description = "Author Name",
            example = "Robert C. Martin"
    )
    private String author;

    @NotBlank(message = "ISBN cannot be empty")
    @Size(min = 5, max = 20, message = "ISBN must be between 5 and 20 characters")
    @Schema(
            description = "Book ISBN",
            example = "9780132350884"
    )
    private String isbn;

    public Book() {
    }

    public Book(Long id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}