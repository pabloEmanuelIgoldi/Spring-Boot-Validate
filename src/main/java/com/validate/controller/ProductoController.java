package com.validate.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validate.dto.ApiResponseDTO;
import com.validate.dto.ProductoDTO;
import com.validate.dto.ProductoRequestDTO;
import com.validate.exception.EntityNotFoundException;
import com.validate.util.CodeResponseUtil;
import com.validate.util.MensajeResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Productos.", description = "Manejo de operaciones para productos.")
@RestController
@RequestMapping("v1/productos")
@Validated
public class ProductoController {
	
	@Operation(summary = "Busqueda.", description = "Retorna todos los productos sin filtros.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa.") })
	@GetMapping
	public ResponseEntity<ApiResponseDTO<List<ProductoDTO>>> get() {
		List<ProductoDTO> data = this.mockProductos();
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(true, MensajeResponseUtil.SUCCESS,
				CodeResponseUtil.SUCCESS, LocalDateTime.now(), data));
	}

	@Operation(summary = "Busqueda.", description = "Retorna un producto filtrando por ID.")
	@Parameters({@Parameter(name = "id", description = "ID del Producto"),})
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
			@ApiResponse(responseCode = "404", description = "Entidad no encontrada.") })
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDTO<ProductoDTO>> getById(@PathVariable @Positive Long id) throws Exception {
		log.info("BUSCAR PRODUCTO POR ID: " + id + ".");
		ProductoDTO data = this.buscarProductoPorId(id);
		return ResponseEntity.status(HttpStatus.OK)
								.body(new ApiResponseDTO<>(true, MensajeResponseUtil.SUCCESS, CodeResponseUtil.SUCCESS, LocalDateTime.now(), data));
	}

	@Operation(summary = "Agregado.", description = "Agregar un nuevo producto ingresando todos sus atributos.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa.") })
	@PostMapping
	public ResponseEntity<ApiResponseDTO<String>> post(@Valid @RequestBody ProductoRequestDTO producto) {
		log.info("PRODUCTO NUEVO: " + producto.toString());
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(new ApiResponseDTO<>(true, MensajeResponseUtil.CREATED,
									 CodeResponseUtil.CREATED, LocalDateTime.now(), null));
	}

	@Operation(summary = "Actualizacion.", description = "Actualizar un producto con todos sus atributos.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
			@ApiResponse(responseCode = "404", description = "Entidad no existente.") })
	@PutMapping
	public ResponseEntity<ApiResponseDTO<String>> put(@RequestBody ProductoDTO producto) throws Exception {
		log.info("PRODUCTO NUEVO: " + producto.toString());
		this.buscarProductoPorId(producto.getId());
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(true, MensajeResponseUtil.SUCCESS,
					CodeResponseUtil.SUCCESS, LocalDateTime.now(), null));
	}

	@Operation(summary = "Eliminacion.", description = "Eliminar un producto por ID.")
	@Parameters({
	    @Parameter(name = "id", description = "ID del Producto"),	  
	})
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operacion exitosa."),
			@ApiResponse(responseCode = "404", description = "Entidad no existente.") })
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable @Positive Long id) throws Exception {
		log.info("ELIMINAR PRODUCTO POR ID: " + id + ".");
		this.buscarProductoPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(true, MensajeResponseUtil.SUCCESS,
					CodeResponseUtil.SUCCESS, LocalDateTime.now(), null));
	}
	
	private ProductoDTO buscarProductoPorId(Long id) throws Exception {
		List<ProductoDTO> productos = this.mockProductos();
		ProductoDTO producto = productos.stream().filter(p -> p.getId().equals(id)).findFirst()
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
		return producto;
	}

	private List<ProductoDTO> mockProductos() {
		List<ProductoDTO> productos = new ArrayList<>();
		productos.add(ProductoDTO.builder().id(234L).nombre("Latop Dell Inspiron 3535").precio(1245000.0).build());
		productos.add(ProductoDTO.builder().id(2111L).nombre("Placa red Pci Express").precio(32456.9).build());
		productos.add(ProductoDTO.builder().id(24679L).nombre("Disco SSD Kingston 480 GB").precio(32456.9).build());
		return productos;
	}

}
