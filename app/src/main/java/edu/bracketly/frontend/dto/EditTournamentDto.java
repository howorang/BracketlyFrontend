package edu.bracketly.frontend.dto;

import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;
import lombok.Data;

import java.util.Date;

@Data
public class EditTournamentDto {

    String name;

    private Date eventDate;

    private BRACKET_TYPE bracketType;

    private SEEDING_STRATEGY seedingStrategy;
}
