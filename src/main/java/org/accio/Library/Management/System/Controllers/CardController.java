package org.accio.Library.Management.System.Controllers;

import org.accio.Library.Management.System.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/generate")
    public ResponseEntity<Object> generateCard() {
        int cardNo = cardService.generateCard();

        return new ResponseEntity<>("A new card is generated with the card no: " + cardNo, HttpStatus.OK);
    }

    @PutMapping("/associate")
    public ResponseEntity<Object> associateCard(@RequestParam("studentId") Integer studentId, @RequestParam("cardId") Integer cardNo) {
        try {
            cardService.associateCard(studentId, cardNo);

            return new ResponseEntity<>("The card with cardId " + cardNo + " has been associated to the student with studentId " + studentId, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}