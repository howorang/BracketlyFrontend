package edu.bracketly.frontend.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SeatDto implements Serializable {
    private long id;
    private PlayerDto player;
}
