package edu.bracketly.frontend.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.bracketly.frontend.api.BasicAuthInterceptor;
import edu.bracketly.frontend.api.BracketApi;
import edu.bracketly.frontend.api.PlayerApi;
import edu.bracketly.frontend.api.RuntimeTypeAdapterFactory;
import edu.bracketly.frontend.api.SingleEliminationBracketApi;
import edu.bracketly.frontend.api.TournamentApi;
import edu.bracketly.frontend.api.UserApi;
import edu.bracketly.frontend.dto.BracketStateDto;
import edu.bracketly.frontend.dto.PlayerDetailsDto;
import edu.bracketly.frontend.dto.PlayerDto;
import edu.bracketly.frontend.dto.SingleBracketStateDto;
import edu.bracketly.frontend.dto.UserDto;
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
    private static final String DATE_FORMAT = "yyyy-MM-dd/HH:mm:ss";

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
    static Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .setLenient()
                .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(BracketStateDto.class)
                        .registerSubtype(SingleBracketStateDto.class))
                .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(UserDto.class)
                        .registerSubtype(PlayerDto.class)
                        .registerSubtype(UserDto.class)
                        .registerSubtype(PlayerDetailsDto.class))
                .create();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    static UserApi provideUserAPI(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    @Provides
    @Singleton
    static TournamentApi provideTournamentAPI(Retrofit retrofit) {
        return retrofit.create(TournamentApi.class);
    }

    @Provides
    @Singleton
    static BracketApi provideBracketApi(Retrofit retrofit) {
        return retrofit.create(BracketApi.class);
    }

    @Provides
    @Singleton
    static SingleEliminationBracketApi provideSingleEliminationBracketApi(Retrofit retrofit) {
        return retrofit.create(SingleEliminationBracketApi.class);
    }

    @Provides
    @Singleton
    static PlayerApi providePlayerApi(Retrofit retrofit) {
        return retrofit.create(PlayerApi.class);
    }
}
