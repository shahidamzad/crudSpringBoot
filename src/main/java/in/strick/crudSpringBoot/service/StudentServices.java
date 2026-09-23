package in.strick.crudSpringBoot.service;

import in.strick.crudSpringBoot.entity.Student;
import in.strick.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static in.strick.crudSpringBoot.repository.StudentRepository.*;

@Service
public class StudentServices {

    private StudentRepository studentRepository;

    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {

        Student studentResp = studentRepository.save(studentReq);

        return studentResp;
    }

    // get one Student logic
    public Student getStudent(Long id) {
      Optional<Student> studentResp =  studentRepository.findByIdAndDeletedIsFalse(id);

      if(studentResp.isPresent()) {
          return studentResp.get();
      }
      return null;

    }


    // get all student logic
    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findAllByDeletedIsFalse();
        return studentList;
    }

    // update logic
    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setEmail(studentReq.getEmail());

        studentToSave.setDeleted(false);

       return studentRepository.save(studentToSave);

    }


    // delete Student
    public boolean deleteStudent(Long id) {

        boolean isStudent = studentRepository.existsById(id);

        if(!isStudent) return  false;
        studentRepository.deleteById(id);

        return true;

    }


    // temporary delete
    public Boolean deleteStudentSoftly(Long id) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

       if (existingStudent.isEmpty()) {
           return false;
       }
      Student studentToSave =  existingStudent.get();
       studentToSave.setDeleted(true);

       studentRepository.save(studentToSave );

       return true;
    }
}
