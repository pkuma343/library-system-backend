package com.genpact.assignment.service

import com.genpact.assignment.dao.BookRepository
import com.genpact.assignment.dao.LibraryRepository
import com.genpact.assignment.models.Book
import com.genpact.assignment.models.Library
import spock.lang.Specification

class LibraryServiceTest extends Specification {

    LibraryService libraryService = new LibraryService()
    LibraryRepository libraryRepository = Mock()
    BookRepository bookRepository = Mock()

    def setup() {
        libraryService.libraryRepository = libraryRepository
        libraryService.bookRepository = bookRepository
    }

    def "test getBooksFromDB"() {
        given:
        Library library = new Library("My Library")
        library.setId(10001)
        Book book = new Book("New Book")
        List<Book> books = new ArrayList<Book>()
        books.add(book)
        library.setBooks(books)
        Optional<Library> lib = Optional.ofNullable(library)
        libraryRepository.findById(*_) >> lib
        when:
        def books1 = libraryService.getBooksFromDB(10001)
        then:
        books1.size() == 1
    }

    def "test saveBook"() {
        given:
        Library library = new Library("My Library")
        library.setId(10001)
        Optional<Library> lib = Optional.ofNullable(library)
        libraryRepository.findById(*_) >> lib
        Book book = new Book("New Book")
        when:
        libraryService.saveBook(10001, book)
        then:
        1 * bookRepository.save(*_)
    }
}
