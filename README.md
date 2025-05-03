# **Validate**

# Índice
### Definición del proyecto
### Tecnología aplicada
### Ventajas de la validación
### Tipos de parámetros
### Anotaciones de validación
### Documentación oficial
### Pruebas
### Índice de proyectos Spring Boot


#  **Definición del proyecto**
La aplicación es una demostración de como validar los parámetros enviados en las peticiones del cliente.

Además, habrá una lógica específica para el manejo de errores de los valores no válidos.

Si bien las validaciones se pueden realizar en distintas capas, en esta Api nos enfocaremos en la validación en la capa Controller.


#  **Tecnología aplicada**

La aplicación está desarrollada en Spring Boot 3.3.11 y Java 17.

Dependencias del proyecto: 
- spring-boot-starter-web: construye aplicaciones web.
- lombok: reduce el código repetitivo.
- spring-boot-starter-validation: facilita la validación de datos.
- springdoc-openapi-starter-webmvc-ui: permite documentación y prueba (swagger).

#  **Ventajas de la validación**
Alguna de las ventajas de validar parámetros y las gestión de los valores no válidos:
- Prevención de datos inválidos: evita corrupción de datos. Bloquea información malformada evitando problemas en capas profundas del sistema.
- Seguridad Mejorada: mitiga ataques comunes. Validando inputs previene inyecciones SQL, XSS, o manipulación de parámetros.
- Separa responsabilidades: la validación se centraliza, evitando lógica repetitiva en servicios.
- Mensajes personalizados: explica al usuario qué corregir  (ej: "El email debe ser válido").
- Resiliencia: fallos controlados. La app no crashea ante inputs inválidos; maneja errores elegante y recuperablemente.
- Errores específicos: sabes exactamente qué campo falló y por qué, acelerando la solución de bugs.



#  **Tipos de parámetros**

La validación de parámetros difiere si este parámetro es simple o en un objeto.

##  **Parámetros Simples(PathVariable)**
Son valores que no están agrupados dentro de ninguna estructura.

Se debe anotar la clase con @Validated para que funcione.

En cada parámetro que se necesita validar, se debe agregar la anotación de validación al lado del @PathVariable. Ejemplo: 

...nombreMetodo(@PathVariable @Positive Integer id)..

Con la anotación @Positive le decimos que el parámetro id tiene que ser positivo, no acepta 0 ni nulos.

Y en caso que no valide el valor, se lanza una excepción tipo ConstraintViolationException (importante para atraparla en otra instancia).


##  **Parámetros Objeto(RequestBody)**
Es una colección de atributos organizados jerárquicamente.

No es necesario anotar la clase con @Validated.

Es necesario agregar el @Valid al lado del @RequestBody.

Y dentro de la clase del parámetro objeto, validar los distintos atributos con las anotaciones de validación.

Y en caso que no valide el valor, se lanza una MethodArgumentNotValidException (importante para atraparla en otra instancia).

#  **Manejos de errores**
Se debe crear una clase que atrape y maneje los errores  y nos ayudara a estandarizar la respuesta.

Se debe anotar la clase con @RestControllerAdvice para identificar la clase que manejara los distintos errores.

Se tiene que crear un metodo que maneje cada excepcion con la anotacion:

@ExceptionHandler(NombreException.class)

#  **Anotaciones de validación**

Algunas de anotaciones de validación según tipo de datos:

- String
	* @NotBlank:	No permite null, ni cadenas vacías ("") ni solo espacios.
	* @NotEmpty:	No permite null ni cadenas vacías (pero sí espacios).
	* @Size(min=, max=):	Limita la longitud (ej: @Size(min = 3, max = 50)).
	* @Pattern(regexp=):	Valida contra una expresión regular (ej: ^[A-Z][a-z]+$).
	* @Email:	Valida formato de email.
- Números (int, double, BigDecimal)
	* @Min(value): Valor mínimo (ej: @Min(18)).
	* @Max(value): Valor máximo (ej: @Max(100)).
	* @DecimalMin(value):	Mínimo con valor en String (ej: @DecimalMin("0.01")).
	* @DecimalMax(value):	Máximo con valor en String (ej: @DecimalMax("99.99")).
	* @Positive: Número > 0.
	* @PositiveOrZero: Número ≥ 0.
	* @Negative: Número < 0.
	* @NegativeOrZero: Número ≤ 0.
- Fechas 
	* @Past: Fecha debe ser en el pasado.
	* @Future: Fecha debe ser en el futuro.
- Colecciones 
	* @NotEmpty:	La colección no debe estar vacía (pero puede ser null).
	* @Size(min=, max=):	Limita el tamaño de la colección.
- Boolean
	* @AssertTrue: El campo debe ser true.
	* @AssertFalse: El campo debe ser false.

#  **Documentación oficial**
https://jakarta.ee/specifications/bean-validation/3.0/

#  **Pruebas**
Para probar la API se puede ingresar a:

http://localhost:8080/apiValidate/swagger-ui/index.html

http://localhost:8080/api-docs

#  **Índice de proyectos Spring Boot**

- [Response Uniforme](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Response-Wrapper)
- [LogBack](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Logback)
- [Profile](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Profile)
- [Spring Doc](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Swagger)
- [Validate](https://github.com/pabloEmanuelIgoldi/Spring-Boot-Validate)

