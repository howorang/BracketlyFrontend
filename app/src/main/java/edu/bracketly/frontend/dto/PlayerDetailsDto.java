package edu.bracketly.frontend.dto;

import java.util.List;

/**
 * Created by howor on 29.12.2017.
 */

public class PlayerDetailsDto extends PlayerDto {
    List<TournamentDto> tournaments;
    List<MatchDto> liveMatches;

    public List<TournamentDto> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentDto> tournaments) {
        this.tournaments = tournaments;
    }

    public List<MatchDto> getLiveMatches() {
        return liveMatches;
    }

    public void setLiveMatches(List<MatchDto> liveMatches) {
        this.liveMatches = liveMatches;
    }
}
