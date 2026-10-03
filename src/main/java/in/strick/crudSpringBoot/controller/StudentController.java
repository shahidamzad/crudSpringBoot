package in.strick.crudSpringBoot.controller;

import in.strick.crudSpringBoot.dto.CreateStudentRequestDTO;
import in.strick.crudSpringBoot.dto.CreateStudentResponseDto;
import in.strick.crudSpringBoot.dto.UpdateStudentRequestDto;
import in.strick.crudSpringBoot.entity.Student;
import in.strick.crudSpringBoot.service.StudentServices;
import jakarta.validation.Valid;
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
    public ResponseEntity<CreateStudentResponseDto> createStudent(
          @Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO) {

        CreateStudentResponseDto createdStudent =  studentServices.createStudent(createStudentRequestDTO);

     return ResponseEntity.status(HttpStatus.CREATED)
             .body(createdStudent);
    }

    // read one student

    @GetMapping("/get")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@RequestParam Long id) {
        CreateStudentResponseDto  studentResp = studentServices.getStudent(id);
        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    // real all / get all  student
    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent() {
      List  <CreateStudentResponseDto> studentList = studentServices.getAllStudent();
        if(studentList== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }


    // update  student
    @PutMapping("/update")
    public ResponseEntity<UpdateStudentRequestDto> updateStudent(@RequestParam Long id, @RequestBody UpdateStudentRequestDto studentReq) {
        UpdateStudentRequestDto studentResp = studentServices.updateStudent(id, studentReq);
        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    // delete

    @DeleteMapping("/delete")
    public ResponseEntity <String > deleteStudent(@RequestParam Long id) {

        Boolean isDeleted =   studentServices.deleteStudent(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");

    }

    // softly deleted

    @PatchMapping("/delete-soft")
    public ResponseEntity <String > deleteStudentSoftly(@RequestParam Long id) {
        Boolean isDeleted =   studentServices.deleteStudentSoftly(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted softly");
    }

}
