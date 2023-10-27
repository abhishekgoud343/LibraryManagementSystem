package org.accio.Library.Management.System.Services;

import org.accio.Library.Management.System.Entities.Author;
import org.accio.Library.Management.System.Entities.Book;
import org.accio.Library.Management.System.Enums.Genre;
import org.accio.Library.Management.System.Exceptions.AuthorNotFoundException;
import org.accio.Library.Management.System.Exceptions.GenreNotFoundException;
import org.accio.Library.Management.System.Repositories.AuthorRepository;
import org.accio.Library.Management.System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public int addBook(Book book, Integer authorId) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isEmpty())
            throw new AuthorNotFoundException();
        Author author = optionalAuthor.get();

        book.setAuthor(author);

        author.getBookList().add(book);

        authorRepository.save(author);

        return book.getBookId();
    }

    public List<String> getBooksByGenre(Genre genre) throws GenreNotFoundException {
        if (genre == null)
            throw new GenreNotFoundException("Genre cannot be null");

        List<Book> bookList = bookRepository.findBooksByGenre(genre);

        return bookList.stream().map(Book::getBookName).collect(Collectors.toList());
    }
}