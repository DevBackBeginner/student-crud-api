package com.spring.students.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentRequestDto {
	
	@NotBlank(message = "El nombre es obligatorio")
	private String name;
	
	@Email(message = "Debe tener el formato valido")
	@NotBlank(message = "El correo es obligatorio")
	private String email;
	
	@NotNull(message = "La edad es obligatorio")
	@Min(value = 15, message ="Edad minima 15 años")
	@Max(value = 100, message = "Edad maxima 100 años")
	private Integer edad;
	
	@NotNull(message = "El curso es obligatorio")
	private Integer curso;

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
