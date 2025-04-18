package ru.alex.models.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestModel {

    private String email;

    private String password;
}
