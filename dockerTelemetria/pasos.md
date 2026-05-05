mongodb://root:example@localhost:27017/testTelemetria
mongodb://root:example@localhost:27017/admin
mongodb://root:example@mongodb:27017/admin

## subir al conexion
curl -X POST -H "Content-Type: application/json" --data @mongo-sink.json http://localhost:8083/connectors

docker compose build
docker compose up -d

docker compose logs --tail 100 zookeeper > zookeeper.log 
docker compose logs --tail 100 kafka > kafka.log
docker compose logs --tail 100 kafka-ui > kafka-ui.log
docker compose logs --tail 100 mongodb > mongodb.log
docker compose logs --tail 100 kafka-connect > kafka-connect.log
docker compose logs --tail 100 metabase > metabase.log
docker compose logs --tail 100 otel-collector > otel-collector.log

echo "-------- zookeeper ----------" >> full.log
docker compose logs --tail 100 zookeeper >> full.log 
echo "-------- kafka ----------" >> full.log
docker compose logs --tail 100 kafka >> full.log
echo "-------- kafka-ui ----------" >> full.log
docker compose logs --tail 100 kafka-ui >> full.log
echo "-------- mongodb ----------" >> full.log
docker compose logs --tail 100 mongodb >> full.log
echo "-------- kafka-connect ----------" >> full.log
docker compose logs --tail 100 kafka-connect >> full.log
echo "-------- metabase ----------" >> full.log
docker compose logs --tail 100 metabase >> full.log
echo "-------- otel-collector ----------" >> full.log
docker compose logs --tail 100 otel-collector >> full.log