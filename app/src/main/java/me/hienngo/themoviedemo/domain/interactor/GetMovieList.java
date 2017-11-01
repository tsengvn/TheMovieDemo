package me.hienngo.themoviedemo.domain.interactor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hienngo.themoviedemo.domain.repo.MovieRepo;
import me.hienngo.themoviedemo.model.viewmodel.MovieViewModel;
import me.hienngo.themoviedemo.util.StringUtils;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

/**
 * @author hienngo
 * @since 10/29/17
 */

public class GetMovieList {
    private final MovieRepo movieRepo;
    private final LocalConfig localConfig;

    private ReplaySubject<List<MovieViewModel>> dataSubject;
    private int currentPage = 0;
    private int maxPage = 1;
    private Map<String, String> queryMap;
    public GetMovieList(MovieRepo movieRepo, LocalConfig localConfig) {
        this.movieRepo = movieRepo;
        this.localConfig = localConfig;
        this.dataSubject = ReplaySubject.create();
        this.currentPage = 0;
        queryMap = new HashMap<>();
    }

    public Observable<List<MovieViewModel>> getData(long startDate, long endDate) {
        clear();
        queryMap.put("primary_release_date.gte", StringUtils.parseDateQuery(startDate));
        queryMap.put("primary_release_date.lte", StringUtils.parseDateQuery(endDate));
        queryMap.put("sort_by", "primary_release_date.desc");
        if (dataSubject != null) {
            dataSubject.onCompleted();
        }
        dataSubject = ReplaySubject.create();
        loadData(++currentPage);
        return dataSubject.asObservable();
    }

    public void loadMore() {
        currentPage++;
        if (currentPage <= maxPage) {
            loadData(currentPage);
        }
    }

    private void loadData(int page) {
        queryMap.put("page", String.valueOf(page));
        this.movieRepo.getMovieList(queryMap)
                .subscribeOn(Schedulers.io())
                .doOnNext(movieDataListResponse -> this.maxPage = movieDataListResponse.totalPages)
                .flatMap(movieResponse -> Observable.from(movieResponse.results))
                .withLatestFrom(localConfig.getConfiguration(), MovieViewModel::new)
                .toList()
                .subscribe(dataList -> dataSubject.onNext(dataList), Throwable::printStackTrace);
    }

    private void clear() {
        currentPage = 0;
        if (dataSubject != null) {
            dataSubject.onCompleted();
        }
        queryMap.clear();
    }
}
