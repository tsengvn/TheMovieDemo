package me.hienngo.themoviedemo.ui.main;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import me.hienngo.themoviedemo.model.viewmodel.MovieViewModel;

/**
 * @author hienngo
 * @since 10/29/17
 */

public interface MainView extends MvpView{
    void onReceiveData(List<MovieViewModel> movieOverviews);

    void clearList();

    void showError();
}
