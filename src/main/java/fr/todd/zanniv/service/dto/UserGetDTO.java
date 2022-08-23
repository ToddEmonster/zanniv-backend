package fr.todd.zanniv.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserGetDTO {
    @JsonProperty("id")
    private int id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserGetDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + username + '\'' +
                '}';
    }
}