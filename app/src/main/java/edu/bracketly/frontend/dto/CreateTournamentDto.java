package edu.bracketly.frontend.dto;


import java.io.Serializable;
import java.util.Date;

import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;
import lombok.Data;

@Data
public class CreateTournamentDto implements Serializable {
    private String name;

    private Date eventDate;

    private BRACKET_TYPE bracketType;

    private SEEDING_STRATEGY seedingStrategy;
}
