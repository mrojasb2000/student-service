package com.services.ms.student.app.infrastructure.adapters.input.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.ms.student.app.application.ports.input.StudentServicePort;
import com.services.ms.student.app.infrastructure.adapters.input.restapi.mapper.StudentRestMapper;
import com.services.ms.student.app.infrastructure.adapters.input.restapi.model.request.StudentCreateRequest;
import com.services.ms.student.app.infrastructure.adapters.input.restapi.model.response.StudentResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StudentRestAdapter {
    private final StudentServicePort servicePort;
    private final StudentRestMapper restMapper;

    @GetMapping("/students")
    public List<StudentResponse> findAll() {
        return restMapper.toStudentResponseList(servicePort.findAll());
    }
    
    @GetMapping("/students/{id}")
    public StudentResponse findById(@RequestParam Long id) {
        return restMapper.toStudentResponse(servicePort.findById(id));
    }
    
    @PostMapping("/students")
    public ResponseEntity<StudentResponse> save(@Valid @RequestBody StudentCreateRequest request) {
       return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toStudentResponse(
                    servicePort.save(restMapper.toStudent(request))));
    }
    
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @Valid @RequestBody StudentCreateRequest request) {
       return ResponseEntity.status(HttpStatus.OK)
                .body(restMapper.toStudentResponse(
                    servicePort.update(id, restMapper.toStudent(request))));
    }
    
    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable Long id){
        servicePort.deleteById(id);
    }
}
