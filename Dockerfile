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
ENTRYPOINT ["java","-jar", "repository-github-0.0.1-SNAPSHOT.jar"]