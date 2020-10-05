package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

//    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public List<Book> getBooksByParams(String bookAuthor, String bookTitle, Integer bookSize) {
        return bookRepo.retreiveByParams(bookAuthor, bookTitle, bookSize);
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBook(Integer bookIdToRemove, String bookAuthorToRemove, String bookTitleToRemove, Integer bookSizeToRemove) {
        return bookRepo.removeItem(bookIdToRemove, bookAuthorToRemove, bookTitleToRemove, bookSizeToRemove);
    }
}
