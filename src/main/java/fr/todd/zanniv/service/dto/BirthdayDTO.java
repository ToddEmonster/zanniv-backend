package fr.todd.zanniv.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class BirthdayDTO extends BirthdayCreateDTO {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("userId")
    private Long userId;


    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BirthdayDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
