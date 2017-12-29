package edu.bracketly.frontend.api;

import java.util.List;

import edu.bracketly.frontend.dto.PlayerDetailsDto;
import edu.bracketly.frontend.dto.PlayerDto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by howor on 29.12.2017.
 */

public interface PlayerApi {

    @GET("player/all")
    Observable<List<PlayerDto>> getAllPlayers(@Query("page") int page,
                                              @Query("field") String field,
                                              @Query("size") int size,
                                              @Query("direction") String direction);

    @GET("player/{playerId}/details")
    Observable<PlayerDetailsDto> getPlayerDetails(@Path("playerId") Long playerId);

}
