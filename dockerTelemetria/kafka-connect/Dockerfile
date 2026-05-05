FROM confluentinc/cp-kafka-connect:7.4.0

USER root
RUN microdnf install -y curl

RUN mkdir -p /etc/kafka-connect/jars && \
    curl -L -o /etc/kafka-connect/jars/mongo-kafka-connect-1.10.1-all.jar https://search.maven.org/remotecontent?filepath=org/mongodb/kafka/mongo-kafka-connect/1.10.1/mongo-kafka-connect-1.10.1-all.jar