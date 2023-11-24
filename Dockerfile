FROM maven:3.9 AS builder
WORKDIR /warehouse
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:21
ENV SPRING_PROFILES_ACTIVE=production
WORKDIR /warehouse
COPY --from=builder "/warehouse/target/warehouse_server-1.0.0.jar" /warehouse/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
