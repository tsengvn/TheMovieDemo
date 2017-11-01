package me.hienngo.themoviedemo.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.hienngo.themoviedemo.di.module.AppModule;
import me.hienngo.themoviedemo.di.module.NetworkModule;
import me.hienngo.themoviedemo.ui.detail.DetailActivity;
import me.hienngo.themoviedemo.ui.main.MainActivity;

/**
 * @author hienngo
 * @since 10/27/17
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
