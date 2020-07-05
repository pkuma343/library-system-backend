package com.genpact.assignment.controller;

import com.genpact.assignment.models.Book;
import com.genpact.assignment.models.Library;
import com.genpact.assignment.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LibraryController {
    Logger logger = LoggerFactory.getLogger(LibraryController.class);
    @Autowired
    LibraryService libraryService;

    @GetMapping("/libraries/{id}/books")
    public ResponseEntity<List<Book>> getBooksFromLibrary(@PathVariable long id) {
        List<Book> books = libraryService.getBooksFromDB(id);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);

    }

    @PostMapping("/libraries/{id}/books")
    public ResponseEntity<Book> saveBookInALibrary(@PathVariable long id, @RequestBody Book book) {
        Book savedBook = libraryService.saveBook(id, book);
        return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/libraries/{id}/books/{bkid}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @PathVariable long bkid, @RequestBody Book book) {
        Book updatedBook = libraryService.updateBook(id, bkid, book);
        return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
    }

    @GetMapping("/libraries")
    public ResponseEntity<List<Library>> getLibraries() {
        List<Library> libraries = libraryService.getLibraries();
        return new ResponseEntity<List<Library>>(libraries, HttpStatus.OK);
    }
}
