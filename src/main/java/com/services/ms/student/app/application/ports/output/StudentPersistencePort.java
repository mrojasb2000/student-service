package com.services.ms.student.app.application.ports.output;

import java.util.Optional;
import java.util.List;
import com.services.ms.student.app.domain.model.Student;

public interface StudentPersistencePort {
    Optional<Student> findById(Long id);
    List<Student> findAll();
    Student save(Student student);
    void deleteById(Long id);
}
