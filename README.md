# Franquisias API

API REST desarrollada con Spring Boot y MongoDB para gestionar franquicias, sucursales y productos. El proyecto está dockerizado y listo para ejecutarse con Docker Compose.

## Requisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y en ejecución.
- Opcional: Java 17 y Maven si deseas compilar manualmente.

## Cómo levantar la aplicación con Docker

Desde la raíz del proyecto, ejecuta:

```bash
docker compose up --build -d
```

Este comando:

1. Construye la imagen de la aplicación Spring Boot.
2. Levanta un contenedor con MongoDB.
3. Levanta la aplicación y la conecta a MongoDB.
4. Expone la API en `http://localhost:8080`.

## Verificar que todo funciona

Una vez levantados los contenedores, abre en tu navegador:

```
http://localhost:8080/swagger-ui/index.html
```

O directamente:

```
http://localhost:8080/
```

Ahí verás Swagger UI con todos los endpoints documentados y listos para probar.

## Detener la aplicación

```bash
docker compose down
```

Si quieres eliminar también los datos persistidos de MongoDB:

```bash
docker compose down -v
```

> **Nota:** sin el flag `-v`, los datos de MongoDB se conservan en un volumen de Docker, por lo que al volver a levantar la app seguirán disponibles.

## Endpoints principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/franquicias` | Listar todas las franquicias (resumen) |
| GET | `/api/franquicias/{id}` | Ver una franquicia con sus sucursales y productos |
| POST | `/api/franquicias` | Crear una franquicia |
| POST | `/api/franquicias/{id}/sucursales` | Agregar una sucursal |
| POST | `/api/franquicias/{id}/sucursales/{sucursalId}/productos` | Agregar un producto |
| DELETE | `/api/franquicias/{id}/sucursales/{sucursalId}/productos/{productId}` | Eliminar un producto |
| PATCH | `/api/franquicias/{id}/nombre` | Actualizar nombre de franquicia |
| PATCH | `/api/franquicias/{id}/sucursales/{sucursalId}/nombre` | Actualizar nombre de sucursal |
| PATCH | `/api/franquicias/{id}/sucursales/{sucursalId}/productos/{productId}/nombre` | Actualizar nombre de producto |
| PATCH | `/api/franquicias/{id}/sucursales/{sucursalId}/productos/{productId}/stock` | Actualizar stock de producto |
| GET | `/api/franquicias/{id}/productos/mayor-stock` | Producto con más stock por sucursal |

## Ejemplo de uso con PowerShell

### Crear una franquicia

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/franquicias" -Method Post `
  -Body '{"name":"Franquicia Demo"}' -ContentType "application/json"
```

### Agregar una sucursal

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/franquicias/{id}/sucursales" -Method Post `
  -Body '{"name":"Sucursal Centro"}' -ContentType "application/json"
```

### Agregar un producto

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/franquicias/{id}/sucursales/{sucursalId}/productos" -Method Post `
  -Body '{"name":"Hamburguesa","stock":50}' -ContentType "application/json"
```

> Reemplaza `{id}`, `{sucursalId}` y `{productId}` por los valores reales devueltos por la API.

## Compilar manualmente (opcional)

Si deseas compilar el JAR sin Docker, asegúrate de tener Java 17 configurado y ejecuta:

```bash
./mvnw clean package -DskipTests
```

En Windows con PowerShell:

```powershell
.\mvnw.cmd clean package -DskipTests
```

## Estructura del proyecto

```
franquisias-api/
├── docker-compose.yml      # Orquesta la app + MongoDB
├── Dockerfile              # Imagen de la app Spring Boot
├── pom.xml                 # Dependencias de Maven
├── README.md               # Este archivo
├── AGENTS.md               # Notas de desarrollo
├── src/
│   └── main/
│       ├── java/
│       │   └── com/franquisias/
│       │       ├── controller/   # Endpoints REST
│       │       ├── dto/          # Objetos de transferencia
│       │       ├── model/        # Entidades
│       │       ├── repository/   # Acceso a MongoDB
│       │       ├── service/      # Lógica de negocio
│       │       └── FranquisiasApiApplication.java
│       └── resources/
│           └── application.properties
└── .gitignore
```
