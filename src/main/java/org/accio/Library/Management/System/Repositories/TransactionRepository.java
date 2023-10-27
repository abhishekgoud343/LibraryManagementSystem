package org.accio.Library.Management.System.Repositories;

import org.accio.Library.Management.System.Entities.Book;
import org.accio.Library.Management.System.Entities.LibraryCard;
import org.accio.Library.Management.System.Entities.Transaction;
import org.accio.Library.Management.System.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus status);
}