#!/bin/bash

echo "Esperando a que Kafka Connect esté listo..."
until curl -s http://localhost:8083/connectors > /dev/null; do
    echo "Kafka Connect no está listo aún... esperando 5 segundos"
    sleep 5
done

echo "✅ Kafka Connect está listo. Registrando conector MongoDB sink..."

curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d @mongo-sink.json

echo ""
echo "📊 Verificando estado del conector..."
curl -s http://localhost:8083/connectors/mongo-sink-otel/status | jq .

echo ""
echo "🔍 Listando todos los conectores activos:"
curl -s http://localhost:8083/connectors