package ee.ivkhkdev.javafxpro1.service;

import ee.ivkhkdev.javafxpro1.model.entity.Book;
import ee.ivkhkdev.javafxpro1.model.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class BookService implements Serializable {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
