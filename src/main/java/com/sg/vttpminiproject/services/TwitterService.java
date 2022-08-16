package com.sg.vttpminiproject.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sg.vttpminiproject.models.Twitter;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class TwitterService {

	 private static final String URL = "https://api.twitter.com/2/users/id/tweets/";
	// stuck at URI building
	// postman GET = https://api.twitter.com/2/users/:id/tweets?max_results=10
	// https://api.twitter.com/2/users/330262748/tweets?max_results=10

	@Value("${BEARER_TOKEN}")
	private String bearerToken;

	public List<Twitter> getTweets() {

		 String url = UriComponentsBuilder
		 .fromHttpUrl(URL)
		 .toUriString();

		RequestEntity<Void> req = RequestEntity
				.get(url)
				.header("Authorization", "Bearer Token= %s".formatted(bearerToken))
				.build();

		RestTemplate invoke = new RestTemplate();

		ResponseEntity<String> resp = invoke.exchange(req, String.class);

		final List<Twitter> tweets = new LinkedList<>();

		try (StringReader strReader = new StringReader(resp.getBody())) {
			JsonReader r = Json.createReader(strReader);
			JsonObject j = r.readObject();
			for (JsonValue v : j.getJsonArray("data"))
				tweets.add(Twitter.create((JsonObject) v));
		}
		return tweets;
	}

}
