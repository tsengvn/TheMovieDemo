package me.hienngo.themoviedemo.model.viewmodel;

import me.hienngo.themoviedemo.model.Configuration;
import me.hienngo.themoviedemo.model.Movie;

/**
 * @author hienngo
 * @since 10/29/17
 */

public class MovieViewModel {
    private final long id;
    private final String name;
    private final String posterUrl;
    private final float vote;
    private final String releaseDate;

    public MovieViewModel(Movie movie, Configuration configuration) {
        this.id = movie.id;
        this.name = movie.title;
        this.vote = movie.voteAverage;
        this.releaseDate = movie.releaseDate;
        this.posterUrl = configuration.images.baseUrl
                + configuration.images.posterSizes[configuration.images.posterSizes.length-2]//should have better logic
                + movie.posterPath;
    }

    public String getName() {
        return name;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public long getId() {
        return id;
    }

    public float getVote() {
        return vote;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
