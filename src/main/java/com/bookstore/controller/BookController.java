package com.bookstore.controller;

import com.bookstore.dtos.BookDTO;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addNewBook")
    public ResponseEntity<BookDTO> createNewBook(@RequestBody BookDTO bookDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createNewBook(bookDTO));
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable(value = "bookId")Long bookId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBookById(bookId));
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable(value = "bookId")Long bookId,@RequestBody BookDTO bookDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookId,bookDTO));
    }

    @GetMapping("/get/{bookId}")
    public ResponseEntity<BookDTO> getSingleBook(@PathVariable(value = "bookId")Long bookId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(bookId));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }
}
