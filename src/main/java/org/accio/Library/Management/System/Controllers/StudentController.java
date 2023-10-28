package org.accio.Library.Management.System.Controllers;

import org.accio.Library.Management.System.Entities.Student;
import org.accio.Library.Management.System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);

        return "The student has been added to DB";
    }
}