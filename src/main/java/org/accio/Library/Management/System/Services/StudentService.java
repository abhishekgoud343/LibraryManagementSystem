package org.accio.Library.Management.System.Services;

import org.accio.Library.Management.System.Entities.Student;
import org.accio.Library.Management.System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void addStudent(Student student) {
        studentRepository.save(student);

        SimpleMailMessage mailMsg = new SimpleMailMessage();

        String body = "Hi, " + student.getName() + "!\n" +
                "You have been successfully registered for the library.";

        mailMsg.setFrom("abhi.acciojob@gmail.com");
        mailMsg.setTo(student.getEmailId());
        mailMsg.setSubject("Welcome to the college library!");
        mailMsg.setText(body);

        mailSender.send(mailMsg);
    }
}