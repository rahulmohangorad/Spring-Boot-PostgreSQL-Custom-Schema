# Spring Boot + PostgreSQL Custom Schema Demo

This project demonstrates **three approaches** to work with PostgreSQL schemas in Spring Boot:
1. **Default schema** for all entities via `hibernate.default_schema`
2. **Per-entity schema** using `@Table(schema = "...")`
3. **Database search path** using JDBC param `currentSchema=my_schema`

Tech:
- Java 21
- Spring Boot 3.3.x
- Spring Data JPA
- PostgreSQL 16
- Gradle (Groovy)
- Docker & Docker Compose

---

## Run with Docker Compose (recommended)

> Requires Docker installed.

```bash
docker compose up --build
```

This will:
- Start **Postgres** on port `5432`
- Build & run the **Spring Boot** app on port `8080`

Once it's up, try these endpoints:

- `POST http://localhost:8080/users/default/{name}`  
  Inserts into **`my_schema.users_default`** (default schema)

- `POST http://localhost:8080/users/custom/{email}`  
  Inserts into **`other_schema.users_custom`** (entity-level schema)

- `POST http://localhost:8080/users/mixed/{phone}`  
  Inserts into **`my_schema.users_mixed`** (resolved via `currentSchema` / search_path)

- `GET  http://localhost:8080/users/all`  
  Returns counts for all three tables

Stop all containers:
```bash
docker compose down
```

> Data persists in the named Docker volume `pgdata` (remove via `docker volume rm pgdata`).

---

## Local run (optional)

If you want to run locally (without Docker), adjust `spring.datasource.*` in `src/main/resources/application.yml`
to point to your local PostgreSQL, then:

```bash
./gradlew bootRun
```

---

## How it works

- `schema.sql` creates two schemas: `my_schema` and `other_schema`.
- `application.yml` sets:
  - `hibernate.default_schema=my_schema` (**Approach A**)
  - JDBC URL includes `?currentSchema=my_schema` so search path prefers `my_schema` (**Approach C**)
- `@Table(schema="other_schema")` on `UserCustom` shows **Approach B** for one entity.

You can mix-and-match these depending on your needs.
