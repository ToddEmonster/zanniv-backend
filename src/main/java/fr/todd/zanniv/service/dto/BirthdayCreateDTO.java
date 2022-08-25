package fr.todd.zanniv.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BirthdayCreateDTO {

    @NotNull
    @JsonProperty("date")
    protected LocalDate date;

    @NotNull
    @JsonProperty("firstname")
    protected String firstname;

    @NotNull
    @JsonProperty("lastname")
    protected String lastname;

    public LocalDate getDate() {
        return date;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "BirthdayCreateDTO{" +
                "date=" + date +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
