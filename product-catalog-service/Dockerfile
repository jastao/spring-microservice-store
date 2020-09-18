# Base docker image for openjdk 11
FROM openjdk:11-jre-slim

# Rename the jar
ADD ["target/product-catalog-service-0.0.1-SNAPSHOT.jar", "app.jar"]

# Tell docker to document this as an exposed port.
EXPOSE 8081

# Update the metadata for the jar
RUN sh -c 'touch /app.jar'

# Run the jar
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
