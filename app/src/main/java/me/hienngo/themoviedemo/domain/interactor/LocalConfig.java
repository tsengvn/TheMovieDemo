package me.hienngo.themoviedemo.domain.interactor;

import me.hienngo.themoviedemo.domain.repo.MovieRepo;
import me.hienngo.themoviedemo.model.Configuration;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author hienngo
 * @since 10/31/17
 */

public class LocalConfig {
    protected final MovieRepo movieRepo;
    private Configuration configuration = null;

    public LocalConfig(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    private Observable<Configuration> fetchConfiguration() {
        return this.movieRepo.getConfiguration()
                .subscribeOn(Schedulers.io())
                .doOnNext(configuration -> this.configuration = configuration);
    }

    public Observable<Configuration> getConfiguration() {
        if (configuration == null) {
            return fetchConfiguration();
        } else {
            return Observable.just(configuration);
        }
    }
}
