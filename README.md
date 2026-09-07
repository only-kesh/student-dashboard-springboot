# Bootstrap Student Dashboard - Gradle Version

Spring Boot Practical 02 converted from Maven to Gradle while keeping the same application structure and functionality.

## Technology
- Java 17+
- Spring Boot 4.1.0
- Spring MVC
- Thymeleaf
- Bootstrap 5.3.8
- Gradle

## Database
This practical does **not** use a database. Student data is stored in an in-memory `List<Student>` inside `StudentService.java`, matching the original practical.

## Run on macOS / Linux

```bash
chmod +x gradlew
./gradlew bootRun
```

Then open:

- http://localhost:8080/
- http://localhost:8080/dashboard
- http://localhost:8080/?keyword=Artificial
- http://localhost:8080/students/1

The included `gradlew` bootstrap script downloads Gradle 9.1.0 on first use and caches it under your user Gradle directory.

## Build JAR

```bash
./gradlew clean build
```

The executable JAR will be created under `build/libs/`.
