#Build da aplicação
FROM maven:3-openjdk-17-slim AS build
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

#Execução da aplicação
FROM openjdk:17-jdk-slim
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/Covid19.jar covid19.jar
CMD ["java", "-jar", "covid19.jar"]