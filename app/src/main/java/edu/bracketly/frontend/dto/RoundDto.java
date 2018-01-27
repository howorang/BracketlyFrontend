package edu.bracketly.frontend.dto;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RoundDto implements Serializable {
    private int number;
    private List<MatchDto> matches;
}
