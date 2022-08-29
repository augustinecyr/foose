package com.sg.vttpminiproject.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class TransferMarkt {
    // query variables
    private String id;
    private String seasonID;
    // payload variables
    private Number rank;
    private String clubImage;
    private String clubName;
    private Number matches;
    private Number wins;
    private Number draws;
    private Number losses;
    private Number goals;
    private Number goalsConceded;
    private Number goalDifference;
    private Number points;

    public String getClubImage() {
        return clubImage;
    }

    public void setClubImage(String clubImage) {
        this.clubImage = clubImage;
    }

    public Number getMatches() {
        return matches;
    }

    public void setMatches(Number matches) {
        this.matches = matches;
    }

    public Number getWins() {
        return wins;
    }

    public void setWins(Number wins) {
        this.wins = wins;
    }

    public Number getDraws() {
        return draws;
    }

    public void setDraws(Number draws) {
        this.draws = draws;
    }

    public Number getLosses() {
        return losses;
    }

    public void setLosses(Number losses) {
        this.losses = losses;
    }

    public Number getGoals() {
        return goals;
    }

    public void setGoals(Number goals) {
        this.goals = goals;
    }

    public Number getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(Number goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public Number getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Number goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Number getPoints() {
        return points;
    }

    public void setPoints(Number points) {
        this.points = points;
    }

    public Number getRank() {
        return rank;
    }

    public void setRank(Number rank) {
        this.rank = rank;
    }

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
        table.setRank(json.getInt("rank"));
        table.setClubImage(json.getString("clubImage"));
        table.setClubName(json.getString("clubName"));
        table.setMatches(json.getInt("matches"));
        

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
                .add("rank", this.rank.toString())
                .add("clubImage", this.clubImage)
                .add("clubName", this.clubName)
                .add("matches", this.matches.toString())

                
                .build();
    }

}
