package com.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Sorting {

    private Sorting() {
    }

    // Splits the list in half, sorts each half, merges them back. O(n log n), stable.
    public static <T> List<T> mergeSort(List<T> items, Comparator<T> comparator) {
        if (items.size() <= 1) {
            return new ArrayList<>(items);
        }
        int middle = items.size() / 2;
        List<T> left = mergeSort(items.subList(0, middle), comparator);
        List<T> right = mergeSort(items.subList(middle, items.size()), comparator);
        return merge(left, right, comparator);
    }

    private static <T> List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
        List<T> result = new ArrayList<>(left.size() + right.size());
        int i = 0;
        int j = 0;
        while (i < left.size() && j < right.size()) {
            // on a tie take left first, so equal elements keep their original order
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size()) {
            result.add(left.get(i++));
        }
        while (j < right.size()) {
            result.add(right.get(j++));
        }
        return result;
    }

    // Swaps neighbours that are out of order, pass after pass, until nothing moves. O(n^2).
    public static <T> List<T> bubbleSort(List<T> items, Comparator<T> comparator) {
        List<T> result = new ArrayList<>(items);
        for (int unsortedEnd = result.size() - 1; unsortedEnd > 0; unsortedEnd--) {
            boolean swappedThisPass = false;
            for (int i = 0; i < unsortedEnd; i++) {
                if (comparator.compare(result.get(i), result.get(i + 1)) > 0) {
                    T temp = result.get(i);
                    result.set(i, result.get(i + 1));
                    result.set(i + 1, temp);
                    swappedThisPass = true;
                }
            }
            if (!swappedThisPass) {
                break;
            }
        }
        return result;
    }
}
