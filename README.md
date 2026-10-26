# Ferretería

Aplicación de práctica para aprender Spring Boot mediante un sistema de gestión de inventario. El proyecto se está construyendo como un **monolito MVC**: la aplicación web, la lógica de negocio, el acceso a datos y las vistas viven juntas en un mismo proyecto y se ejecutan como una sola aplicación.

La interfaz principal usa **Thymeleaf** para renderizar páginas HTML en el servidor. También existe una API REST para consultar y administrar los mismos productos; es una forma adicional de acceso, no una arquitectura de microservicios ni el centro de la aplicación.

## Estado actual

Ya está implementado el CRUD de productos en las dos interfaces:

- La aplicación web permite listar, registrar, editar y eliminar productos desde páginas Thymeleaf.
- La API REST ofrece operaciones para listar, buscar, crear, editar y eliminar productos en formato JSON.
- La lógica y persistencia se comparten: ambos controladores utilizan el mismo servicio y repositorio.
- Las vistas de listado y formulario tienen estilos CSS propios.

## Tecnologías

- Java 25 y Spring Boot 4.1.1.
- Spring Web MVC y Thymeleaf para la aplicación web.
- Spring Data JPA para persistencia.
- Maven para dependencias y ejecución.
- Configuración local de base de datos MariaDB.

## Cómo se organiza

```text
src/main/java/com/ferreteria/ferreteria/
├── FerreteriaApplication.java
├── controller/
│   ├── ProductoWebController.java
│   └── ProductoRestController.java
├── modells/
│   └── Producto.java
├── repository/
│   └── IProductoRepository.java
└── service/
    ├── IProductoService.java
    └── ProductoService.java

src/main/resources/
├── application.properties
├── static/css/
│   ├── formulario.css
│   └── styles.css
└── templates/productos/
    ├── formulario.html
    └── lista.html
```

El flujo de la aplicación es:

1. Un controlador recibe la petición web o REST.
2. El servicio aplica las reglas del CRUD y valida los datos del producto.
3. El repositorio de Spring Data JPA consulta o modifica la base de datos.
4. Para las rutas web, el controlador devuelve una vista Thymeleaf; para las rutas REST, devuelve datos o un estado HTTP.

`Producto` es la entidad persistida. Sus campos son código autogenerado, nombre, marca, categoría, precio, stock y descripción. La validación actual requiere nombre, marca, categoría, precio mayor que cero y stock mayor que cero; la descripción es opcional.

## Rutas disponibles

### Aplicación web

| Método | Ruta | Uso |
|---|---|---|
| `GET` | `/productos` | Ver el inventario |
| `GET` | `/productos/nuevo` | Mostrar el formulario de registro |
| `POST` | `/productos/crear` | Crear un producto o guardar cambios |
| `GET` | `/productos/editar/{codProd}` | Mostrar el formulario de edición |
| `POST` | `/productos/eliminar/{codProd}` | Eliminar un producto |

### API REST

| Método | Ruta | Uso |
|---|---|---|
| `GET` | `/api/productos` | Listar productos |
| `GET` | `/api/productos/{codProd}` | Consultar un producto |
| `POST` | `/api/productos` | Crear un producto |
| `PUT` | `/api/productos/{codProd}` | Editar un producto |
| `DELETE` | `/api/productos/{codProd}` | Eliminar un producto |

Las operaciones de creación y edición de la API reciben el producto en el cuerpo de la petición como JSON.

## Base de datos y ejecución

`src/main/resources/application.properties` contiene una configuración local para MariaDB (`ferreteria_db` en `localhost:3307`). Ajusta esos valores a tu instalación y no publiques credenciales personales.

Antes de ejecutar, hay que comprobar que el controlador JDBC de MariaDB configurado en `application.properties` coincida con la dependencia declarada en `pom.xml`; actualmente esos archivos declaran controladores distintos.

Con la base de datos y el controlador configurados:

```bash
./mvnw spring-boot:run
```

La aplicación web queda disponible en `http://localhost:8080/productos`. Para ejecutar las pruebas:

```bash
./mvnw test
```
