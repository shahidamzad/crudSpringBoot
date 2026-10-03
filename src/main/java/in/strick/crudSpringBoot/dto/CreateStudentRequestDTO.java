package in.strick.crudSpringBoot.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {
// validations
    @NotBlank(message = "name can't be empty/null or blank")
    @Size(min = 2 , max= 50 , message = "Student name can be 2 to 50 character long")
    private String name;

    @NotBlank(message = "email is required ")
    @Email(message = "email must be valid")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 18 , message = "Student must be 18 year old")
    private Integer age;

    @NotNull(message = "Roll no is required")
    private Integer rollNo;

    @NotBlank(message = " subject is required")
    private String subject;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
