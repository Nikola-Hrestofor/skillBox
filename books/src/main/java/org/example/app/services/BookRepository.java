package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public List<Book> retreiveByParams(String regex) {
        ArrayList<Book> books = new ArrayList<>();
        for(Book book: retreiveAll()){
            if (book.getAuthor().matches(".*" + regex + ".*") ||
                    book.getTitle().matches(".*" + regex + ".*") ||
                    (book.getSize() != null && book.getSize().toString().matches(".*" + regex + ".*")) ||
                    (book.getId() != null && book.getId().toString().matches(".*" + regex + ".*"))){
                books.add(book);
            }
        }
        return books;
    }

    @Override
    public void store(Book book) {
        if(!book.getAuthor().equals("") || !(book.getSize() == null) || !book.getTitle().equals("")) {
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            repo.add(book);
        } else {
            logger.info("empty entry..");
        }
    }

    @Override
    public boolean removeItem(String regex) {
        for (Book book : retreiveAll()) {
            if(regex == null) return true;
            if (book.getAuthor().matches(".*" + regex + ".*") ||
                    book.getTitle().matches(".*" + regex + ".*") ||
                    (book.getSize() != null && book.getSize().toString().matches(".*" + regex + ".*")) ||
                    (book.getId() != null && book.getId().toString().matches(".*" + regex + ".*"))){
                logger.info("remove book completed: " + book);
                repo.remove(book);
            }
        }
        return true;
    }
}
