package com.bookstore.service.impl;

import com.bookstore.dtos.BookDTO;
import com.bookstore.entities.Book;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private ModelMapper modelMapper;
    private BookRepository bookRepository;

    public BookServiceImpl(ModelMapper modelMapper, BookRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO createNewBook(BookDTO bookDTO) {
        //first we need to convert this dto into entity and then save it into repository.
        Book book = mapToEntity(bookDTO);
        Book newBook = bookRepository.save(book);
        return mapToDto(newBook);
    }

    @Override
    public String deleteBookById(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.deleteById(book.getId());
        return "Book Deleted Successfully...";
    }

    @Override
    public BookDTO updateBook(long bookId, BookDTO bookDTO) {
        Book bookFound = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Book book = mapToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return mapToDto(savedBook);
    }

    @Override
    public BookDTO getBookById(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return mapToDto(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        List<BookDTO> bookDTOS=allBooks.stream().map(book -> mapToDto(book)).collect(Collectors.toList());
        return bookDTOS;
    }

    //DTO to Entity conversion using model mapper
    private Book mapToEntity(BookDTO bookDTO){
        Book book=modelMapper.map(bookDTO,Book.class);
        return book;
    }

    //Entity To DTO conversion

    private BookDTO mapToDto(Book book){
        BookDTO bookDTO=modelMapper.map(book,BookDTO.class);

        return bookDTO;
    }
}
