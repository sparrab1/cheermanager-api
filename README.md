# CheerManager API

API REST desarrollada con Java y Spring Boot para la gestión de información básica de deportistas de un equipo de Cheerleading.

## Descripción

CheerManager API permite consultar y registrar información básica de los deportistas pertenecientes a un equipo de Cheerleading.

El proyecto fue desarrollado para aplicar conceptos fundamentales de desarrollo de APIs REST, incluyendo:

- Métodos HTTP GET y POST.
- Parámetros mediante PathVariable.
- Parámetros mediante RequestParam.
- Recepción de información JSON mediante RequestBody.
- Uso de DTO mediante record.
- Uso de ResponseEntity.
- Manejo de códigos de respuesta HTTP.
- Organización del código mediante Controller, Service, Model y DTO.

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Maven
- Visual Studio Code
- Git
- GitHub

## Estructura del proyecto


src/main/java/com/cheermanager/api
│
├── controller
│   └── DeportistaController.java
├── dto
│   └── DeportistaRequest.java
├── model
│   └── Deportista.java
├── service
│   └── DeportistaService.java
└── CheermanagerApiApplication.java

## Requisitos para ejecutar el proyecto
 
Para ejecutar el proyecto se requiere:
 
Java 21 o superior.
Maven Wrapper incluido en el proyecto.
 
No es necesario instalar Maven de forma independiente, ya que el proyecto incluye Maven Wrapper.

## Ejecución

Windows

Desde la carpeta raíz del proyecto ejecutar:

.\mvnw.cmd spring-boot:run

La aplicación se ejecutará en:

http://localhost:8080

## Endpoints disponibles

1. Obtener todos los deportistas

Método: GET

GET /api/deportistas

Ejemplo:

http://localhost:8080/api/deportistas

Respuesta:

[
  {
    "id": 1,
    "nombre": "Valentina",
    "apellido": "Gómez",
    "edad": 13,
    "categoria": "Junior",
    "nivel": "Nivel 2",
    "posicion": "Flyer",
    "estado": "ACTIVO"
  }
]
2. Obtener un deportista por ID

Método: GET

PathVariable: id

GET /api/deportistas/{id}

Ejemplo:

http://localhost:8080/api/deportistas/1

Si el deportista existe, la API retorna:

200 OK

Si no existe:

404 Not Found

3. Buscar deportistas por categoría

Método: GET

RequestParam: categoria

GET /api/deportistas/buscar?categoria=Junior

Ejemplo:

http://localhost:8080/api/deportistas/buscar?categoria=Junior

Este endpoint permite filtrar los deportistas según su categoría.

4. Registrar un nuevo deportista

Método: POST

POST /api/deportistas

El endpoint recibe información en formato JSON mediante RequestBody.

Ejemplo:

{
  "nombre": "Camila",
  "apellido": "López",
  "edad": 14,
  "categoria": "Junior",
  "nivel": "Nivel 2",
  "posicion": "Tumbler"
}

Respuesta:

201 Created

Ejemplo de respuesta:

{
  "id": 4,
  "nombre": "Camila",
  "apellido": "López",
  "edad": 14,
  "categoria": "Junior",
  "nivel": "Nivel 2",
  "posicion": "Tumbler",
  "estado": "ACTIVO"
}

## DTO

Para recibir la información enviada mediante el endpoint POST se utiliza el record:

public record DeportistaRequest(
        String nombre,
        String apellido,
        int edad,
        String categoria,
        String nivel,
        String posicion
) {
}

El DTO permite definir los datos que el cliente puede enviar para registrar un deportista.

El id y el estado son gestionados por la aplicación.

## Respuestas HTTP

La API utiliza códigos HTTP coherentes con cada operación:

Código	Descripción
200 - OK - Solicitud procesada correctamente
201 - Created -	Deportista creado correctamente
404 - Not Found - Deportista no encontrado

## Consideraciones

Actualmente los datos se almacenan temporalmente en memoria mediante una lista dentro del Service.

Por esta razón, los datos registrados mediante POST se mantienen mientras la aplicación esté ejecutándose. Al reiniciar la aplicación se cargan nuevamente los datos iniciales.

El proyecto está planteado como una API REST básica y puede ser ampliado posteriormente para incorporar funcionalidades como:

- Gestión de pagos de mensualidades.
- Control de pagos de uniformes.
- Registro de torneos.
- Control de asistencia.
- Persistencia mediante una base de datos.