package com.sg.vttpminiproject.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sg.vttpminiproject.models.TransferMarkt;
import com.sg.vttpminiproject.repositories.TransferMarktRepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;

@Service
public class TransferMarktService {
    private static final String URL = "https://transfermarket.p.rapidapi.com/competitions/get-table";
	// https://transfermarket.p.rapidapi.com/competitions/get-table?id=GB1&seasonID=2022 // example for EPL table.

	@Value("${X-RapidAPI-Key}")
	private String rapidAPI;

	@Autowired
	private TransferMarktRepository trfRepo;

	public List<TransferMarkt> getTable(String id, String seasonID) {

		String payload;
		System.out.println("Attempting request to TransferMarkt.com....");

		try {

			String url = UriComponentsBuilder
					.fromUriString(URL)
					.queryParam("id", id)
                    .queryParam("seasonID", seasonID)
					.encode()
					.toUriString();

			RequestEntity<Void> req = RequestEntity
					.get(url)
                    .header("X-RapidAPI-Key","%s".formatted(rapidAPI))// rapidAPI key
					.build();

			System.out.println(">>> [url]: " + url); // prints out the URL that is built

			System.out.println("-----------------------------------------------------------");

			RestTemplate template = new RestTemplate();

			ResponseEntity<String> resp;
			resp = template.exchange(req, String.class);

			payload = resp.getBody();

			System.out.println(">>> [payload]: " + payload); // the payload
			System.out.println("-----------------------------------------------------------");

		} catch (Exception ex) {
			System.err.printf("Error: %s\n", ex.getMessage()); // troubleshooting 
			return Collections.emptyList();
		}

		List<TransferMarkt> table = new LinkedList<>();

		try (StringReader strReader = new StringReader(payload)) {
			JsonReader r = Json.createReader(strReader);
			JsonObject j = r.readObject();

            JsonArray leagueTable = j.getJsonArray("table");
            JsonObject first = leagueTable.getJsonObject(0); // first team of the table
            JsonString firstName = first.getJsonString("clubName"); 
            JsonObject last = leagueTable.getJsonObject(19);
            JsonString lastName = last.getJsonString("clubName");

			System.out.println("Total number of teams: " + leagueTable.size());
            System.out.println("-----------------------------------------------------------");
            System.out.println("Top of the table: " + firstName);
            System.out.println("-----------------------------------------------------------");
            System.out.println("Last of the table: " + lastName);
            System.out.println("-----------------------------------------------------------");


            
			for (JsonValue v : j.getJsonArray("table")) {

				table.add(TransferMarkt.create((JsonObject) v));

			}


		}

		trfRepo.save(table); // enter the tweet id to view it on redis cli

		return table;
	}
}
