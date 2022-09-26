package com.sg.vttpminiproject.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;

    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User create(JsonObject json) {
		final User user = new User();
		user.setFirstName(json.getString("firstname"));
        user.setLastName(json.getString("lastname"));
        user.setEmail(json.getString("email"));
        user.setUsername(json.getString("username"));
        user.setPassword(json.getString("password"));
		return user;
	}

	public static User create(String json) {
		try (StringReader strReader = new StringReader(json)) {
			JsonReader j = Json.createReader(strReader);
			return create(j.readObject());
		}
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
				.add("firstname", this.firstName)
				.add("lastname", this.lastName)
				.add("email", this.email)
                .add("username", this.username)
				.build();
	}
}
