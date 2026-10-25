package com.github.phoswald.sample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.startsWith;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "app.sample.config=ValueFromTestProperties")
class ApplicationTest {

    @Test
    void getIndexPage() {
        when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType("text/html")
                .body(startsWith("<!doctype html>"))
                .body(containsString("<title>Spring Boot Sample Service</title>"));
    }

    @Test
    void getTime() {
        when()
                .get("/app/rest/sample/time")
                .then()
                .statusCode(200)
                .body(matchesRegex("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]+(Z|[+-][0-9]{2}:[0-9]{2})\\[.+]\\n"));
    }

    @Test
    void getConfig() {
        when()
                .get("/app/rest/sample/config")
                .then()
                .statusCode(200)
                .body(equalTo("ValueFromTestProperties\n"));
    }

    @Test
    @Disabled
    void postEchoXml() {
        given()
                .contentType("application/xml")
                .body("<echoRequest><input>Test Input</input></echoRequest>")
                .when()
                .post("/app/rest/sample/echo-xml")
                .then()
                .statusCode(200)
                .contentType("application/xml")
                .body(equalTo("""
                        <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                        <echoResponse>
                            <output>Received Test Input</output>
                        </echoResponse>
                        """));
    }

    @Test
    void postEchoJson() {
        given()
                .contentType("application/json")
                .body("{\"input\":\"Test Input\"}")
                .when()
                .post("/app/rest/sample/echo-json")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body(containsString("Received Test Input"));
//                .body(equalTo("""
//                        {
//                            "output": "Received Test Input"
//                        }
//                        """));
    }

    @Test
    void getSamplePage() {
        given()
                .auth().preemptive().basic("username1", "password1")
                .when()
                .get("/app/pages/sample")
                .then()
                .statusCode(200)
                .contentType("text/html")
                .body(startsWith("<!doctype html>"))
                .body(containsString("<title>Sample Page</title>"))
                //.body(containsString("<span>Hello</span>, <span>username1</span>!")) // #greeting, username
                .body(containsString("<td>ValueFromTestProperties</td>")); // sampleConfig
    }
}
