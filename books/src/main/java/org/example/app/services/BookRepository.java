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
    public boolean removeItem(Integer bookIdToRemove, String bookAuthorToRemove, String bookTitleToRemove, Integer bookSizeToRemove) {
        for (Book book : retreiveAll()) {
            if(bookIdToRemove == null & bookAuthorToRemove.equals("") & bookTitleToRemove.equals("") & bookSizeToRemove == null) return true;
            if ((book.getId().equals(bookIdToRemove) || bookIdToRemove == null) & (book.getAuthor().equals(bookAuthorToRemove) || bookAuthorToRemove.equals("")) & (book.getTitle().equals(bookTitleToRemove) || bookTitleToRemove.equals("")) & (book.getSize().equals(bookSizeToRemove) || bookSizeToRemove == null)) {
                logger.info("remove book completed: " + book);
                repo.remove(book);
            }
        }
        return true;
    }
}
