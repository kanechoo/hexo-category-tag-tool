package site.konchoo.tool.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ListUtils {
    private ListUtils() {
    }

    @SafeVarargs
    public static <T> List<T> unionLists(List<T>... lists) {
        return Arrays.stream(lists).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
