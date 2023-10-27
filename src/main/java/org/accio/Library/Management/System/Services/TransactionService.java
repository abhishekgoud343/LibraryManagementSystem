package org.accio.Library.Management.System.Services;

import org.accio.Library.Management.System.Entities.Book;
import org.accio.Library.Management.System.Entities.LibraryCard;
import org.accio.Library.Management.System.Entities.Transaction;
import org.accio.Library.Management.System.Enums.CardStatus;
import org.accio.Library.Management.System.Enums.TransactionStatus;
import org.accio.Library.Management.System.Exceptions.*;
import org.accio.Library.Management.System.Repositories.BookRepository;
import org.accio.Library.Management.System.Repositories.CardRepository;
import org.accio.Library.Management.System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    private static final int MAX_LIMIT_OF_BOOKS = 3;
    private static final int MAX_DAYS = 15;
    private static final int FINE_PER_DAY = 5;

    public int issueBook(Integer cardId, Integer bookId) throws Exception {
        //checking validations
         Optional<LibraryCard> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty())
            throw new CardNotFoundException();
        LibraryCard card = optionalCard.get();

        if (card.getCardStatus() != CardStatus.ACTIVE)
            throw new InvalidCardStatusException();

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty())
            throw new BookNotFoundException();
        Book book = optionalBook.get();

        if (!book.isAvailable())
            throw new BookNotAvailableException("This book is currently not available for issue");

        if (card.getNoOfBooksIssued() == MAX_LIMIT_OF_BOOKS)
            throw new MaxBooksAlreadyIssuedException("The max book limit is reached: " + MAX_LIMIT_OF_BOOKS);

        //creating Txn entity
        Transaction transaction = new Transaction();

        //setting attributes
        transaction.setTransactionStatus(TransactionStatus.ISSUED);
//        transaction.setIssueDate(new Date());
        card.setNoOfBooksIssued(card.getNoOfBooksIssued() + 1);
        book.setAvailable(false);

        //setting FKs
        transaction.setBook(book);
        transaction.setCard(card);

        //saving relevant entities
        card.getTransactionList().add(transaction);
        book.getTransactionList().add(transaction);

        //saving (the child entity instead of the parent entities, as there are multiple parent entities, to avoid multiple saves)
        transactionRepository.save(transaction);

        return transaction.getTransactionId();
    }

    public String returnBook(Integer cardId, Integer bookId) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty())
            throw new BookNotFoundException();
        Book book = optionalBook.get();

        Optional<LibraryCard> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty())
            throw new CardNotFoundException();
        LibraryCard card = optionalCard.get();

        Optional<Transaction> optionalTransaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book, card, TransactionStatus.ISSUED);
        if (optionalTransaction.isEmpty())
            throw new TransactionNotFoundException();
        Transaction transaction = optionalTransaction.get();

        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setReturnDate(new Date());

        Date date = transaction.getCreatedOn();
        long milliseconds = Math.abs(System.currentTimeMillis() - date.getTime());
        int days = (int) TimeUnit.DAYS.convert(milliseconds, TimeUnit.MILLISECONDS);
        int excess_days = MAX_DAYS - days;

        int fineAmt = 0;
        if (excess_days > 0) fineAmt = excess_days * FINE_PER_DAY;
        transaction.setFine(fineAmt);
        card.setFine(card.getFine() + fineAmt);

        card.setNoOfBooksIssued(card.getNoOfBooksIssued() - 1);
        book.setAvailable(true);

        transactionRepository.save(transaction);

        String res = "The book return is complete";
        if (excess_days > 0) res += ". A fine amount of " + fineAmt + " should be paid due to late return by " + excess_days + " days";

        return res;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}