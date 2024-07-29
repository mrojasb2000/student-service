package com.services.ms.student.app.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.services.ms.student.app.application.ports.output.StudentPersistencePort;
import com.services.ms.student.app.domain.model.Student;
import com.services.ms.student.app.infrastructure.adapters.output.persistence.mapper.StudentPersistenceMapper;
import com.services.ms.student.app.infrastructure.adapters.output.persistence.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentPersistencePort{

    private final StudentRepository studentRepository;
    private final StudentPersistenceMapper mapper;

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id)
                .map(mapper::toStudent);
    }

    @Override
    public List<Student> findAll() {
        return mapper.toStudentList(studentRepository.findAll());
    }

    @Override
    public Student save(Student student) {
       return mapper.toStudent(studentRepository.save(mapper.toStudentEntoEntity(student)));
    }

    @Override
    public void deleteById(Long id) {
       studentRepository.deleteById(id);
    }
    
}
