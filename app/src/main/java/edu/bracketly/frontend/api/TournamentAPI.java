package edu.bracketly.frontend.api;

import java.util.Date;

import edu.bracketly.frontend.dto.TournamentSimpleDto;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Completable;
import rx.Observable;

/**
 * Created by howor on 22.12.2017.
 */

public interface TournamentAPI {
    @POST("/tournament")
    public Completable createTournament(@Query("name") String name,
                                        @Query("eventDate") Date eventDate,
                                        @Query("bracketType") String bracketType,
                                        @Query("seedingStrategy") String seedingStrategy);

    @GET("/tournament/all")
    public Observable<TournamentSimpleDto> getAllTournaments();

}
