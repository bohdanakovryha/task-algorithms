package com.algorithms;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public final class Searching {

    private Searching() {
    }

    // Checks items one by one, returns the first match.
    public static <T> Optional<T> linearSearch(List<T> items, Predicate<T> condition) {
        for (T item : items) {
            if (condition.test(item)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    // List must already be sorted by comparator. -1 if target is not found.
    public static <T> int binarySearch(List<T> sorted, T target, Comparator<T> comparator) {
        int low = 0;
        int high = sorted.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int comparison = comparator.compare(sorted.get(middle), target);
            if (comparison == 0) {
                return middle;
            } else if (comparison < 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }
}
