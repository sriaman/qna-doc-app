# Use an official JDK image
FROM openjdk:17-jdk-slim

# Add a volume to reduce container size and reuse layers
VOLUME /tmp

# Set working directory inside the container
WORKDIR /app

# Copy the jar built by Gradle or Maven
COPY build/libs/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
