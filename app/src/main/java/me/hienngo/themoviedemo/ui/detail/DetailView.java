package me.hienngo.themoviedemo.ui.detail;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import me.hienngo.themoviedemo.model.viewmodel.MovieDetailViewModel;

/**
 * @author hienngo
 * @since 10/31/17
 */

public interface DetailView extends MvpView {
    void showError();

    void onReceiveData(MovieDetailViewModel movieDetailViewModel);

    void setTitle(String title);
}
