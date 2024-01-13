# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-oracle


  # Set the working directory in the container
WORKDIR /app

  # Copy the application JAR file into the container at /app
COPY target/product-service.jar /app/

  # Expose the port the app runs on (assuming the Spring Boot app runs on port 8080)
EXPOSE 8080

  # Specify the command to run on container start
CMD ["java", "-jar", "product-service.jar"]
