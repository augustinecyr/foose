package com.sg.vttpminiproject.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class TransferMarkt {
    String id;
    String seasonID;
    String clubName;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSeasonID() {
        return seasonID;
    }
    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public static TransferMarkt create(JsonObject json) {
		final TransferMarkt table = new TransferMarkt();
		table.setClubName(json.getString("clubName"));
		
		return table;
	}

	public static TransferMarkt create(String json) {
		try (StringReader strReader = new StringReader(json)) {
			JsonReader j = Json.createReader(strReader);
			return create(j.readObject());
		}
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
        .add("clubName", this.clubName)
				.build();
	}
}
