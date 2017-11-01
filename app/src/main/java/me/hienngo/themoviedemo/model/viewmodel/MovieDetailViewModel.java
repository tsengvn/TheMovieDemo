package me.hienngo.themoviedemo.model.viewmodel;

import me.hienngo.themoviedemo.model.Configuration;
import me.hienngo.themoviedemo.model.MovieDetail;
import me.hienngo.themoviedemo.util.StringUtils;

/**
 * @author hienngo
 * @since 10/31/17
 */

public class MovieDetailViewModel {
    private final String title;
    private final String overview;
    private final String genres;
    private final String productions;
    private final String homepage;
    private final String releaseDate;
    private final String imageUrl;

    public MovieDetailViewModel(MovieDetail movieDetail, Configuration configuration) {
        title = movieDetail.title;
        overview = movieDetail.overview;
        genres = StringUtils.mergeNameValues(movieDetail.genres);
        productions = StringUtils.mergeNameValues(movieDetail.productionCompanies);
        homepage = movieDetail.homepage;
        releaseDate = movieDetail.releaseDate;
        imageUrl = configuration.images.baseUrl
                + configuration.images.posterSizes[configuration.images.backdropSizes.length-1]//should have better logic
                + movieDetail.backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getGenres() {
        return genres;
    }

    public String getProductions() {
        return productions;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
