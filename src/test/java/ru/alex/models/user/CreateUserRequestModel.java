package ru.alex.models.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestModel {
    private String name;
    private String job;
}
