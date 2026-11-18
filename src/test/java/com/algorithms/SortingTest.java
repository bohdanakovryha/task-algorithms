package com.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortingTest {

    // Two fields so we can sort by priority and check what happens to items with the same priority.
    private static final class Item {
        final String name;
        final int priority;

        Item(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
    }

    @Test
    void mergeSort_sortsNumbers() {
        List<Integer> numbers = List.of(5, 3, 8, 1, 9, 2);

        assertEquals(List.of(1, 2, 3, 5, 8, 9), Sorting.mergeSort(numbers, Comparator.naturalOrder()));
    }

    @Test
    void mergeSort_doesNotChangeInput() {
        List<Integer> numbers = List.of(3, 1, 2);

        assertEquals(List.of(1, 2, 3), Sorting.mergeSort(numbers, Comparator.naturalOrder()));
        assertEquals(List.of(3, 1, 2), numbers);
    }

    @Test
    void mergeSort_emptyAndSingleElement() {
        assertTrue(Sorting.mergeSort(List.<Integer>of(), Comparator.naturalOrder()).isEmpty());
        assertEquals(List.of(7), Sorting.mergeSort(List.of(7), Comparator.naturalOrder()));
    }

    @Test
    void mergeSort_isStable() {
        Item a = new Item("A", 2);
        Item b = new Item("B", 1);
        Item c = new Item("C", 2);

        List<Item> sorted = Sorting.mergeSort(List.of(a, b, c),
                Comparator.comparingInt((Item item) -> item.priority).reversed());

        // a and c both have priority 2, a was first in the input, so a must stay before c
        assertEquals(List.of(a, c, b), sorted);
    }

    @Test
    void bubbleSort_sortsNumbers() {
        List<Integer> numbers = List.of(5, 3, 8, 1, 9, 2);

        assertEquals(List.of(1, 2, 3, 5, 8, 9), Sorting.bubbleSort(numbers, Comparator.naturalOrder()));
    }

    @Test
    void bubbleSort_doesNotChangeInput() {
        List<Integer> numbers = List.of(3, 1, 2);

        assertEquals(List.of(1, 2, 3), Sorting.bubbleSort(numbers, Comparator.naturalOrder()));
        assertEquals(List.of(3, 1, 2), numbers);
    }

    @Test
    void bubbleSort_emptyAndSingleElement() {
        assertTrue(Sorting.bubbleSort(List.<Integer>of(), Comparator.naturalOrder()).isEmpty());
        assertEquals(List.of(7), Sorting.bubbleSort(List.of(7), Comparator.naturalOrder()));
    }

    @Test
    void bubbleSort_isStable() {
        Item a = new Item("A", 2);
        Item b = new Item("B", 1);
        Item c = new Item("C", 2);

        List<Item> sorted = Sorting.bubbleSort(List.of(a, b, c),
                Comparator.comparingInt((Item item) -> item.priority).reversed());

        assertEquals(List.of(a, c, b), sorted);
    }
}
