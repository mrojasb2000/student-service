package com.services.ms.student.app.infrastructure.adapters.input.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.services.ms.student.app.domain.model.Student;
import com.services.ms.student.app.infrastructure.adapters.input.restapi.model.request.StudentCreateRequest;
import com.services.ms.student.app.infrastructure.adapters.input.restapi.model.response.StudentResponse;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StudentRestMapper {

    @Mapping(target = "id", ignore = true) // Reduntante
    Student toStudent(StudentCreateRequest request); // Rest model -> Domain model

    StudentResponse toStudentResponse(Student student); // Domain model -> Rest model

    List<StudentResponse> toStudentResponseList(List<Student> studentList);
}
