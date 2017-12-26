package edu.bracketly.frontend.api;

import java.util.List;

import edu.bracketly.frontend.dto.TournamentDto;
import edu.bracketly.frontend.dto.TournamentSimpleDto;
import edu.bracketly.frontend.dto.TournamentStartResponseDto;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by howor on 22.12.2017.
 */

public interface TournamentApi {

    @GET("/tournament/{tournamentId}")
    public Observable<TournamentDto> getTournamentDetails(@Path("tournamentId") Long tournamentId);


    @POST("/tournament")
    Completable createTournament(@Query("name") String name,
                                 @Query("eventDate") String eventDate,
                                 @Query("bracketType") String bracketType,
                                 @Query("seedingStrategy") String seedingStrategy);

    @GET("/tournament/all")
    Observable<List<TournamentSimpleDto>> getAllTournaments(@Query("page") int page,
                                                            @Query("field") String field,
                                                            @Query("size") int size,
                                                            @Query("direction") String direction);

    @PUT("/tournament/join/{tournamentId}")
    Completable joinTournament(@Path("tournamentId") Long tournamentId);

    @POST("/tournament/start/{tournamentId}")
    Observable<TournamentStartResponseDto> startTournament(@Path("tournamentId") Long tournamentId);

    @PUT("/tournament/{tournamentId}")
    Completable editTournament(@Path("tournamentId") Long tournamentId);

}
