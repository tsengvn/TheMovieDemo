package me.hienngo.themoviedemo.domain.repo;

import java.util.Map;

import me.hienngo.themoviedemo.model.Configuration;
import me.hienngo.themoviedemo.model.DataListResponse;
import me.hienngo.themoviedemo.model.Movie;
import me.hienngo.themoviedemo.model.MovieDetail;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author hienngo
 * @since 10/27/17
 */

public interface MovieRepo {
    @GET("movie/popular")
    Observable<DataListResponse<Movie>> getPopular(@Query("page") int page);

    @GET("configuration")
    Observable<Configuration> getConfiguration();

    @GET("movie/{id}")
    Observable<MovieDetail> getDetail(@Path("id") long id);

    @GET("discover/movie")
    Observable<DataListResponse<Movie>> getMovieList(@QueryMap Map<String, String> queries);
}
