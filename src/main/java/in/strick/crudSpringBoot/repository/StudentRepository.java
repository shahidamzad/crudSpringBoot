package in.strick.crudSpringBoot.repository;

import in.strick.crudSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findAllByDeletedIsFalse();
}