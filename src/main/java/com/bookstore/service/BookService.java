package com.bookstore.service;


import com.bookstore.dtos.BookDTO;

import java.util.List;

public interface BookService {

    //create Book
    BookDTO createNewBook(BookDTO bookDTO);

    //delete book
    public String deleteBookById(long bookId);

    //update book
    BookDTO updateBook(long bookId, BookDTO book);

    //get a single book by book id
    BookDTO getBookById(long bookId);

    //get all books
    List<BookDTO> getAllBooks();
}
