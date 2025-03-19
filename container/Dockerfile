# Stage 1: Build stage
FROM gradle:8.10-jdk21 AS build

WORKDIR /app

# Copy Gradle wrapper scripts and related files first (for better caching)
COPY gradlew gradle/ gradle/wrapper/ settings.gradle build.gradle ./

# Ensure the Gradle wrapper script is executable
RUN chmod +x gradlew

# Download dependencies first (to optimize caching)
RUN ./gradlew dependencies --no-daemon || true

# Now copy the rest of the project files
COPY . .

# Build the application
RUN ./gradlew clean build --no-daemon

# Stage 2: Production stage
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
