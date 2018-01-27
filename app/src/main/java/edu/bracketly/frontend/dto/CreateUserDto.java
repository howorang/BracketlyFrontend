package edu.bracketly.frontend.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateUserDto implements Serializable {
    private String username;
    private String password;
}
