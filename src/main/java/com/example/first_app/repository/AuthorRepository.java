package com.example.first_app.repository;

import com.example.first_app.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findByEmail(String email);
}