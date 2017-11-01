package me.hienngo.themoviedemo.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.hienngo.themoviedemo.MovieApp;
import me.hienngo.themoviedemo.domain.interactor.GetDetail;
import me.hienngo.themoviedemo.domain.interactor.GetMovieList;
import me.hienngo.themoviedemo.domain.interactor.LocalConfig;
import me.hienngo.themoviedemo.domain.repo.MovieRepo;

/**
 * @author hienngo
 * @since 10/27/17
 */
@Module
public class AppModule {
    private MovieApp movieApp;
    public AppModule(MovieApp app) {
        this.movieApp = app;
    }

    @Provides @Singleton
    public Context provideContext() {
        return this.movieApp;
    }

    @Provides @Singleton
    public LocalConfig provideLocalConfig(MovieRepo movieRepo) {
        return new LocalConfig(movieRepo);
    }

    @Provides @Singleton
    public GetMovieList provideGetMovieList(MovieRepo movieRepo, LocalConfig config) {
        return new GetMovieList(movieRepo, config);
    }

    @Provides @Singleton
    public GetDetail provideGetDetail(MovieRepo movieRepo, LocalConfig config) {
        return new GetDetail(movieRepo, config);
    }
}
