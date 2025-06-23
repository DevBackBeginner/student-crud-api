package com.spring.students.mapper;

import com.spring.students.dto.StudentRequestDto;
import com.spring.students.dto.StudentResponseDto;
import com.spring.students.model.Students;

public class StudentsMapper {
	
	 public static StudentResponseDto toResponseDto(Students student) {
        return new StudentResponseDto(
            student.getName(),
            student.getEmail(),
            student.getEdad(),
            student.getCurso()
        );
    }

	 public static Students toEntity(StudentRequestDto dto) {
		 return new Students(0, dto.getName(), dto.getEmail(), dto.getEdad(), dto.getCurso());
	 }
}
