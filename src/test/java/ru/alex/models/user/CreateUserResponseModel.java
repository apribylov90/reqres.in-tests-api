package ru.alex.models.user;

import lombok.Getter;

@Getter
public class CreateUserResponseModel {
    private String name;
    private String job;
    private String id;
    private String createdAt;

}
