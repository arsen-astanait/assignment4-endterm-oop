package com.example.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public final class SearchUtil {

    private SearchUtil() {}

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> res = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) res.add(item);
        }
        return res;
    }

    public static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        List<T> copy = new ArrayList<>(list);
        copy.sort(comparator);
        return copy;
    }
}
