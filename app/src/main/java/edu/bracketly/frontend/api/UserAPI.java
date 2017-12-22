package edu.bracketly.frontend.api;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Completable;

/**
 * Created by howor on 22.12.2017.
 */

public interface UserAPI {
    @POST("user/")
    public Completable createUser(@Query("username") String username,
                                  @Query("password") String password);
}
