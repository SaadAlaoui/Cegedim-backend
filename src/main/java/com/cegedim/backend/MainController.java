package com.cegedim.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	private String url = "https://jsonplaceholder.typicode.com/posts";
	
	@RequestMapping("/")
	public String defaultAction() {
		return "Hello world !!";
	}
}
