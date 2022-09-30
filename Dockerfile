FROM openjdk:11
ADD target/food-delivery-0.0.1-SNAPSHOT.jar food-delivery-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "food-delivery-0.0.1-SNAPSHOT.jar"]