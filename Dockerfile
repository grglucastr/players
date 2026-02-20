# ============================================================
# STAGE 1: BUILD STAGE
# Uses a full Maven + JDK image to compile and package the app
# ============================================================
FROM maven:3.9.12-eclipse-temurin-25 AS builder

# Set a working directory inside the container
WORKDIR /app

# --- Dependency Caching Layer (Docker's best practice) ---
# Copy ONLY the pom.xml first and download dependencies.
# This layer is cached and won't re-run unless pom.xml changes,
# which speeds up subsequent builds dramatically.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Now copy the rest of the source code
COPY src ./src

# Package the app, skipping tests (run tests in CI separately)
RUN mvn package -DskipTests -B


# ============================================================
# STAGE 2: RUNTIME STAGE
# Uses a minimal JRE image — no Maven, no JDK, no source code
# ============================================================
FROM eclipse-temurin:25-jre-alpine AS runtime

# Create a non-root user for security (never run as root!)
RUN addgroup --system spring && adduser --system --ingroup spring spring

# Set working directory
WORKDIR /app

# Copy ONLY the final JAR from the builder stage
# Adjust the JAR name to match your artifact (or use a wildcard)
COPY --from=builder /app/target/*.jar app.jar

# Change ownership to the non-root user
RUN chown spring:spring app.jar

# Switch to non-root user
USER spring

# Expose the default Spring Boot port
EXPOSE 8080

# JVM tuning flags for containers:
#   -XX:+UseContainerSupport         → respects container CPU/memory limits
#   -XX:MaxRAMPercentage=75.0        → use up to 75% of container RAM for heap
#   -Djava.security.egd=...          → faster startup (avoids entropy blocking)
ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-Djava.security.egd=file:/dev/./urandom", \
  "-jar", "app.jar"]