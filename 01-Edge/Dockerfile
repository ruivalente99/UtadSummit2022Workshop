################# SDK ####################
FROM maven:3.8.4-eclipse-temurin-17 as builder
WORKDIR /root/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src

RUN mvn -e -DskipTests clean package
WORKDIR /extracted
RUN jar -xf /root/app/target/edge-app-0.0.1-SNAPSHOT.jar

################# RUNTIME ####################
FROM eclipse-temurin:17
RUN mkdir /app

COPY --from=builder /extracted/BOOT-INF/lib /app/lib
COPY --from=builder /extracted/META-INF /app/META-INF
COPY --from=builder /extracted/BOOT-INF/classes /app

################# LABELS ####################
ARG BUILD_DATE
ARG COMMIT_USER
LABEL org.opencontainers.image.created=$BUILD_DATE
LABEL org.opencontainers.image.authors=$COMMIT_USER

################# ENTRYPOINT ####################
ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-cp", "app:app/lib/*", "com.neoception.workshop.edge.EdgeApplication"]
