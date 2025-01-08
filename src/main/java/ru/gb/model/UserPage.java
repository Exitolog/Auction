package ru.gb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPage {

    private String login;
    private String password;
    private String confirmPassword;
    private String phoneNumber;


}
