FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn test
RUN mvn package
COPY target/*.jar app.jar
EXPOSE 8080
CMD java -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar --spring.datasource.url=jdbc:mysql://mysql:3306/miniautorizador --spring.datasource.username=root --spring.datasource.password=