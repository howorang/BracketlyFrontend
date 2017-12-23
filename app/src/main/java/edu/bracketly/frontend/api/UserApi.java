package edu.bracketly.frontend.api;

import io.reactivex.Completable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by howor on 22.12.2017.
 */

public interface UserApi {
    @POST("user/")
    public Completable createUser(@Query("username") String username,
                                  @Query("password") String password);
}
