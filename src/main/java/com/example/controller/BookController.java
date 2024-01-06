package com.example.controller;

import com.example.DTO.BookDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {
    private List<BookDTO> bookList = new LinkedList<>();

    public BookController() {
        BookDTO book1 = new BookDTO();
        book1.setId(UUID.randomUUID().toString());
        book1.setTitle("AAAAA");
        book1.setAuthor("BBBBBB");
        book1.setPublishYear(LocalDate.now());
        bookList.add(book1);

        BookDTO book2 = new BookDTO();
        book2.setId(UUID.randomUUID().toString());
        book2.setTitle("DDDD");
        book2.setAuthor("CCCC");
        book2.setPublishYear(LocalDate.now());
        bookList.add(book2);

        BookDTO book3 = new BookDTO();
        book3.setId(UUID.randomUUID().toString());
        book3.setTitle("MMMM");
        book3.setAuthor("NNNN");
        book3.setPublishYear(LocalDate.now());
        bookList.add(book3);

    }

    @PostMapping("/create")   //POST
    public Boolean createdStudent(@RequestBody BookDTO book) {
        book.setId(UUID.randomUUID().toString());
        book.setPublishYear(LocalDate.now());
        bookList.add(book);
        return true;
    }

    @GetMapping("/all")    //GET
    public List<BookDTO> getAll() {
        return bookList;
    }

    @GetMapping("/{id}")    //GET
    public BookDTO getBookId(@PathVariable("id") String id) {
        for (BookDTO bookDTO : bookList) {
            if (bookDTO.getId().equals(id)) {
                return bookDTO;
            }
        }
        return null;
    }

    @PutMapping("/update/{id}")   //PUT
    public Boolean updateBook(@RequestBody BookDTO book, @PathVariable("id") String id) {
        for (BookDTO bookDTO : bookList) {
            if (bookDTO != null) {
                if (bookDTO.getId().equals(id)) {
                    bookDTO.setTitle(book.getTitle());
                    bookDTO.setAuthor(book.getAuthor());
                    return true;
                }
            }
        }
        return false;
    }

    @DeleteMapping("/delete/{id}")  //DELETE
    public Boolean deleteBook(@PathVariable("id") String id) {
        return bookList.removeIf(bookDTO -> bookDTO.getId().equals(id));
    }


}
