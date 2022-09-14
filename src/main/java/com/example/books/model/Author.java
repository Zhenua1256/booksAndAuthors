package com.example.books.model;

import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "AUTHORS")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_seq")
  @SequenceGenerator(name = "authors_seq", sequenceName = "authors_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 100)
  @NotEmpty
  private String name;

  @OneToMany(mappedBy = "authorId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Book> books;
}
