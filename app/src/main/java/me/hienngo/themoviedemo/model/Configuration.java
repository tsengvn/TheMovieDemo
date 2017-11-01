package me.hienngo.themoviedemo.model;

/**
 * @author hienngo
 * @since 10/30/17
 */

public class Configuration {
    public ImageConfig images;

    public static class ImageConfig {
        public String baseUrl;
        public String secureBaseUrl;
        public String[] posterSizes;
        public String[] backdropSizes;
    }
}
