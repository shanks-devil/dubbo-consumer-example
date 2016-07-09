package com.github.shanks.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.shanks.consumer.service.DemoWebServie;
import com.github.shanks.domain.Demo;

@RestController
@RequestMapping("/dubbo/demo")
public class DemoController {

	@Autowired
	private DemoWebServie demoWebServie;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Demo getDemo() {
		return demoWebServie.get();
	}
	
}
