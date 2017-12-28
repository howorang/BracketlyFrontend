package edu.bracketly.frontend.api;

import java.util.List;

import edu.bracketly.frontend.dto.BracketStateDto;
import edu.bracketly.frontend.dto.MatchDto;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by howor on 26.12.2017.
 */

public interface BracketApi {
    @GET("/bracket/{bracketId}")
    Observable<BracketStateDto> getBracketState(@Path("bracketId") Long bracketId);

    @POST("/bracket/{bracketId}/play/{matchId}")
    Completable playMatch(@Path("bracketId") Long bracketId,
                          @Path("matchId") Long matchId,
                          @Body Long winningSeatId);

    @POST("/bracket/{bracketId}/start/{matchId}")
    Completable startMatch(@Path("bracketId") Long bracketId,
                           @Path("matchId") Long matchId);

    @GET("/bracket/{bracketId}/match/available")
    Observable<List<MatchDto>> getAvailableMatchesForBracket(@Path("bracketId") Long bracketId);

    @GET("/bracket/{bracketId}/match/next")
    Observable<MatchDto> getNextMatchForBracket(@Path("bracketId") Long bracketId);

}
