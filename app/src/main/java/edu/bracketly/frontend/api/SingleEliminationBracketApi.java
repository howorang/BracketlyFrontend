package edu.bracketly.frontend.api;

import java.util.List;

import edu.bracketly.frontend.dto.RoundDto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by howor on 27.12.2017.
 */

public interface SingleEliminationBracketApi {
    @GET("/single-bracket/{bracketId}/round/{roundNumber}")
    Observable<RoundDto> getRound(@Path("bracketId") Long bracketId,
                                  @Path("roundNumber") Integer roundNumber);

    @GET("/single-bracket/{bracketId}/rounds")
    Observable<List<RoundDto>> getAllRounds(@Path("bracketId") Long bracketId);
}
