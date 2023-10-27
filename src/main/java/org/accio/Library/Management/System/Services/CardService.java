package org.accio.Library.Management.System.Services;

import org.accio.Library.Management.System.Entities.LibraryCard;
import org.accio.Library.Management.System.Entities.Student;
import org.accio.Library.Management.System.Enums.CardStatus;
import org.accio.Library.Management.System.Repositories.CardRepository;
import org.accio.Library.Management.System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public int generateCard() {
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.NEW);

        cardRepository.save(card); //card obj updated with generated cardId

        return card.getCardNo();
    }

    public void associateCard(Integer studentId, Integer cardNo) throws Exception {
        Optional<LibraryCard> optionalCard = cardRepository.findById(cardNo);
        if (optionalCard.isEmpty())
            throw new Exception("The card number is invalid");
        LibraryCard card = optionalCard.get();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty())
            throw new Exception("The student Id is invalid");
        Student student = optionalStudent.get();

        //check if the card is already associated
        if (card.getStudent() != null)
            if (card.getStudent().equals(student))
                throw new Exception("The card has already been associated to this student");
            else
                throw new Exception("The card has already been associated to another studednt");

        if (student.getLibraryCard() != null)
            throw new Exception("The student already has a library card associated to them");

        //update libraryCard entity attributes
        card.setCardStatus(CardStatus.ACTIVE);
        card.setNameOnCard(student.getName());
        card.setStudent(student);

        //update student entity attributes
        student.setLibraryCard(card);

        studentRepository.save(student); //due to bidirectional mapping and the cascading effect that it provides, saving the parent entity (student) also affects changes in the child entity (libraryCard)
    }
}