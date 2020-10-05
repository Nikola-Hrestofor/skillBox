package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/filter")
    public String filterBook(Book book, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getBooksByParams(book.getAuthor(), book.getTitle(), book.getSize()));
        return "book_shelf";
    }


    @PostMapping("/save")
    public String saveBook(Book book) {
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove,
                             @RequestParam(value = "bookAuthorToRemove") String bookAuthorToRemove,
                             @RequestParam(value = "bookTitleToRemove") String bookTitleToRemove,
                             @RequestParam(value = "bookSizeToRemove") Integer bookSizeToRemove) {
        if (bookService.removeBook(bookIdToRemove, bookAuthorToRemove, bookTitleToRemove, bookSizeToRemove)) {
            return "redirect:/books/shelf";
        } else {
            return "book_shelf";
        }
    }
}
