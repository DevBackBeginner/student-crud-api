package com.spring.students.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.students.dto.StudentRequestDto;
import com.spring.students.dto.StudentResponseDto;
import com.spring.students.dto.response.ApiResponse;
import com.spring.students.mapper.StudentsMapper;
import com.spring.students.model.Students;
import com.spring.students.service.StudentsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("students")
public class StudentsController {
	
	@Autowired
	StudentsService studentsService;
	
	@GetMapping
	public ResponseEntity<?> getStudents(){
		List<Students> students = studentsService.getAllStudents();
		
		if (students.isEmpty()) {
			ApiResponse<List<Students>> response = new ApiResponse<>("Lista vacia", students);
			return ResponseEntity.ok(response);
		}
		
		List<StudentResponseDto> responseList = students.stream()
			    .map(StudentsMapper::toResponseDto)
			    .toList();

		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Listado de estudiantes", responseList));
	}
	
	
	@GetMapping("/{email}")
	public ResponseEntity<ApiResponse<StudentResponseDto>> getStudent(@PathVariable String email){		
		return studentsService.getStudent(email)
		        .map(student -> {
		        	StudentResponseDto dto = StudentsMapper.toResponseDto(student);
		        	return ResponseEntity.ok(new ApiResponse<>("Estudiante encontrado", dto));
		        })
		        .orElseGet(() -> 
		            ResponseEntity
		                .status(HttpStatus.NOT_FOUND)
		                .body(new ApiResponse<>("Estudiante no encontrado", null))
		        );
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequestDto  dto){
		boolean existEmail = studentsService.getStudent(dto.getEmail()).isPresent();
		
		if (existEmail) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>("El correo ya ha sido registrado", null));
		}
		
		Students student = StudentsMapper.toEntity(dto);
		Students created= studentsService.createStudent(student);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/students/{email}").buildAndExpand(student.getEmail()).toUri();
		
		StudentResponseDto responseDto = StudentsMapper.toResponseDto(created);
		ApiResponse<StudentResponseDto> response = new ApiResponse<>("Estudiante creado con exito", responseDto);
		
		return ResponseEntity.created(location).body(response);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<StudentResponseDto>> putStudent(@RequestBody Students student){
		return studentsService.putStudent(student)
				.map(s -> {
					StudentResponseDto dto = StudentsMapper.toResponseDto(s);
					return ResponseEntity.ok(new ApiResponse<>("El estudiante sido actualizado correctamente", dto));
				})
				.orElseGet(() -> ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ApiResponse<>("El estudiante no ha sido encontrado", null)));
	}
	
	@PatchMapping("/patch")
	public ResponseEntity<ApiResponse<StudentResponseDto>> patchStudent(@RequestBody Students student){
		return studentsService.patchStudent(student)
				.map(s -> {
					StudentResponseDto dto = StudentsMapper.toResponseDto(s);
					return ResponseEntity.ok(new ApiResponse<>("El estudiante actualizado correctamente", dto));
				})
				.orElseGet(() -> ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ApiResponse<>("El estudiante no ha sido encontrado", null)));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse<StudentResponseDto>> deleteStudent(@PathVariable int id){
		return studentsService.deleteStudent(id)
				.map(s -> {
					StudentResponseDto dto = StudentsMapper.toResponseDto(s);
					return ResponseEntity.ok(new ApiResponse<>("El estudiante ha sido eliminado correctamente", dto));
				})
				.orElseGet(() -> ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ApiResponse<>("El estudiante no ha sido encontrado", null)));
	}
}
