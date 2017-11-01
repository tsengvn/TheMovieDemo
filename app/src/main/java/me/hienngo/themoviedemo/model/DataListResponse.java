package me.hienngo.themoviedemo.model;

import java.util.List;

/**
 * @author hienngo
 * @since 10/29/17
 */

public class DataListResponse<T> {
    public int page;
    public int totalResults;
    public int totalPages;
    public List<T> results;
}
