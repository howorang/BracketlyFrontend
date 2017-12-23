package edu.bracketly.frontend.api;

import edu.bracketly.frontend.dto.UserDetailsDto;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by howor on 22.12.2017.
 */

public interface UserApi {
    @GET("user/me")
    public Observable<UserDetailsDto> aboutMe();

    @POST("user/")
    public Completable createUser(@Query("username") String username,
                                  @Query("password") String password);
}
