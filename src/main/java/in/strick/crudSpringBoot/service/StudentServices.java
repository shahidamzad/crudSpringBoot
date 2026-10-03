package in.strick.crudSpringBoot.service;

import in.strick.crudSpringBoot.dto.CreateStudentRequestDTO;
import in.strick.crudSpringBoot.dto.CreateStudentResponseDto;
import in.strick.crudSpringBoot.dto.UpdateStudentRequestDto;
import in.strick.crudSpringBoot.entity.Student;
import in.strick.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    private StudentRepository studentRepository;

    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDTO createStudentRequestDTO) {

        Student student = mapToEntity(createStudentRequestDTO);

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

      Student studentResp =   studentRepository.save(student);

      return mapToDto(studentResp);

    }

    // get one Student logic
    public CreateStudentResponseDto getStudent(Long id) {
      Optional<Student> studentResp =  studentRepository.findByIdAndDeletedIsFalse(id);

      if(studentResp.isPresent()) {
          return mapToDto(studentResp.get());
      }
      return null;

    }


    // get all student logic
    public List<CreateStudentResponseDto> getAllStudent() {
        List<Student> studentList = studentRepository.findAllByDeletedIsFalse();
        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }

    // update logic
    public UpdateStudentRequestDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
        studentToSave.setUpdatedAt(LocalDateTime.now());

       Student savedStudent =  studentRepository.save(studentToSave);

       return mapToUpdateDto(savedStudent);

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


    private Student mapToEntity(CreateStudentRequestDTO createStudentRequestDTO){
    Student student = new Student();

    student.setName(createStudentRequestDTO.getName());
    student.setAge(createStudentRequestDTO.getAge());
    student.setRollNo(createStudentRequestDTO.getRollNo());
    student.setSubject(createStudentRequestDTO.getSubject());
    student.setEmail(createStudentRequestDTO.getEmail());

    student.setDeleted(false);

    return student;


    }

    private CreateStudentResponseDto mapToDto(Student student){
        CreateStudentResponseDto responseDto = new CreateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setEmail(student.getEmail());
        responseDto.setMessage("Student save successfully");
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;


    }

    private UpdateStudentRequestDto mapToUpdateDto(Student student){
        UpdateStudentRequestDto responseDto = new UpdateStudentRequestDto();


        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());

        responseDto.setMessage("Student Updated successfully");

        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;

    }
}


