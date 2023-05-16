# sample-springboot

Experiments with Spring Boot and Docker, featuring:

- Static web content
- Dynamic web content using Spring MVC and Thymeleaf
- REST endpoints using Spring MVC
- H2 database using JPA and Hibernate

## Run Standalone

~~~
$ mvn clean verify
$ export APP_SAMPLE_CONFIG=ValueFromShell
$ java \
  -Dserver.port=8080 \
  -Dspring.datasource.url=jdbc:h2:./databases/task-db \
  -jar target/sample-springboot-0.1.0-SNAPSHOT.jar
~~~

## Run with Docker

~~~
$ mvn clean verify -P docker
$ docker run -it --name sample-springboot --rm \
  -p 8080:8080 \
  -e APP_SAMPLE_CONFIG=ValueFromDockerRun \
  -e SPRING_DATASOURCE_URL=jdbc:h2:/databases/task-db \
  -v ./databases/:/databases \
  sample-springboot:0.1.0-SNAPSHOT
~~~

# URLs

- http://localhost:8080/

~~~
$ curl 'http://localhost:8080/app/rest/sample/time' -i
$ curl 'http://localhost:8080/app/rest/sample/config' -i
$ curl 'http://localhost:8080/app/rest/sample/echo-xml' -i -X POST \
  -H 'content-type: text/xml' \
  -d '<EchoRequest><input>This is CURL</input></EchoRequest>'
$ curl 'http://localhost:8080/app/rest/sample/echo-json' -i -X POST \
  -H 'content-type: application/json' \
  -d '{"input":"This is CURL"}'
$ curl 'http://localhost:8080/app/rest/tasks' -i
$ curl 'http://localhost:8080/app/rest/tasks' -i -X POST \
  -H 'content-type: application/json' \
  -d '{"title":"Some task","description":"This is CURL","done":true}'
$ curl 'http://localhost:8080/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i
$ curl 'http://localhost:8080/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i -X PUT \
  -H 'content-type: application/json' \
  -d '{"title":"Some updated task","description":"This is still CURL","done":false}'
$ curl 'http://localhost:8080/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i -X DELETE
~~~
