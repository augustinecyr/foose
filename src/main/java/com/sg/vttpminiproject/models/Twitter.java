package com.sg.vttpminiproject.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Twitter {

    private String id;
    private String text;

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
			.build();
	}


}
