package org.accio.Library.Management.System.Controllers;

import org.accio.Library.Management.System.Entities.Transaction;
import org.accio.Library.Management.System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue-book")
    public ResponseEntity<Object> issueBook(@RequestParam("cardId") Integer cardId, @RequestParam("bookId") Integer bookId) {
        try {
            int transactionId = transactionService.issueBook(cardId, bookId);

            return new ResponseEntity<>("The book with bookId " + bookId + " has been issued to card with cardId " + cardId + ". The Txn Id for this transaction is: " + transactionId, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return-book")
    public ResponseEntity<Object> returnBook(@RequestParam("cardId") Integer cardId, @RequestParam("bookId") Integer bookId) {
        try {
            String result = transactionService.returnBook(cardId, bookId);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}