FROM openjdk:11 as builder
WORKDIR application
COPY ./pom.xml ./pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY ./src ./src
RUN ["chmod", "+x", "mvnw"]
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw clean package && cp target/repository-github-0.0.1-SNAPSHOT.jar repository-github-0.0.1-SNAPSHOT.jar
RUN java -jar repository-github-0.0.1-SNAPSHOT.jar extract
#ENTRYPOINT ["java","-jar", "repository-github-0.0.1-SNAPSHOT.jar"]
FROM openjdk:11-jre-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
docker build -t ricodesenv/repository-github:1.0.2
docker run -p 8080:8080 ricodesenv/repository-github:1.0.2