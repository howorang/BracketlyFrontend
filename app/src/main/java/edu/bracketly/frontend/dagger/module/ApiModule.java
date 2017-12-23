package edu.bracketly.frontend.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.bracketly.frontend.api.BasicAuthInterceptor;
import edu.bracketly.frontend.api.TournamentAPI;
import edu.bracketly.frontend.api.UserAPI;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by howor on 22.12.2017.
 */

@Module
public abstract class ApiModule {

    private static final String BASE_URL = "https://bracketly.herokuapp.com/";

    @Provides
    @Singleton
    static BasicAuthInterceptor provideAuthInterceptor() {
        return new BasicAuthInterceptor();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttp(BasicAuthInterceptor authInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    static UserAPI provideUserAPI(Retrofit retrofit) {
        return retrofit.create(UserAPI.class);
    }

    @Provides
    @Singleton
    static TournamentAPI provideTournamentAPI(Retrofit retrofit) {
        return retrofit.create(TournamentAPI.class);
    }
}
