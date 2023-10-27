package org.accio.Library.Management.System.Controllers;

import org.accio.Library.Management.System.Entities.Author;
import org.accio.Library.Management.System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<Object> addAuthor(@RequestBody Author author) {
        try {
            int authorId = authorService.addAuthor(author);

            return new ResponseEntity<>("The author has been added to DB and assigned the authorId: " + authorId, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-author/{authorId}")
    public ResponseEntity<Object> getAuthorById(@PathVariable Integer authorId) {
        try {
            Author author = authorService.getAuthorById(authorId);

            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-names")
    public List<String> getAllAuthorNames() {
        return authorService.getAllAuthorNames();
    }

    @GetMapping("/list-of-books-by/{authorId}")
    public ResponseEntity<Object> getBooks(@PathVariable("authorId") Integer authorId) {
        try {
            List<String> bookNameList = authorService.getBooks(authorId);
            return new ResponseEntity<>(bookNameList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}