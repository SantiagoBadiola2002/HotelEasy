# 🏨 hoteleasy - Sistema de Gestión de Reservas para Hotel

Este proyecto es un sistema completo de gestión de reservas para hoteles, desarrollado con **Java 17**, **Spring Boot**, y documentado con **Swagger**. Permite gestionar clientes, habitaciones, reservas, pagos, usuarios y el historial de estadías.

---

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Swagger / OpenAPI**
- **Lombok**
- **MySQL** *(configurable)*
- **Maven**

---

## 📦 Módulos y Funcionalidades

### 👤 Cliente
- Crear, obtener, actualizar y eliminar clientes.
- Buscar por ID, nombre completo o cédula.
- Listar todos los clientes.

### 🛏️ Habitación
- Crear y gestionar habitaciones.
- Buscar por número, piso, tipo y estado (Disponible, Ocupada, etc).
- Eliminar habitaciones.

### 🧾 Reserva
- Crear reservas asociadas a clientes y habitaciones.
- Cambiar estado (confirmada, cancelada, etc) y cambiar habitación asignada.
- Filtrar por cliente o vendedor.

### 🧍 Usuario
- CRUD básico de usuarios.
- Búsqueda por nombre de usuario.
- Ideal para funcionalidades de login o gestión de personal.

### 💳 Pago
- Registro de pagos realizados por las reservas.
- Filtrar por ID de reserva.

### 📚 Historial
- Registrar check-in y check-out.
- Obtener historial por fecha, ID o en forma de lista general.

---

## 📖 Documentación con Swagger

La documentación interactiva del API está disponible con Swagger UI: http://localhost:8080/swagger-ui/index.html

---

## 🔧 Configuración de la Base de Datos

Para que la aplicación funcione correctamente, es necesario configurar las credenciales de la base de datos en el archivo `src/main/resources/application.properties`. Allí debes ingresar la URL, el nombre de usuario y la contraseña de tu base de datos. Por ejemplo:

#Conexión a MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/hoteleasy_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

---

## ⚙️ Cómo Ejecutar
Prerrequisitos

- Java 17
- Maven 3.6+
- (Opcional) phpMyAdmin

Ejecutar en entorno local

git clone https://github.com/tuusuario/hoteleasy.git
cd hoteleasy
mvn spring-boot:run

## 🧪 Pruebas
Puedes utilizar herramientas como Postman o directamente la UI de Swagger para realizar pruebas manuales. Las pruebas unitarias y de integración están disponibles bajo el paquete test/.


