package org.accio.Library.Management.System.Services;

import org.accio.Library.Management.System.Entities.Author;
import org.accio.Library.Management.System.Entities.Book;
import org.accio.Library.Management.System.Exceptions.AuthorNotFoundException;
import org.accio.Library.Management.System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public int addAuthor(Author author) {
        authorRepository.save(author);

        return author.getAuthorId();
    }

    public Author getAuthorById(Integer authorId) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isEmpty())
            throw new AuthorNotFoundException();

        return optionalAuthor.get();
    }

    public List<String> getAllAuthorNames() {
        List<Author> authorList = authorRepository.findAll();

//        if (authorList.isEmpty())
//            throw new AuthorListEmptyException();

        List<String> res = new ArrayList<>();
        for (Author author : authorList)
            res.add(author.getAuthorName());

        return res;
    }

    public List<String> getBooks(Integer authorId) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isEmpty())
            throw new AuthorNotFoundException();
        Author author = optionalAuthor.get();

        List<Book> bookList = author.getBookList();

        List<String> bookNames = new ArrayList<>();
        for (Book book : bookList)
            bookNames.add(book.getBookName());

        return bookNames;
    }
}