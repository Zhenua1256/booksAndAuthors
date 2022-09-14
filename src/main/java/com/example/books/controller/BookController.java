package com.example.books.controller;

import com.example.books.model.Book;
import com.example.books.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {

  private final BookService bookService;

  @Operation(summary = "Create book")
  @PostMapping(value = "/books")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Book> create(@RequestBody Book book) {
    return ResponseEntity.ok(bookService.create(book));
  }

  @Operation(summary = "Update book")
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "/books")
  public ResponseEntity<Book> update(@RequestBody Book book) {
    return ResponseEntity.ok(bookService.update(book));
  }

  @Operation(summary = "Delete book")
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping(value = "/books/{id}")
  public void delete(@PathVariable Long id) {
    bookService.delete(id);
  }

  @Operation(summary = "Get book")
  @GetMapping(value = "/books/{id}")
  public ResponseEntity<Book> getOne(@PathVariable Long id) {
    return ResponseEntity.ok(bookService.getOne(id));
  }

  @Operation(summary = "Get all books")
  @GetMapping(value = "/books")
  public ResponseEntity<List<Book>> getAll() {
    return ResponseEntity.ok(bookService.getAll());
  }
}
