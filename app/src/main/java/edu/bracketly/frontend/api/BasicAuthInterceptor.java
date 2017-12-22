package edu.bracketly.frontend.api;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by howor on 22.12.2017.
 */

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public void setCredentials(String username, String password) {
        this.credentials = Credentials.basic(username, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (credentials != null) {
            request = request.newBuilder().addHeader("Authorization", credentials).build();
        }
        return chain.proceed(request);
    }
}
