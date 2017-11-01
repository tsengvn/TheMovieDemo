package me.hienngo.themoviedemo;

import android.app.Application;
import android.content.Context;

import me.hienngo.themoviedemo.di.component.AppComponent;
import me.hienngo.themoviedemo.di.component.DaggerAppComponent;
import me.hienngo.themoviedemo.di.module.AppModule;
import me.hienngo.themoviedemo.di.module.NetworkModule;

/**
 * @author hienngo
 * @since 10/30/17
 */

public class MovieApp extends Application {
    private AppComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().appModule(new AppModule(this))
                .networkModule(new NetworkModule()).build();
    }

    public static AppComponent component(Context context) {
        return ((MovieApp)context.getApplicationContext()).component;
    }


}
