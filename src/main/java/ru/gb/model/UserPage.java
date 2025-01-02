package ru.gb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPage {

    private String id;
    private String login;
    private String password;
    private String confirmPassword;
    private String phoneNumber;


}
