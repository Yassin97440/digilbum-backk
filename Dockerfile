# Étape 1 : Build l'application avec une image Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=prod
# Copier les fichiers de configuration Maven et le pom.xml
COPY pom.xml .
COPY mvnw .mvnw
COPY .mvn .mvn

# Télécharger les dépendances sans builder (meilleure gestion du cache Docker)
RUN mvn dependency:go-offline

# Copier les sources du projet et builder l'application
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Image d'exécution légère pour Java 17
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app


# Ajouter l'utilisateur non-root pour des raisons de sécurité
RUN addgroup -S digilbum && adduser -S digilbum -G digilbum
# Créer le dossier pictures et configurer les permissions avant de changer d'utilisateur
USER digilbum:digilbum
RUN mkdir ~/pictures && chmod 777 ~/pictures

# Copier le JAR depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port standard de Spring Boot
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]


