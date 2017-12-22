package edu.bracketly.frontend.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String password;
}
