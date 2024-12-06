# Hospital Management System - Citas

Este proyecto es una aplicación para la gestión de citas médicas en un hospital. Permite crear, editar, y cancelar citas, así como ver la lista de todas las citas existentes. La aplicación está construida utilizando **Spring Boot**, **Thymeleaf** para la interfaz de usuario y **MySQL** como base de datos.

## Requisitos previos

Antes de ejecutar la aplicación, asegúrate de tener instalados los siguientes programas:

- **Java 17** o superior (Recomendado: JDK 17)
- **Maven** (para gestionar dependencias y ejecutar el proyecto)
- **MySQL** (como base de datos)

## Configuración de la base de datos

1. **Crear base de datos en MySQL**:
    - Abre tu consola de MySQL y crea una base de datos llamada `hospital`:
      ```sql
      CREATE DATABASE hospital;
      ```

2. **Configurar las credenciales de la base de datos**:
    - Abre el archivo `src/main/resources/application.properties` y asegúrate de que los siguientes parámetros estén configurados correctamente:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/hospital
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.format_sql=true
      ```

    - Reemplaza `your_username` y `your_password` con las credenciales correspondientes a tu instalación de MySQL.

## Configuración del proyecto

1. **Clonar el repositorio**:
   Si aún no tienes el proyecto, puedes clonar el repositorio usando el siguiente comando en tu terminal:
   ```bash
   git clone https://github.com/tu_usuario/hospital.git

2. **Instalar las Dependencias**;
   En la raíz del proyecto, ejecuta el siguiente comando para instalar las dependencias necesarias:

   ```bash
   mvn install

## Ejecución de la Aplicación
1. **Ejecutar el Servidor**;

   Para ejecutar la aplicación, usa el siguiente comando:

   ```bash
   mvn spring-boot:run

Esto iniciará el servidor en el puerto 8080 de manera predeterminada. Puedes acceder a la aplicación en tu navegador usando:

   http://localhost:8080
   
## Funcionalidades
1. **Ver Citas**
   Puedes visualizar la lista de citas en la página principal.

2. **Crear una Cita**
   Para crear una nueva cita, selecciona el doctor y el consultorio disponible, y luego ingresa la hora.

3. **Editar una Cita**
   Puedes modificar los datos de una cita existente.

4. **Cancelar una Cita**
   Puedes cancelar una cita previamente agendada.

## Estructura del Proyecto

La estructura de carpetas del proyecto es la siguiente:

|hospital
│
├── /src
│   ├── /main
│   │   ├── /java
│   │   │   └── /com
│   │   │       └── /test
│   │   │           └── /hospital
│   │   │               ├── /controller
│   │   │               │   └── CitaController.java
│   │   │               ├── /model
│   │   │               │   └── Cita.java
│   │   │               ├── /repository
│   │   │               │   ├── CitaRepository.java
│   │   │               │   ├── DoctorRepository.java
│   │   │               │   └── ConsultorioRepository.java
│   │   │               ├── /service
│   │   │               │   └── CitaService.java
│   │   │               └── /HospitalApplication.java
│   │   ├── /resources
│   │   │   ├── application.properties
│   │   │   ├── /templates
│   │   │   │   ├── /citas
│   │   │   │   │   ├── crear.html
│   │   │   │   │   ├── editar.html
│   │   │   │   │   └── lista.html
│   │   │   └── /static
│   └── /test
│       ├── /java
│       │   └── /com
│       │       └── /test
│       │           └── /hospital
│       │               └── HospitalApplicationTests.java
│
└── /pom.xml
   
## Descripción de Carpetas y Archivos
**/controller**: Contiene las clases responsables de manejar las solicitudes HTTP (por ejemplo, CitaController).

**/model**: Contiene las entidades del sistema, como Cita, Doctor, Consultorio.

**/repository**: Contiene las interfaces de repositorios para acceder a la base de datos.

**/service**: Contiene la lógica de negocio, como CitaService, que maneja la creación, edición y validación de citas.

**/templates**: Contiene los archivos HTML con la interfaz de usuario, procesados por Thymeleaf.

**/static**: Contiene archivos estáticos (CSS, imágenes, JS) si es necesario.

**/test**: Contiene las pruebas unitarias de la aplicación.
