package com.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DependencyGraphTest {

    @Test
    void allPrerequisitesOf_chain() {
        DependencyGraph graph = new DependencyGraph();
        graph.addDependency(3L, 2L);   // 3 after 2
        graph.addDependency(2L, 1L);   // 2 after 1

        assertEquals(Set.of(1L, 2L), graph.allPrerequisitesOf(3L));
    }

    @Test
    void allPrerequisitesOf_diamond() {
        DependencyGraph graph = new DependencyGraph();
        graph.addTask(1L);
        graph.addDependency(2L, 1L);
        graph.addDependency(3L, 1L);
        graph.addDependency(4L, 2L);
        graph.addDependency(4L, 3L);

        assertEquals(Set.of(1L, 2L, 3L), graph.allPrerequisitesOf(4L));
    }

    @Test
    void allPrerequisitesOf_cycle_doesNotLoopForever() {
        DependencyGraph graph = new DependencyGraph();
        graph.addDependency(1L, 2L);
        graph.addDependency(2L, 3L);
        graph.addDependency(3L, 1L);

        assertEquals(Set.of(1L, 2L, 3L), graph.allPrerequisitesOf(1L));
    }

    @Test
    void addDependency_onItself_throws() {
        assertThrows(IllegalArgumentException.class, () -> new DependencyGraph().addDependency(1L, 1L));
    }

    @Test
    void canStart_whenAllPrerequisitesDone() {
        DependencyGraph graph = new DependencyGraph();
        graph.addDependency(3L, 1L);
        graph.addDependency(3L, 2L);

        assertEquals(Set.of(1L, 2L), graph.prerequisitesOf(3L));
        assertFalse(graph.canStart(3L, Set.of(1L)));
        assertTrue(graph.canStart(3L, Set.of(1L, 2L)));
        assertTrue(graph.canStart(1L, Set.of()));
    }
}
