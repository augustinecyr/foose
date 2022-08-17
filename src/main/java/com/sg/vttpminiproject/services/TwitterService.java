package com.sg.vttpminiproject.services;

import java.io.StringReader;
import java.util.Collections;
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

	private static final String URL = "https://api.twitter.com/2/users/330262748/tweets";
	// stuck at URI building - *SOLVED*
	// postman GET = https://api.twitter.com/2/users/:id/tweets
	// https://api.twitter.com/2/users/330262748/tweets

	@Value("${BEARER_TOKEN}")
	private String bearerToken;

	public List<Twitter> getTweets() {

		String payload;
		System.out.println("Starting program....");

		try {

			String url = UriComponentsBuilder
					.fromUriString(URL)
					.encode()
					.toUriString();

			RequestEntity<Void> req = RequestEntity
					.get(url)
					.header("Authorization", "Bearer %s".formatted(bearerToken)) // rmb to set BEARER_TOKEN on cli
					.build();

			System.out.println("url: " + url); // prints out the URL that is built
			 
			RestTemplate template = new RestTemplate();

			ResponseEntity<String> resp;
			resp = template.exchange(req, String.class);

			payload = resp.getBody();

			System.out.println(">>>> payload: " + payload); // the payload if everything goes well lmao

		} catch (Exception ex) {
			System.err.printf("Error: %s\n", ex.getMessage()); // helps with problem solving SO MUCH
			return Collections.emptyList();
		}


		List<Twitter> tweets = new LinkedList<>();

		try (StringReader strReader = new StringReader(payload)) {
			JsonReader r = Json.createReader(strReader);
			JsonObject j = r.readObject();
			for (JsonValue v : j.getJsonArray("data"))
				tweets.add(Twitter.create((JsonObject) v));
		}

		return tweets;
	}

}
