package edu.bracketly.frontend.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TournamentStartResponseDto implements Serializable {
    private Long bracketId;
}
