package me.hienngo.themoviedemo.domain.interactor;

import me.hienngo.themoviedemo.domain.repo.MovieRepo;
import me.hienngo.themoviedemo.model.viewmodel.MovieDetailViewModel;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author hienngo
 * @since 10/31/17
 */

public class GetDetail {
    private final MovieRepo movieRepo;
    private final LocalConfig localConfig;

    public GetDetail(MovieRepo movieRepo, LocalConfig localConfig) {
        this.movieRepo = movieRepo;
        this.localConfig = localConfig;
    }

    public Observable<MovieDetailViewModel> get(long id) {
        return this.movieRepo.getDetail(id)
                .subscribeOn(Schedulers.io())
                .withLatestFrom(localConfig.getConfiguration(), MovieDetailViewModel::new);
    }
}
