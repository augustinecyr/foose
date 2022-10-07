package com.sg.vttpminiproject.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Contact {
    String name;
    String email;
    String message;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", this.name)
                .add("email", this.email)
                .add("message", this.message)
                .build();
    }

    public static Contact create(String json) {
        try (StringReader strReader = new StringReader(json)) {
            JsonReader j = Json.createReader(strReader);
            return create(j.readObject());
        }
    }

    public static Contact create(JsonObject json) {
        final Contact contact = new Contact();
        contact.setName(json.getString("name"));
        contact.setEmail(json.getString("email"));
        contact.setMessage(json.getString("message"));
        return contact;
    }

}
