package edu.bracketly.frontend.dto;


import java.util.Date;

import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;
import edu.bracketly.frontend.consts.TOURNAMENT_STATUS;

public class TournamentDto {

    private Long id;
    private String name;
    private Long organizerId;
    private BRACKET_TYPE bracketType;
    private SEEDING_STRATEGY seedingStrategy;
    private TOURNAMENT_STATUS tournamentStatus;
    private Long bracketId;
    private Date creationDate;
    private Date eventDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public BRACKET_TYPE getBracketType() {
        return bracketType;
    }

    public void setBracketType(BRACKET_TYPE bracketType) {
        this.bracketType = bracketType;
    }

    public SEEDING_STRATEGY getSeedingStrategy() {
        return seedingStrategy;
    }

    public void setSeedingStrategy(SEEDING_STRATEGY seedingStrategy) {
        this.seedingStrategy = seedingStrategy;
    }

    public TOURNAMENT_STATUS getTournamentStatus() {
        return tournamentStatus;
    }

    public void setTournamentStatus(TOURNAMENT_STATUS tournamentStatus) {
        this.tournamentStatus = tournamentStatus;
    }

    public Long getBracketId() {
        return bracketId;
    }

    public void setBracketId(Long bracketId) {
        this.bracketId = bracketId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
