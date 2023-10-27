package org.accio.Library.Management.System.Services;

import org.accio.Library.Management.System.Entities.Student;
import org.accio.Library.Management.System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student) {
        studentRepository.save(student);

        return "The student has been added to DB";
    }
}