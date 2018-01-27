package edu.bracketly.frontend.dto;


import java.io.Serializable;
import java.util.Date;

import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;
import edu.bracketly.frontend.consts.TOURNAMENT_STATUS;
import lombok.Data;

@Data
public class TournamentDto implements Serializable {

    private Long id;
    private String name;
    private Long organizerId;
    private BRACKET_TYPE bracketType;
    private SEEDING_STRATEGY seedingStrategy;
    private TOURNAMENT_STATUS tournamentStatus;
    private Long bracketId;
    private Date creationDate;
    private Date eventDate;
}
