package me.hienngo.themoviedemo.ui.detail;

import android.content.Intent;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import me.hienngo.themoviedemo.domain.interactor.GetDetail;
import me.hienngo.themoviedemo.model.viewmodel.MovieDetailViewModel;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author hienngo
 * @since 10/31/17
 */

public class DetailPresenter extends MvpBasePresenter<DetailView> {
    private final GetDetail getDetail;

    private Subscription subscription;
    public DetailPresenter(GetDetail getDetail) {
        this.getDetail = getDetail;
    }

    public void loadData(Intent intent) {
        long id = intent.getLongExtra("id", -1);
        String title = intent.getStringExtra("title");
        getView().setTitle(title);
        if (id != -1) {
            subscription = getDetail.get(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DataObserver());
        } else {
            getView().showError();
        }
    }
    public void onStop() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }


    private class DataObserver implements Observer<MovieDetailViewModel> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            getView().showError();
        }

        @Override
        public void onNext(MovieDetailViewModel movieDetailViewModel) {
            getView().onReceiveData(movieDetailViewModel);
        }
    }
}
