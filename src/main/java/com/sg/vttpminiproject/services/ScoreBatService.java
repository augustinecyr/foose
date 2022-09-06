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

import com.sg.vttpminiproject.models.ScoreBat;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class ScoreBatService {

    private static final String URL = "https://www.scorebat.com/video-api/v3/feed";

    @Value("${YOUR_API_TOKEN}")
    private String apiToken;

    public List<ScoreBat> getHighlights() {

        String payload;
        System.out.println("Fetching videos from Scorebat.com...");

        try {

            String url = UriComponentsBuilder
                    .fromUriString(URL)
                    .queryParam("token", "%s".formatted(apiToken))
                    .encode()
                    .toUriString();

            RequestEntity<Void> req = RequestEntity
                    .get(url)
                    .build();

            System.out.println(">>> [url]: " + url);
            System.out.println("-----------------------------------------------------------");

            RestTemplate template = new RestTemplate();

            ResponseEntity<String> resp;
            resp = template.exchange(req, String.class);

            payload = resp.getBody();

            System.out.println(">>> [payload]: " + payload); // the payload if everything goes well lmao
            System.out.println("-----------------------------------------------------------");

        } catch (Exception ex) {
            System.err.printf("Error: %s\n", ex.getMessage()); // helps with problem solving SO MUCH
            return Collections.emptyList();
        }

        List<ScoreBat> highlights = new LinkedList<>();
        
        try (StringReader strRdr = new StringReader(payload)) {
            JsonReader r = Json.createReader(strRdr);
            JsonObject j = r.readObject();
            
            for (JsonValue v : j.getJsonArray("response")) {
                highlights.add(ScoreBat.create((JsonObject) v));
            }

            return highlights;
        }
    }
}
