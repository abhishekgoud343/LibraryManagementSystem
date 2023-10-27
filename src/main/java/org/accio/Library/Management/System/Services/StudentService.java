package org.accio.Library.Management.System.Services;

import org.accio.Library.Management.System.Entities.Author;
import org.accio.Library.Management.System.Entities.Book;
import org.accio.Library.Management.System.Entities.Student;
import org.accio.Library.Management.System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student has been added";
    }
}