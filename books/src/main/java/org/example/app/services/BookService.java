package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public List<Book> getBooksByParams(String regexToRemove) {
        return bookRepo.retreiveByParams(regexToRemove);
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBook(String regex) {
        return bookRepo.removeItem(regex);
    }
}
