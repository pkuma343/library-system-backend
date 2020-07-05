package com.genpact.assignment.service;

import com.genpact.assignment.dao.BookRepository;
import com.genpact.assignment.dao.LibraryRepository;
import com.genpact.assignment.exception.NotFoundException;
import com.genpact.assignment.models.Book;
import com.genpact.assignment.models.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LibraryService {
    private static Logger logger = LoggerFactory.getLogger(LibraryService.class);
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LibraryRepository libraryRepository;

    public List<Book> getBooksFromDB(long id) throws NotFoundException {
        Optional<Library> library = libraryRepository.findById(id);
        if (!library.isPresent()) {
            throw new NotFoundException("Library not found");
        }
        return library.get().getBooks();
    }

    public Book saveBook(long id, Book bookToSave) throws NotFoundException {
        Optional<Library> library = libraryRepository.findById(id);
        if (!library.isPresent()) {
            throw new NotFoundException("Library not found");
        }
        bookToSave.setLibrary(library.get());
        Book savedBook = bookRepository.save(bookToSave);
        logger.info("Saved book: {}", savedBook);
        return savedBook;
    }

    public Book updateBook(long id, long bookId, Book bookToUpdate) throws NotFoundException {
        Optional<Library> library = libraryRepository.findById(id);
        if (!library.isPresent()) {
            throw new NotFoundException("Library not found");
        }
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) {
            throw new NotFoundException("Book not found");
        }
        bookToUpdate.setLibrary((library.get()));
        bookToUpdate.setId(book.get().getId());
        Book updatedBook = bookRepository.save(bookToUpdate);
        logger.info("Updated book: {}", updatedBook);
        return updatedBook;
    }

    public List<Library> getLibraries() {
        List<Library> libraries = libraryRepository.findAll();
        if (libraries.size() == 0) {
            throw new NotFoundException("Library not found");
        }
        return libraries;
    }
}
