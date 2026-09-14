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
