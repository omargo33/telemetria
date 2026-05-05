db = db.getSiblingDB('testTelemetria');
db.ejemplo.insertOne({mensaje: "Base creada automáticamente", fecha: new Date()});
