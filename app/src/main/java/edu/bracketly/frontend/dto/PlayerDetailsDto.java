package edu.bracketly.frontend.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by howor on 29.12.2017.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlayerDetailsDto extends PlayerDto {
    List<TournamentDto> tournaments;
    List<MatchDto> liveMatches;
}
