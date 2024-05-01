docker-compose -f docker-app-compose.yml down

mvn clean install -DskipTests

cd ./elastic-query-service
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=elastic-query-service

cd ../analytics-service
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=analytics-service

cd ../kafka-streams-service
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=kafka-streams-service

cd ../twitter-to-kafka-service
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=twitter-to-kafka-service

cd ../kafka-to-elastic-service
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=kafka-to-elastic-service

cd ../gateway-service
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=gateway-service

cd ../

docker-compose -f docker-app-compose.yml up -d