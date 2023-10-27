package org.accio.Library.Management.System.Repositories;

import org.accio.Library.Management.System.Entities.Book;
import org.accio.Library.Management.System.Enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByGenre(Genre genre);
}