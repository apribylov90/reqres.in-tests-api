package ru.alex.models.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestModel {
    private String email;
    private String password;
}
