package fr.todd.zanniv.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BirthdayDTO {

    @NotNull
    @JsonProperty("date")
    private LocalDate date;

    @NotNull
    @JsonProperty("firstname")
    private String firstname;

    @NotNull
    @JsonProperty("lastname")
    private String lastname;

    public LocalDate getDate() {
        return date;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "BirthdayDTO{" +
                "date=" + date +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}