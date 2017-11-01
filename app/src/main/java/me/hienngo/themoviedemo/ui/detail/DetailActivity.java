package me.hienngo.themoviedemo.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import me.hienngo.themoviedemo.MovieApp;
import me.hienngo.themoviedemo.R;
import me.hienngo.themoviedemo.domain.interactor.GetDetail;
import me.hienngo.themoviedemo.model.viewmodel.MovieDetailViewModel;

/**
 * @author hienngo
 * @since 10/31/17
 */

public class DetailActivity extends MvpActivity<DetailView, DetailPresenter> implements DetailView {

    @Inject
    GetDetail getDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MovieApp.component(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public DetailPresenter createPresenter() {
        return new DetailPresenter(getDetail);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().loadData(getIntent());
    }

    @Override
    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReceiveData(MovieDetailViewModel model) {
        ImageView imageView = findViewById(R.id.ivImage);
        Picasso.with(this).load(model.getImageUrl()).fit().centerInside().into(imageView);

        TextView textView = findViewById(R.id.tvTitle);
        textView.setText(model.getTitle());

        textView = findViewById(R.id.tvOverview);
        textView.setText(model.getOverview());

        textView = findViewById(R.id.tvGenres);
        textView.setText(model.getGenres());

        textView = findViewById(R.id.tvProductions);
        textView.setText(model.getProductions());

        textView = findViewById(R.id.tvHomePage);
        textView.setText(model.getHomepage());

        textView = findViewById(R.id.tvReleaseDate);
        textView.setText(model.getReleaseDate());
    }
}
