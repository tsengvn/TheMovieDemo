package me.hienngo.themoviedemo.model;

/**
 * @author hienngo
 * @since 10/31/17
 */

public class MovieDetail {
    public boolean adult;
    public String backdropPath;
    public long budget;
    public String homepage;
    public long id;
    public String overview;
    public String releaseDate;
    public String status;
    public String title;
    public String tagline;
    public Item[] genres;
    public Item[] productionCompanies;

    public static class Item {
        public long id;
        public String name;
    }

}
