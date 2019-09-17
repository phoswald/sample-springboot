package com.github.phoswald.sample.springboot.sample;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleResource {

    private String settingA = "A?"; // TODO make configurable

    private String settingB = "B?"; // TODO make configurable

    @GetMapping(path = "/rest/sample/time", produces = "text/plain")
    public String getTime() {
        String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        return "The current time is " + now;
    }

    @GetMapping(path = "/rest/sample/config", produces = "text/plain")
    public String getConfig() {
        String config = "" + //
                "SampleConfigSettingA = " + settingA + "\n" + //
                "SampleConfigSettingB = " + settingB + "\n"; //
        return config;
    }

    @PostMapping(path = "/rest/sample/echo", consumes = "text/xml", produces = "text/xml")
    public EchoResponse postEcho(@RequestBody EchoRequest request) {
        EchoResponse response = new EchoResponse();
        response.setOuput("Received " + request.getInput());
        return response;
    }
}
