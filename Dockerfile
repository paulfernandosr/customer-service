FROM openjdk:11-oracle
COPY "./target/customer-service-1.0.0.jar" "/app/customer-service-1.0.0.jar"
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/customer-service-1.0.0.jar"]