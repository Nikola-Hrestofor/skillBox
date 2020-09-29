package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();
    List<T> retreiveByParams(String bookAuthor, String bookTitle, Integer bookSize);

    void store(T book);

    boolean removeItem(Integer bookIdToRemove, String bookAuthorToRemove, String bookTitleToRemove, Integer bookSizeToRemove);
}
