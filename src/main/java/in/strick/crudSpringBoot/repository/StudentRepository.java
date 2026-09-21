package in.strick.crudSpringBoot.repository;

import in.strick.crudSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// @Repository
public interface StudentRepository extends JpaRepository<Student,Long> {



}
