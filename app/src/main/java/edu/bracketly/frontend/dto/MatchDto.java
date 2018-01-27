package edu.bracketly.frontend.dto;

import java.io.Serializable;
import java.util.List;

import edu.bracketly.frontend.consts.MATCH_STATUS;
import lombok.Data;


@Data
public class MatchDto implements Serializable {
    private Long id;
    private String tag;
    private List<SeatDto> seats;
    private MATCH_STATUS matchStatus;
    private PlayerDto winner;
}
