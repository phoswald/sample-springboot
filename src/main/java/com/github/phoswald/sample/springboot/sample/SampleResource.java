package com.github.phoswald.sample.springboot.sample;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/sample")
public class SampleResource {

    private String sampleConfig = System.getProperty("app.sample.config");

    @GetMapping(path = "/time", produces = "text/plain")
    public String getTime() {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @GetMapping(path = "/config", produces = "text/plain")
    public String getConfig() {
        return sampleConfig;
    }

    @PostMapping(path = "/echo", consumes = "text/xml", produces = "text/xml")
    public EchoResponse postEcho(@RequestBody EchoRequest request) {
        EchoResponse response = new EchoResponse();
        response.setOuput("Received " + request.getInput());
        return response;
    }
}
