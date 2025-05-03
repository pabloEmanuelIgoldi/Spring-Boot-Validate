package com.validate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {

	@Schema(description = "ID unico del producto.")
	@Positive(message = "El ID debe ser mayor que cero.")
	private Long id;
	@Schema(description = "Nombre del producto.")
	@NotEmpty(message = "El nombre no puede estar vacio.")
	private String nombre;
	@Schema(description = "Precio del producto.")
	@Positive(message = "El precio debe ser mayor que cero.")
	private Double precio;
}
