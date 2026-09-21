package in.strick.crudSpringBoot.controller;

import in.strick.crudSpringBoot.entity.Student;
import in.strick.crudSpringBoot.service.StudentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    // create student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

     Student createdStudent =  studentServices.createStudent(student);

     return ResponseEntity.status(HttpStatus.CREATED)
             .body(createdStudent);
    }

    // read one student

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student studentResp = studentServices.getStudent(id);
        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    // real all / get all  student
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
      List  <Student> studentList = studentServices.getAllStudent();
        if(studentList== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }


    // update  student
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentReq) {
        Student studentResp = studentServices.updateStudent(id, studentReq);
        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    // delete

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <String > deleteStudent(@PathVariable Long id) {

      boolean isDeleted =   studentServices.deleteStudent(id);

      if(!isDeleted) {
          return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok("Record deleted");

    }

}
