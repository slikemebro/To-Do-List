FROM openjdk:17
COPY ./target/ToDoList-0.0.1-SNAPSHOT.jar /app/ToDoList-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "ToDoList-0.0.1-SNAPSHOT.jar"]

