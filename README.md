# sample-springboot

Experiments with Spring Boot, featuring:

- Static web content
- Dynamic web content using Spring MVC and Thymeleaf
- REST endpoints using Spring MVC
- H2 database using JPA and Hibernate

## Run Standalone

    $ mvn clean verify
    $ java \
      -Dserver.port=8080 \
      -Dapp.sample.config=SampleConfigValue \
      -jar target/sample-springboot-0.1.0-SNAPSHOT.jar

## URLs

- http://localhost:8080/

~~~
$ curl 'http://localhost:8080/rest/sample/time'
$ curl 'http://localhost:8080/rest/sample/config'
$ curl 'http://localhost:8080/rest/sample/echo' -i -X POST \
  -H 'content-type: text/xml' \
  -d '<EchoRequest><input>This is CURL</input></EchoRequest>'
$ curl 'http://localhost:8080/rest/tasks' -i
$ curl 'http://localhost:8080/rest/tasks' -i -X POST \
  -H 'content-type: application/json' \
  -d '{"title":"Some task","description":"This is CURL","done":true}'
$ curl 'http://localhost:8080/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i
$ curl 'http://localhost:8080/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i -X PUT \
  -H 'content-type: application/json' \
  -d '{"title":"Some updated task","description":"This is still CURL","done":false}'
$ curl 'http://localhost:8080/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i -X DELETE
~~~
