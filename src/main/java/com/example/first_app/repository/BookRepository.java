package com.example.first_app.repository;

import com.example.first_app.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByAuthorId(String authorId);
    
    @Aggregation(pipeline = {
        "{ $lookup: { from: 'authors', localField: 'author', foreignField: '_id', as: 'authorDetails' } }",
        "{ $unwind: '$authorDetails' }"
    })
    List<Book> findBooksWithAuthors();
}
