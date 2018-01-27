package edu.bracketly.frontend.dto;

import java.io.Serializable;
import java.util.Date;

import edu.bracketly.frontend.consts.TOURNAMENT_STATUS;
import lombok.Data;

@Data
public class TournamentSimpleDto implements Serializable {
    private Long id;
    private String name;
    private Date creationDate;
    private Date eventDate;
    private TOURNAMENT_STATUS tournamentStatus;
}
