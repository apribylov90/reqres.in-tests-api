package ru.alex.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class UsersResponseModel {

    private int page;

    @JsonProperty("per_page")
    private int perPage;

    private int total;

    @JsonProperty("total_pages")
    private int totalPages;

    private List<UserData> data;

    private Support support;
}
