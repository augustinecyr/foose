package com.sg.vttpminiproject.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class ScoreBat {

    private String matchviewUrl;
    private String thumbnail;
    private String title;



    public String getMatchviewUrl() {
        return matchviewUrl;
    }

    public void setMatchviewUrl(String matchviewUrl) {
        this.matchviewUrl = matchviewUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static ScoreBat create(JsonObject json) {
        final ScoreBat highlight = new ScoreBat();
        highlight.setMatchviewUrl(json.getString("matchviewUrl"));
        highlight.setThumbnail(json.getString("thumbnail"));
        highlight.setTitle(json.getString("title"));
        return highlight;

    }


    public static ScoreBat create(String json) {
        try (StringReader strReader = new StringReader(json)) {
            JsonReader j = Json.createReader(strReader);
            return create(j.readObject());
        }
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("matchviewUrl", this.matchviewUrl)
                .add("thumbnail", this.thumbnail)
                .add("title", this.title)
                .build();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
