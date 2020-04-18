# sample-springboot

Experiments with Spring Boot, featuring:

- Static web content
- Dynamic web content using Thymeleaf
- REST endpoints using JAX-RS 
- H2 database using JPA 

## Run Standalone

    $ mvn clean verify
    $ java -Dserver.port=8080 \
      -jar target/sample-springboot-0.1.0-SNAPSHOT.jar

## URLs

- http://localhost:8080/

~~~
$ curl 'http://localhost:8080/rest/sample/time'
~~~
