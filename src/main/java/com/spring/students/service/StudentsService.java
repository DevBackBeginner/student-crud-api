package com.spring.students.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import com.spring.students.model.Students;

@Service
public class StudentsService {
	
	private List<Students> students = new ArrayList<>(Arrays.asList(
		new Students(1, "Pablo", "pablo@gmail.com", 12, 640),
	    new Students(2, "Lucía", "lucia@hotmail.com", 13, 675),
	    new Students(3, "Juan", "juan123@yahoo.com", 11, 610),
	    new Students(4, "Camila", "camila.romero@gmail.com", 12, 700)
	));
	 
	public List<Students> getAllStudents(){
		return students;
	}
	
	public Optional<Students> getStudent(String email){
		for (Students s : students) {
			if (s.getEmail().equalsIgnoreCase(email)) {
				return Optional.of(s);
			}
		}
		return Optional.empty();
	}
	
	public Students createStudent(Students student){
		students.add(student);
		return student;
	}
	
	public Optional<Students> putStudent(Students student) {
		for (Students s : students) {
			if (s.getId() == student.getId()) {
				s.setName(student.getName());
				s.setEmail(student.getEmail());
				s.setEdad(student.getEdad());
				s.setCurso(student.getCurso());
				return Optional.of(s);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Students> patchStudent(Students student){
		for (Students s : students) {
			if (s.getId() == student.getId()) {
				if (student.getName() != null) {
					s.setName(student.getName());
				} 
				if (student.getEmail() != null) {
					s.setEmail(student.getEmail());
				} 
				if (student.getCurso() != null) {
				    s.setCurso(student.getCurso());
				}
				if (student.getCurso() != null) {
					s.setCurso(student.getCurso());
				}
				return Optional.of(s);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Students> deleteStudent(int id){
		for (Students s : students) {
			if (s.getId() == id) {
				students.remove(s);
				return Optional.of(s);
			}
		}
		return Optional.empty();
	}
}
