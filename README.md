# Foro

Este proyecto es una API REST desarrollada con Spring para gestionar un foro en el que se pueden realizar consultas (tópicos). Además, implementa mecanismos de autenticación y autorización para controlar el acceso a las funcionalidades del foro.

## Características del Proyecto

- **Gestión de Entidades**:
  - Los usuarios pueden:
      - crear, listar, modificar y eliminar tópicos y repuestas.
      - crear, listar y modificar cursos.
      - crear, autenticar, listar y modificar su usuario.
  - Los admin pueden:
      - realizar las mismas acciones que los usuarios
      - listar eliminados y reactivar tópicos y respuestas.
      - eliminar, listar eliminados y reactivar cursos.
      - eliminar, listar eliminados, reactivar y cambiar rol de usuarios.
                      
- **Almacenamiento en Base de Datos:**
  - Los datos se almacenan en una base de datos local PostgreSQL.
  - Se realiza una normalización básica de los datos al almacenarlos.
    
- **Autenticación y Autorización**: Se implementa un sistema de autenticación y autorización utilizando JWT (JSON Web Tokens) para proteger las rutas y garantizar que solo los usuarios autorizados puedan realizar ciertas acciones.
  
- **Estructura y Organización del Proyecto**:
  - **Controllers**: Se utilizan controladores para gestionar las solicitudes HTTP y proporcionar respuestas.
  - **DTO (Data Transfer Objects)**: Se emplean DTOs para la manipulación y transferencia de datos entre las capas de la aplicación.
  - **Excepciones**: Se manejan las excepciones de manera centralizada para mejorar la experiencia del usuario y asegurar la estabilidad de la API.
  - **Validaciones**: Se realizan validaciones tanto en el servidor como en los DTOs para garantizar la integridad de los datos.
  - **Migraciones con Flyway**: Flyway se utiliza para gestionar las migraciones de la base de datos, permitiendo una evolución controlada del esquema.

## Tecnologías

- **Lenguaje de Programación:** Java (Spring Framework)
- **Spring Security**: Para la implementación de autenticación y autorización.
- **JWT (JSON Web Tokens)**: Para la autenticación basada en tokens.
- **Base de datos**: PostgreSQL.
- **Dependencias**: Flyway, DevTools, Lombok, Java-jwt, Spring Data JPA, Spring Security, Spring Docs, Spring Validation, PostgreSQL 
- **JPA/Hibernate**: Para la persistencia de los datos.

## Configuración del Proyecto

### Requisitos Previos

1. **Instalar Java 17 o superior.**
2. **PostgreSQL:** Asegúrate de tener PostgreSQL instalado y un servidor de base de datos en funcionamiento.
3. **Configuración del Archivo `application.properties`:** Actualiza las credenciales de acceso a tu base de datos en el archivo `application.properties`.
