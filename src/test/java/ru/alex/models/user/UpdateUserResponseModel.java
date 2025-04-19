package ru.alex.models.user;

import lombok.Getter;

@Getter
public class UpdateUserResponseModel {
    private String name;
    private String job;
    private String updatedAt;
}
