package com.github.phoswald.sample.sample;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/rest/sample")
public class SampleResource {

	@Autowired
	private SampleConfig config;

    @GetMapping(path = "/time", produces = "text/plain")
    public String getTime() {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @GetMapping(path = "/config", produces = "text/plain")
    public String getConfig() {
        return config.getConfig();
    }

    @PostMapping(path = "/echo-xml", consumes = "text/xml", produces = "text/xml")
    public EchoResponse postEchoXml(@RequestBody EchoRequest request) {
        EchoResponse response = new EchoResponse();
        response.setOuput("Received " + request.getInput());
        return response;
    }

    @PostMapping(path = "/echo-json", consumes = "application/json", produces = "application/json")
    public EchoResponse postEchoJson(@RequestBody EchoRequest request) {
        EchoResponse response = new EchoResponse();
        response.setOuput("Received " + request.getInput());
        return response;
    }
}
