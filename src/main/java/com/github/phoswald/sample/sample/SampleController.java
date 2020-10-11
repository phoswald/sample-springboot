package com.github.phoswald.sample.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/pages/sample")
public class SampleController {

	@Autowired
	private SampleConfig config;

    @GetMapping
    public ModelAndView getSamplePage() {
        return new ModelAndView("sample", "model", new SampleViewModel(config.getConfig()));
    }
}
