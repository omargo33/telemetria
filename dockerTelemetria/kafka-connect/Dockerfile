FROM confluentinc/cp-kafka-connect:7.4.0

USER root
RUN microdnf install -y curl unzip

# Crear directorio para plugins
RUN mkdir -p /etc/kafka-connect/jars

# Descargar MongoDB Kafka Connector (versión más reciente)
RUN curl -L -o /tmp/mongo-kafka-connect.zip \
    https://repo1.maven.org/maven2/org/mongodb/kafka/mongo-kafka-connect/1.11.0/mongo-kafka-connect-1.11.0-all.jar && \
    mv /tmp/mongo-kafka-connect.zip /etc/kafka-connect/jars/mongo-kafka-connect-1.11.0-all.jar

# Asegurar permisos
RUN chmod 644 /etc/kafka-connect/jars/*.jar

# Verificar que el archivo existe
RUN ls -la /etc/kafka-connect/jars/