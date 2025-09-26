# Use a lightweight official OpenJDK image for Java 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files (pom.xml and src) to the container
# This is done in two steps to leverage Docker cache for dependencies
COPY pom.xml .
COPY mvnw .           # <-- NEW LINE: Copy the Maven wrapper executable
COPY mvnw.cmd .       # <-- NEW LINE: Copy the Windows Maven wrapper script
COPY src ./src

# Make the Maven wrapper script executable (important for Linux-based Docker)
RUN chmod +x ./mvnw   # <-- NEW LINE: Make mvnw executable

# Build the application using Maven
# The -DskipTests flag skips tests during the build, which is often done for faster deployments
RUN ./mvnw clean install -DskipTests

# Expose the port your Spring Boot application runs on
# This should match your server.port in application.properties if specified, or default 8080
EXPOSE 8080

# Command to run the application
# This assumes your JAR is built into target/*.jar
ENTRYPOINT ["java", "-jar", "target/*.jar"]
