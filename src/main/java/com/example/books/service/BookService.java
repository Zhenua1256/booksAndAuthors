package com.example.books.service;

import com.example.books.model.Book;

import java.util.List;

public interface BookService {

  Book create(Book book);

  Book update(Book book);

  void delete(Long id);

  Book getOne(Long id);

  List<Book> getAll();

}
