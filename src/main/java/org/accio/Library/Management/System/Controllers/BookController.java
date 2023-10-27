package org.accio.Library.Management.System.Controllers;

import org.accio.Library.Management.System.Entities.Book;
import org.accio.Library.Management.System.Enums.Genre;
import org.accio.Library.Management.System.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId) {
        try {
            int bookId = bookService.addBook(book, authorId);

            return new ResponseEntity<>("The book has been added to DB and assigned the bookId: " + bookId, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<Object> getBooksByGenre(@RequestParam("genre") Genre genre) {
        try {
            List<String> bookList = bookService.getBooksByGenre(genre);

            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}