### 📖 AWS Startup Template For Spring Boot Developers

<ul style="list-style-type:disc">
  <li>📖 This <b>AWS Full-Stack Developer Template</b> provides fully functional Development Environment</li>
    <li>📖 <b>Spring Boot Microservices</b> Source Code</li>
    <li>📖 Local <b>Docker</b> Environment</li>
    <li>📖 <b>Github Actions</b> CI/CD <b>GitOps</b> pipeline</li>
    <li>📖 <b>AWS Terraform</b> Infrastructure with <b>AWS EKS Kubernetes Cluster</b></li>
  <li>📖 Full <b>Technology Stack</b>:</li>
  <ul>
    <li>✅ <b>Swagger UI</b></li>
    <li>✅ <b>Spring Boot</b></li>
    <li>✅ <b>Terraform</b></li>
    <li>✅ <b>Kubernetes</b></li>
    <li>✅ <b>Kafka Streams</b></li>
    <li>✅ <b>Twitter to Kafka Event Streaming</b></li>
    <li>✅ <b>Kafka State Store</b></li>
    <li>✅ <b>Elasticsearch</b></li>
    <li>✅ <b>Elastic Query Service</b></li>
    <li>✅ <b>Kafka UI</b></li>
    <li>✅ <b>Event-Driven Microservices</b></li>
    <li>✅ <b>CQRS</b></li>
    <li>✅ <b>PostgreSQL Database</b></li>
    <li>✅ <b>Keycloak Oauth2 Authorization Server</b></li>
    <li>✅ <b>Github Actions</b></li>
    <li>✅ <b>Local Docker Environment</b></li>
    <li>✅ <b>Remote Debugging</b></li>
    <li>✅ <b>Spring Cloud Gateway</b></li>    
    <li>✅ <b>AWS SSL Certificate</b></li>  
    <li>✅ <b>External DNS</b></li>  
    <li>✅ <b>AWS Load Balancer Controller</b></li>  
    <li>✅ <b>Spring Cloud Kubernetes</b></li>  
    <li>✅ <b>Grafana Observability Stack</b></li>
  </ul>
</ul>

### 📖 Source Code

- [API Source Code and Docker Images Repository](https://github.com/greeta-twitter-01/twitter-api)
- [Terraform Infrastructure and GitOps Pipeline](https://github.com/greeta-twitter-01/twitter-infra)

### 📖 Step By Step Guide

#### Local Docker Environment Setup:

```
sh docker-start.sh
```

- this script will build kafka, elastic and other third-party docker images and start environment with your code changes

- Warning! Make sure that all kafka containers are started successfully! (see `Kafka Containers Troubleshooting` below)

```
sh docker-app-start.sh
```

- this script will build spring boot docker images and start environment with your code changes

- Warning! Make sure that all kafka containers are started successfully, before running this script! (see `Kafka Containers Troubleshooting` below)

```
sh docker-app-restart.sh
```

- this script will restart spring boot docker images without rebuilding them (use `sh docker-app-start.sh` if you want to rebuild docker images after code changes)

- For example, if `kafka-to-elastic-service` stops with errors, run `sh docker-app-restart` to restart all spring boot containers (or use `docker-compose -f docker-app-compose.yml up -d` if you don't want to restart all spring boot containers)

#### Local Docker Environment Acceptance Test:

- If `twitter-to-kafka` service starts successfully, it will start generating mock twitter messages and add them to Kafka `twitter-topic`

- If `kafka-to-elastic` service starts successfully, it will consume messages from Kafka `twitter-topic` and add them to `Elasticsearch` indexed documents (see the docker logs)

- If `kafka-streams` service starts successfully, it will consume messages from Kafka `twitter-topic` and add them to Kafka `twitter-analytics-topic`

- If `analytics` service starts successfully, it will consume messages from Kafka `twitter-analytics-topic` and insert records to `twitter-analytics` PostgreSQL Table

- open `localhost:9000` in your Browser and switch between `Analytics`, `Elastic Query` and `Kafka Streams` Microservices

- Warning! If Swagger UI fails to load on the first try, please, refresh the page!

- Warning! Sometimes switching between services doesn't refresh Swagger UI completely and you might see wrong REST endpoints: just refresh the page and continue

- Warning! Sometimes REST endpoints return `504 Gateway Timeout`, `405 Method not Allowed` or other errors, just retry the REST API endpoint again


- find word count for any word on `Analytics` page (see the list of words in `MockKafkaStreamRunner`)

- find word count for any word on `Kafka Streams` page (see the list of words in `MockKafkaStreamRunner`)

- find all documents or document by id on `Elastic Query` page

- click `Authorize` on `Elastic Query` page and use `admin/admin` or `user/user` for credentials (`clientId` should be `twitter-app`)

- search documents by text using `get-document-by-text` endpoint. It will return all documents containing the Text + Word Count for this Text. (see the list of text words in `MockKafkaStreamRunner`)

- Congratulations! You successfuly tested `Twitter Analytics` Microservices with CQRS!

- See this README file for AWS Infrastructure Setup: **https://github.com/greeta-twitter-01/twitter-infra**


### Remote Debugging

![Configuration to debug a containerized Java application from IntelliJ IDEA](documentation/06-14.png)

- if you want to debug your AWS kubernetes services remotely, use port forwarding:

```
kubectl port-forward 8002:8002
```


#### Kafka Containers Troubleshooting

- Check containers in the following order:
- `Kafka Server (cp-server)`
- `Init Kafka (cp-kafka)`: container should finish creation of kafka topics successfully and then exit
- `Schema Registry (cp-schema-registry)`
- If everything is sussessful, containers `cp-server` and `cp-schema-registry` should be running without errors and container `cp-kafka` should finish its job and exit without errors.
- if any of the containers has exited with errors, run `docker-compose up -d` again
- if any of the containers stopped responding (check the logs), remove container (docker stop, docker rm ) and run `docker-compose up -d` again
