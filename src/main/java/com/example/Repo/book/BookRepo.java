package com.example.Repo.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.book.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {

}
