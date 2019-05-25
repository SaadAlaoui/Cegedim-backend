package com.cegedim.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	private String url = "https://jsonplaceholder.typicode.com/posts";
	
	@RequestMapping("/")
	public List<Post> defaultAction() {
		List<Post> JsonList = new ArrayList<Post>();
		JSONArray arr = new JSONArray("[\r\n" + 
				"  {\r\n" + 
				"    \"userId\": 1,\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\r\n" + 
				"    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\r\n" + 
				"  }]");
		for (int i = 0; i < arr.length(); i++)
		{
			JsonList.add(
					new Post(
							(int)arr.getJSONObject(i).get("id"),
							(int)arr.getJSONObject(i).get("userId"),
							arr.getJSONObject(i).get("title").toString(),
							arr.getJSONObject(i).get("body").toString()
					));
		}
		return JsonList;
	}
}
