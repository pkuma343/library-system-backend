package com.genpact.assignment.dao;

import com.genpact.assignment.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
