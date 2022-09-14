package com.example.books.service;

import com.example.books.model.Author;

import java.util.List;

public interface AuthorService {

  Author create(Author author);

  Author update(Author author);

  Author getOne(Long id);

  List<Author> getAll();

  void delete(Long id);
}
