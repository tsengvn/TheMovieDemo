package me.hienngo.themoviedemo.di.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.hienngo.themoviedemo.BuildConfig;
import me.hienngo.themoviedemo.domain.interactor.RequestInterceptor;
import me.hienngo.themoviedemo.domain.repo.MovieRepo;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hienngo
 * @since 10/29/17
 */
@Module
public class NetworkModule {
    private static final String END_POINT = "https://api.themoviedb.org/3/";

    @Singleton
    @Provides
    public OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new RequestInterceptor())
                .build();
    }

    @Singleton @Provides
    public MovieRepo provideMovieRepo(OkHttpClient okHttpClient, Converter.Factory factory) {
        return new Retrofit.Builder().baseUrl(END_POINT)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MovieRepo.class);
    }

    @Singleton @Provides
    public Converter.Factory provideConverterFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }

}
