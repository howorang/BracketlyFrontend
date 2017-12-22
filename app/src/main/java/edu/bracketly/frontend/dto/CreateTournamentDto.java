package edu.bracketly.frontend.dto;


import java.util.Date;

import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;
import lombok.Data;

@Data
public class CreateTournamentDto {
    private String name;

    private Date eventDate;

    private BRACKET_TYPE bracketType;

    private SEEDING_STRATEGY seedingStrategy;
}
