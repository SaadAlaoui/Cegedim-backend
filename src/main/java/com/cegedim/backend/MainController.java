package com.cegedim.backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
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
		JSONArray arr = new JSONArray(callURL(this.url));
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
	
	private String callURL(String myURL) {
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 
 
		return sb.toString();
	}
}
