package com.example.books.impl;

import com.example.books.model.Author;
import com.example.books.repository.AuthorRepository;
import com.example.books.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Author create(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public Author update(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public Author getOne(Long id) {
    return authorRepository.findById(id).orElse(null);
  }

  @Override
  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  @Override
  public void delete(Long id) {
    if(authorRepository.findById(id).isPresent()) {
      authorRepository.delete(authorRepository.getById(id));
    }
  }
}
