# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Crea un volumen para archivos temporales
VOLUME /tmp

# Copia el archivo JAR generado en la carpeta target al contenedor
COPY target/ejercicio08-0.0.1-SNAPSHOT.jar app.jar

# Define el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]
