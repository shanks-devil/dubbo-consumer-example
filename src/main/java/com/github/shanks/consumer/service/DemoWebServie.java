package com.github.shanks.consumer.service;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.shanks.api.ExampleService;
import com.github.shanks.domain.Demo;

@Service
public class DemoWebServie {

	@Reference(version = "1.0.0")
	private ExampleService exampleService;

	public Demo get() {
		Demo demo = exampleService.getOne();
		// do something
		return demo;
	}

}
