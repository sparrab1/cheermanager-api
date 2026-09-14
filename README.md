# CheerManager API

API REST desarrollada con Java y Spring Boot para la gestión de deportistas de un equipo de Cheerleading.

El proyecto permite registrar, consultar, actualizar y eliminar deportistas, utilizando persistencia de datos mediante JPA, Hibernate y una base de datos H2.

## Descripción del proyecto

**CheerManager API** es una API REST orientada a la administración de información básica de deportistas de Cheerleading.

El sistema permite gestionar información como:

- Nombre y apellido del deportista.
- Edad.
- Categoría.
- Nivel.
- Posición dentro del equipo.
- Estado del deportista.

La aplicación implementa una arquitectura basada en capas:

Cliente
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
JPA / Hibernate
   ↓
Base de datos H2

Esta estructura permite separar las responsabilidades de cada componente y facilita el mantenimiento y evolución del proyecto.

## Objetivo

Desarrollar una API REST utilizando Spring Boot que permita realizar operaciones CRUD sobre los deportistas y demostrar el uso de persistencia de datos mediante JPA, Hibernate y H2.

Además, se implementa una consulta personalizada para buscar deportistas por categoría.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Visual Studio Code
- Git
- GitHub

## Estructura del proyecto

cheermanager-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── cheermanager/
│   │   │           └── api/
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   └── DeportistaController.java
│   │   │               │
│   │   │               ├── dto/
│   │   │               │   └── DeportistaRequest.java
│   │   │               │
│   │   │               ├── model/
│   │   │               │   └── Deportista.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               │   └── DeportistaRepository.java
│   │   │               │
│   │   │               └── service/
│   │   │                   └── DeportistaService.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── data/
│   └── cheermanager.mv.db
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md

La carpeta data/ contiene los archivos generados por H2 para la persistencia local de los datos.

## Entidad Deportista

La entidad Deportista está mapeada como una entidad JPA mediante la anotación:

@Entity

Cuenta con un identificador generado automáticamente:

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

## Atributos

Campo		Tipo	Descripción
id		Long	Identificador único
nombre		String	Nombre del deportista
apellido	String	Apellido del deportista
edad		int	Edad
categoria	String	Categoría deportiva
nivel		String	Nivel del deportista
posicion	String	Posición dentro del equipo
estado		String	Estado del deportista

## Persistencia de datos

El proyecto utiliza:

- Spring Data JPA para facilitar el acceso a los datos.
- Hibernate como implementación ORM.
- H2 Database como base de datos.

La configuración se encuentra en:

src/main/resources/application.properties

Configuración utilizada:

spring.datasource.url=jdbc:h2:file:./data/cheermanager
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

La base de datos H2 se almacena como archivo local, permitiendo conservar los registros incluso después de detener y volver a iniciar la aplicación.

## Repositorio

El acceso a los datos se realiza mediante DeportistaRepository, que extiende de JpaRepository:

public interface DeportistaRepository extends JpaRepository<Deportista, Long> {

    List<Deportista> findByCategoriaIgnoreCase(String categoria);
}

Esto permite utilizar las operaciones CRUD proporcionadas por Spring Data JPA.

También se implementa una consulta personalizada para buscar deportistas por categoría:

findByCategoriaIgnoreCase(String categoria)

## Ejecución del proyecto

### Requisitos previos

Antes de ejecutar el proyecto se debe contar con:

- Java 21 instalado.
- Maven o Maven Wrapper.
- Git, si se desea trabajar con el repositorio.
- Visual Studio Code u otro IDE compatible con Java.

Para verificar Java:

java -version

La versión utilizada en el proyecto es:

Java 21

## Ejecutar la aplicación

Desde la carpeta raíz del proyecto:

Windows
.\mvnw.cmd spring-boot:run

La aplicación se ejecutará en:

http://localhost:8080

## Compilar el proyecto

Para verificar que el proyecto compile correctamente:

.\mvnw.cmd clean compile

También se puede ejecutar:

.\mvnw.cmd clean package

## Endpoints disponibles

La API utiliza la ruta base:

/api/deportistas

### 1. Crear un deportista

### POST

POST /api/deportistas

### Ejemplo de solicitud

{
  "nombre": "Sara",
  "apellido": "Parra",
  "edad": 21,
  "categoria": "Junior",
  "nivel": "Avanzado",
  "posicion": "Flyer"
}

### Respuesta esperada

HTTP 201 Created

{
  "id": 1,
  "nombre": "Sara",
  "apellido": "Parra",
  "edad": 21,
  "categoria": "Junior",
  "nivel": "Avanzado",
  "posicion": "Flyer",
  "estado": "ACTIVO"
}

El campo id es generado automáticamente por la base de datos.

El estado inicial del deportista se establece como:

ACTIVO

### 2. Consultar todos los deportistas

### GET

GET /api/deportistas

### Ejemplo de respuesta

HTTP 200 OK

[
  {
    "id": 1,
    "nombre": "Sara",
    "apellido": "Parra",
    "edad": 21,
    "categoria": "Junior",
    "nivel": "Avanzado",
    "posicion": "Flyer",
    "estado": "ACTIVO"
  }
]

### 3. Consultar un deportista por ID

### GET

GET /api/deportistas/{id}

### Ejemplo

GET /api/deportistas/1

### Respuesta exitosa

HTTP 200 OK

{
  "id": 1,
  "nombre": "Sara",
  "apellido": "Parra",
  "edad": 21,
  "categoria": "Junior",
  "nivel": "Avanzado",
  "posicion": "Flyer",
  "estado": "ACTIVO"
}

Si el deportista no existe:

HTTP 404 Not Found

### 4. Actualizar un deportista

### PUT

PUT /api/deportistas/{id}

### Ejemplo

PUT /api/deportistas/1

### Solicitud

{
  "nombre": "Sara",
  "apellido": "Parra",
  "edad": 22,
  "categoria": "Senior",
  "nivel": "Avanzado",
  "posicion": "Base"
}

### Respuesta

HTTP 200 OK

{
  "id": 1,
  "nombre": "Sara",
  "apellido": "Parra",
  "edad": 22,
  "categoria": "Senior",
  "nivel": "Avanzado",
  "posicion": "Base",
  "estado": "ACTIVO"
}

Si el deportista no existe:

HTTP 404 Not Found

### 5. Eliminar un deportista

### DELETE

DELETE /api/deportistas/{id}

### Ejemplo

DELETE /api/deportistas/2

Cuando la eliminación se realiza correctamente:

HTTP 204 No Content

Si el deportista no existe:

HTTP 404 Not Found

### 6. Buscar deportistas por categoría

La API incluye una consulta personalizada utilizando Spring Data JPA.

### GET

GET /api/deportistas/buscar?categoria={categoria}

### Ejemplo

GET /api/deportistas/buscar?categoria=Junior

La búsqueda no diferencia entre mayúsculas y minúsculas.

Por ejemplo:

Junior
junior
JUNIOR

son tratados como la misma categoría.

### Respuesta

HTTP 200 OK

[
  {
    "id": 1,
    "nombre": "Sara",
    "apellido": "Parra",
    "edad": 21,
    "categoria": "Junior",
    "nivel": "Avanzado",
    "posicion": "Flyer",
    "estado": "ACTIVO"
  }
]

## Resumen de códigos HTTP
Código		Uso
200 OK		Consulta o actualización realizada correctamente
201 Created	Deportista creado correctamente
204 No Content	Deportista eliminado correctamente
404 Not Found	El deportista solicitado no existe
