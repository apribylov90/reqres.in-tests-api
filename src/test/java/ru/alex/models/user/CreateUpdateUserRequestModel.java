package ru.alex.models.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdateUserRequestModel {
    private String name;
    private String job;
}
