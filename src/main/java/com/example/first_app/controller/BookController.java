package com.example.first_app.controller;

import com.example.first_app.model.*;
import com.example.first_app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    // Create a new book with author
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        if (book.getAuthor() != null) {
            authorRepository.save(book.getAuthor());
        }
        return bookRepository.save(book);
    }
    
    // Get all books with author details
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findBooksWithAuthors();
    }
    
    // Get books by author
    @GetMapping("/author/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable String authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    
    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        return ResponseEntity.ok(bookRepository.save(book));
    }
    
    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}