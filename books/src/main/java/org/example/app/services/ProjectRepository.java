package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();
    List<T> retreiveByParams(String regexToRemove);

    void store(T book);

    boolean removeItem(String regexToRemove);
}
