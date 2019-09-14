package com.github.phoswald.sample.springboot.sample;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleResource {

    @RequestMapping(path="/rest/sample/time", produces="text/plain")
    public String getTime() {
        String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        return "The current time is " + now;
    }
}
