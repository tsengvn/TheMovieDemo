package me.hienngo.themoviedemo.domain.interactor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author hienngo
 * @since 10/31/17
 */

public class RequestInterceptor implements Interceptor {
    private static final String API_KEY = "c814b2795d7c1859a86aa955d121d6a9";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().addQueryParameter("api_key",getApiKey()).build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }

    private String getApiKey() {
        //fast way, should have better solution, eg: encrypted inside JNI
        return API_KEY;
    }
}
