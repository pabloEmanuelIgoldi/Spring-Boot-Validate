package com.validate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductoRequestDTO {

	@Schema(description = "Nombre del producto.")
	@NotEmpty(message = "El nombre no puede estar vacio.")
	private String nombre;
	
	@Schema(description = "Precio del producto.", example = "123.9")
	@Positive(message = "El precio debe ser mayor que cero.")
	private Double precio;
}
