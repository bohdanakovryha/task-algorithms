package com.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchingTest {

    private static final List<Integer> SORTED = List.of(1, 3, 5, 7, 9);

    @Test
    void linearSearch_findsFirstMatch() {
        assertEquals(7, Searching.linearSearch(List.of(5, 7, 8), n -> n > 6).orElseThrow());
        assertTrue(Searching.linearSearch(List.of(1, 2), n -> n > 6).isEmpty());
    }

    @Test
    void binarySearch_findsExistingValue() {
        assertEquals(3, Searching.binarySearch(SORTED, 7, Comparator.naturalOrder()));
    }

    @Test
    void binarySearch_missingValue() {
        assertEquals(-1, Searching.binarySearch(SORTED, 4, Comparator.naturalOrder()));
        assertEquals(-1, Searching.binarySearch(List.<Integer>of(), 4, Comparator.naturalOrder()));
    }
}
