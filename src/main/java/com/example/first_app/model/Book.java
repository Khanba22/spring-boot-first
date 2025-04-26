package com.example.first_app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.Data;

@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String title;
    private String isbn;
    @DBRef
    private Author author;
}