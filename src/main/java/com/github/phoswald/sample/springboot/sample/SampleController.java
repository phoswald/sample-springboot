package com.github.phoswald.sample.springboot.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

    @GetMapping("/pages/sample")
    public ModelAndView getSamplePage() {
        return new ModelAndView("sample", "model", new SampleViewModel());
    }
}
