# BeatSync — Java Music Discovery Web App

A student project MVP with a responsive browser interface, Spring Boot REST API, JDBC repository layer, and H2 database.

## Requirements
- Java 21+
- Maven 3.9+
- Internet on first run for Maven dependencies

## Run
```bash
mvn spring-boot:run
```
Windows: double-click `run.bat`. Open http://localhost:8080.

Build:
```bash
mvn clean package
java -jar target/beatsync-1.0.0.jar
```

## REST API
- `GET /api/songs` — list tracks
- `GET /api/songs?q=night` — search title, artist, album
- `POST /api/songs` — add metadata JSON
- `DELETE /api/songs/{id}` — delete a track
- `GET /api/health` — health check

Example POST JSON:
```json
{"title":"New Track","artist":"Artist Name","album":"Album","genre":"Pop","audioUrl":""}
```

H2 console: http://localhost:8080/h2-console  
JDBC URL: `jdbc:h2:file:./data/beatsync`; username `sa`; blank password.

## Structure
- `controller/` — REST endpoints and API error handling
- `service/` — business rules and validation
- `repository/` — JDBC/SQL access
- `model/` — Song domain record
- `resources/static/` — browser interface
- `schema.sql`, `data.sql` — database schema and demo records

## Rubric mapping
- Problem/design: music discovery use case and layered architecture
- Core Java/OOP: model, controller, service, repository, constructor injection, custom exceptions
- Collections/generics: typed `List<Song>`
- JDBC/database: `JdbcTemplate`, parameterized SQL, H2 schema/seed records
- Web integration: Spring MVC REST controllers on the Servlet stack
- Exception handling: centralized `@RestControllerAdvice`

## Limitations
This is an educational MVP, not a production streaming service. Demo songs contain metadata only; the UI can preview user-provided local audio. Authentication, role-based admin access, persistent playlists/favorites, server-side audio upload/streaming, and PostgreSQL are future work. Use only audio you own or are licensed to distribute.

## Deployment
A Dockerfile is included for container hosting. For production, migrate to managed PostgreSQL and add authentication, authorization, tests, monitoring, and backups.
