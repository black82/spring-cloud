package com.black.bookservice;


import com.black.bookservice.model.Book;
import com.black.bookservice.model.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final BookService bookService;

    @Autowired
    public DataLoader(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        this.bookService.createBook(new Book(1L,"Aldous Huxley", "Brave new world"));
        this.bookService.createBook(new Book(2L,"George Orwell", "Animal Farm"));
    }
}
