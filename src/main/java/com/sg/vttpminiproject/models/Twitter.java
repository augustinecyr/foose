package com.sg.vttpminiproject.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Twitter {

	private String id;
	private String text;
	private String created_at;
	private JsonObject entities;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static Twitter create(JsonObject json) {
		final Twitter tweet = new Twitter();
		tweet.setId(json.getString("id"));
		tweet.setText(json.getString("text"));
		tweet.setCreated_at(json.getString("created_at"));
		tweet.setEntities(json.getJsonObject("entities"));
		return tweet;
	}

	public static Twitter create(String json) {
		try (StringReader strReader = new StringReader(json)) {
			JsonReader j = Json.createReader(strReader);
			return create(j.readObject());
		}
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
				.add("id", this.id)
				.add("text", this.text)
				.add("created_at", this.created_at)
				.add("entities", this.entities)
				.build();
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public JsonObject getEntities() {
		return entities;
	}

	public void setEntities(JsonObject entities) {
		this.entities = entities;
	}

}
