package me.hienngo.themoviedemo.ui.main;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.Calendar;
import java.util.List;

import me.hienngo.themoviedemo.domain.interactor.GetMovieList;
import me.hienngo.themoviedemo.model.viewmodel.MovieViewModel;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author hienngo
 * @since 10/29/17
 */

public class MainPresenter extends MvpBasePresenter<MainView>{

    private final GetMovieList getMovieList;

    private Subscription subscription;
    public MainPresenter(GetMovieList getMovieList) {
        this.getMovieList = getMovieList;
    }

    public void loadData() {
        Calendar calendar = Calendar.getInstance();
        long endDate = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        long startDate = calendar.getTimeInMillis();
        loadData(startDate, endDate);
    }

    public void loadData(long startDate, long endDate) {
        subscription = getMovieList.getData(startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DataObserver());
    }

    public void onStop() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public void onLoadMore() {
        getMovieList.loadMore();
    }

    private class DataObserver implements Observer<List<MovieViewModel>> {
        @Override
        public void onCompleted() {
            getView().clearList();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            getView().showError();
        }

        @Override
        public void onNext(List<MovieViewModel> movieOverviews) {
            getView().onReceiveData(movieOverviews);
        }
    }
}
