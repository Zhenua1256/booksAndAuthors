package com.example.books.controller;

import com.example.books.model.Author;
import com.example.books.service.AuthorService;
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
public class AuthorController {

  private final AuthorService authorService;

  @Operation(summary = "Create author")
  @PostMapping(value = "/authors")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Author> create(@RequestBody Author author) {
    return ResponseEntity.ok(authorService.create(author));
  }

  @Operation(summary = "Update author")
  @PutMapping(value = "/authors")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Author> update(@RequestBody Author author) {
    return ResponseEntity.ok(authorService.update(author));
  }

  @Operation(summary = "Get author")
  @GetMapping(value = "/authors/{id}")
  public ResponseEntity<Author> getOne(@PathVariable Long id) {
    return ResponseEntity.ok(authorService.getOne(id));
  }

  @Operation(summary = "Get all authors")
  @GetMapping(value = "/authors")
  public ResponseEntity<List<Author>> getAll() {
    return ResponseEntity.ok(authorService.getAll());
  }

  @Operation(summary = "Delete author")
  @DeleteMapping(value = "/authors/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void delete(@PathVariable Long id) {
    authorService.delete(id);
  }
}
