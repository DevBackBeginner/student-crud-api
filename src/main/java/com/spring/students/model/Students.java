package com.spring.students.model;

public class Students {

	private int id;
	private String name;
	private String email;
	private Integer edad;
	private Integer curso;
	
	public Students(int id, String name, String email, Integer edad, Integer curso) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.edad = edad;
		this.curso = curso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}
}
