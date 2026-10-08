# Franquisias API - Instrucciones de desarrollo

## Requisitos

- Java 17
- Maven (o usar el wrapper `mvnw`)
- Docker Desktop (para levantar MongoDB con Docker Compose)

## Configuración de Java

Este proyecto usa Spring Boot 3.3.4, que requiere Java 17. Si tienes varias versiones instaladas, puedes forzar el JDK 17 al compilar con:

### Windows (PowerShell)

```powershell
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot"
.\mvnw.cmd clean compile
```

Para configurar Java 17 de forma permanente:

```powershell
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot", "Machine")
[Environment]::SetEnvironmentVariable("Path", "%JAVA_HOME%\bin;" + [Environment]::GetEnvironmentVariable("Path", "Machine"), "Machine")
```

## Compilar y empaquetar

```powershell
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot"
.\mvnw.cmd clean package -DskipTests
```

## Levantar con Docker Compose

Asegúrate de que Docker Desktop esté corriendo y ejecuta:

```powershell
docker compose up --build -d
```

Esto levanta:

- MongoDB en `localhost:27017`
- La API en `http://localhost:8080`

## Detener los contenedores

```powershell
docker compose down
```

## Swagger UI

Con la app corriendo, puedes probar todos los endpoints desde el navegador en:

```
http://localhost:8080/swagger-ui/index.html
```

También está disponible la especificación OpenAPI en:

```
http://localhost:8080/v3/api-docs
```

## Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/franquicias` | Listar todas las franquicias (resumen: id, nombre y cantidad de sucursales) |
| GET | `/api/franquicias/{id}` | Ver una franquicia por id con todas sus sucursales y productos |
| POST | `/api/franquicias` | Crear una franquicia |
| POST | `/api/franquicias/{id}/sucursales` | Agregar una sucursal |
| POST | `/api/franquicias/{id}/sucursales/{sucursalId}/productos` | Agregar un producto |
| DELETE | `/api/franquicias/{id}/sucursales/{sucursalId}/productos/{productId}` | Eliminar un producto |
| PATCH | `/api/franquicias/{id}/sucursales/{sucursalId}/productos/{productId}/stock?stock=10` | Actualizar stock |
| GET | `/api/franquicias/{id}/productos/mayor-stock` | Producto con más stock por sucursal |

## Configuración de MongoDB

La URI de MongoDB se lee de la variable de entorno `MONGO_URI`. Si no está definida, usa `mongodb://localhost:27017/franquisiasdb`.

Para usar MongoDB Atlas u otra instancia, exporta la variable antes de ejecutar la app:

```powershell
$env:MONGO_URI="mongodb+srv://usuario:password@cluster.mongodb.net/franquisiasdb"
```
