package edu.bracketly.frontend.dto;


import java.util.Date;

import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;

public class CreateTournamentDto {
    private String name;

    private Date eventDate;

    private BRACKET_TYPE bracketType;

    private SEEDING_STRATEGY seedingStrategy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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
}
