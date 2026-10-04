# Ferretería

Proyecto de práctica para aprender **Spring Boot** y desarrollar una aplicación web de gestión de productos. La idea es trabajar con el patrón **MVC**, persistir los datos con **Spring Data JPA** y usar **Thymeleaf** para las vistas HTML. También se contempla exponer operaciones mediante una API REST.

> **Estado actual:** el proyecto está en desarrollo. Por ahora incluye la clase de arranque y la entidad `Producto`; todavía no hay controladores, servicios, repositorios ni plantillas Thymeleaf implementados.

## Tecnologías

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Thymeleaf
- MySQL Connector/J
- Maven

## Estructura actual

```text
src/
├── main/
│   ├── java/com/ferreteria/ferreteria/
│   │   ├── FerreteriaApplication.java
│   │   └── modells/
│   │       └── Producto.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/ferreteria/ferreteria/
        └── FerreteriaApplicationTests.java
```

La entidad `Producto` representa un producto de ferretería y contiene código, nombre, marca, categoría, precio, stock y descripción. Su código se genera automáticamente mediante JPA.

## MVC, Thymeleaf y API REST

En una aplicación MVC tradicional, el controlador recibe la petición, consulta la lógica de negocio y devuelve una vista. Thymeleaf permite generar esa vista HTML en el servidor.

Una API REST, en cambio, normalmente responde con datos —por ejemplo, JSON— en lugar de una página HTML. Ambos enfoques pueden convivir en el mismo proyecto: se pueden crear rutas MVC que rendericen plantillas Thymeleaf y rutas REST para consumir los datos desde otros clientes.

La separación prevista para el proyecto es:

- **Modelo:** entidades y acceso a datos, como `Producto`.
- **Vista:** plantillas HTML con Thymeleaf.
- **Controlador:** recibe las peticiones web o REST y coordina la respuesta.
- **Servicio (capa adicional):** concentra las reglas de negocio entre los controladores y los repositorios.

Estas capas describen la organización objetivo; aún no todas están creadas.

## Requisitos

- JDK 25.
- MySQL instalado y en ejecución.
- Una base de datos MySQL creada para el proyecto.

## Configuración de la base de datos

Antes de ejecutar la aplicación, configura `src/main/resources/application.properties` con la URL de tu base de datos y tus credenciales locales. Por ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ferreteria
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASENA
```

Crea previamente la base de datos `ferreteria` en MySQL o ajusta el nombre de la URL al que hayas creado. No guardes credenciales reales en el repositorio.

## Ejecutar y probar

Desde la raíz del proyecto, una vez configurada la conexión a MySQL:

```bash
./mvnw spring-boot:run
```

Para ejecutar las pruebas:

```bash
./mvnw test
```

## Próximos pasos posibles

- Crear el repositorio JPA para `Producto`.
- Añadir un servicio con las operaciones de gestión de productos.
- Implementar controladores REST y/o controladores MVC.
- Crear formularios y plantillas Thymeleaf.
- Añadir validaciones y pruebas para las operaciones.
