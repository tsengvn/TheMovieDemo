package me.hienngo.themoviedemo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import me.hienngo.themoviedemo.MovieApp;
import me.hienngo.themoviedemo.R;
import me.hienngo.themoviedemo.domain.interactor.GetMovieList;
import me.hienngo.themoviedemo.model.viewmodel.MovieViewModel;
import me.hienngo.themoviedemo.ui.detail.DetailActivity;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, MovieAdapter.LoadMoreListener, MovieAdapter.OnItemClickListener, DatePickerDialog.OnDateSetListener {

    @Inject
    GetMovieList getMovieList;

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MovieApp.component(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        getPresenter().loadData();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(getMovieList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_filter_date) {
            openDateSelector();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onReceiveData(List<MovieViewModel> viewModels) {
        movieAdapter.setDataList(viewModels);
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(this);
        movieAdapter.setLoadMoreListener(this);
        movieAdapter.setClickListener(this);
        recyclerView.setAdapter(movieAdapter);
    }

    private void openDateSelector() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onLoadMore() {
        getPresenter().onLoadMore();
    }

    @Override
    public void onItemClicked(MovieViewModel model) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", model.getId());
        intent.putExtra("title", model.getName());
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        long startDate = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, yearEnd);
        calendar.set(Calendar.MONTH, monthOfYearEnd);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonthEnd);

        long endDate = calendar.getTimeInMillis();
        getPresenter().loadData(startDate, endDate);
    }

    @Override
    public void clearList() {
        movieAdapter.clear();
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show();
    }
}
