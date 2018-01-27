package edu.bracketly.frontend.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by howor on 29.12.2017.
 */

@EqualsAndHashCode
@Data
public class UserDto implements Serializable {
    private long id;
    private String username;
}
