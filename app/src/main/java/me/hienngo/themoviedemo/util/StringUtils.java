package me.hienngo.themoviedemo.util;

import com.annimon.stream.Stream;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.hienngo.themoviedemo.model.MovieDetail;

/**
 * @author hienngo
 * @since 10/31/17
 */

public class StringUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static String mergeNameValues(MovieDetail.Item[] items) {
        if (items != null && items.length > 0) {
            return Stream.of(items).map(item -> item.name)
                    .reduce((value1, value2) -> value1 +","+ value2).get();
        } else {
            return "";
        }
    }

    public static String parseDateQuery(long timeInMils) {
        return DATE_FORMAT.format(new Date(timeInMils));
    }
}
