package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookDTO {

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank(message = "Author cannot be empty")
    @Size(min = 2, max = 50)
    private String author;

    @NotBlank(message = "ISBN cannot be empty")
    @Size(min = 5, max = 20)
    private String isbn;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, String author, String isbn) {
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