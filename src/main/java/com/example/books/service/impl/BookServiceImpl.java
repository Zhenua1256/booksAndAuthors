package com.example.books.service.impl;

import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  public Book create(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book update(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public void delete(Long id) {
    if(bookRepository.findById(id).isPresent()) {
      bookRepository.delete(bookRepository.getById(id));
    }
  }

  @Override
  public Book getOne(Long id) {
    return bookRepository.findById(id).orElse(null);
  }

  @Override
  public List<Book> getAll() {
    return bookRepository.findAll();
  }
}
