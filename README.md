# API de Gestión de Usuarios

Esta es una API RESTful para la **gestión de usuarios**, desarrollada con el objetivo de facilitar la creación, consulta y administración de usuarios en un sistema. La API está documentada utilizando OpenAPI 3.1.0 y cuenta con una interfaz interactiva mediante Swagger UI.

## 🚀 Características

- Crear usuarios mediante solicitud `POST`
- Consultar todos los usuarios o uno específico mediante `GET`
- Documentación de la API con Swagger
- Respuestas estructuradas en formato JSON
- Manejo de errores estándar (`400`, `404`, `500`)
- Licencia MIT

## 📑 Documentación

Puedes acceder a la documentación interactiva (Swagger UI) una vez que el servidor esté corriendo en:

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ⚙️ Cómo ejecutar el proyecto

Sigue los pasos a continuación para ejecutar el proyecto localmente:

### 1. Clona el repositorio

```bash
git clone https://github.com/tuusuario/tu-repositorio.git
cd tu-repositorio
```

### 2. Compila el proyecto

Asegúrate de tener instalado Java 17+ y Maven o Gradle.

**Con Maven:**

```bash
mvn clean install
```

**Con Gradle:**

```bash
./gradlew build
```

### 3. Ejecuta la aplicación

**Con Maven:**

```bash
mvn spring-boot:run
```

**O ejecutando directamente el .jar generado:**

```bash
java -jar target/nombre-del-jar.jar
```

> 🔧 Asegúrate de que el puerto **8080** esté libre antes de iniciar la aplicación.

### 4. Accede a Swagger

Una vez iniciada la aplicación, abre tu navegador y accede a:

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🧪 Endpoints principales

### Obtener usuarios

```
GET /api/users
```

- Parámetro opcional: `email`

### Crear usuario

```
POST /api/users
```

- Body esperado:

```json
{
  "name": "Juan Pérez",
  "email": "juan.perez@ejemplo.com"
}
```

---

## 🛠 Estructura de respuesta

### Éxito

```json
{
  "message": "Usuario creado exitosamente."
}
```

### Error

```json
{
  "error": "Mensaje de error descriptivo."
}
```

---

## 🧾 Licencia

Este proyecto está bajo la licencia [MIT](https://opensource.org/licenses/MIT).

---

