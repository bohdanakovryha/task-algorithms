package com.algorithms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DependencyGraph {

    private final Map<Long, Set<Long>> dependents = new LinkedHashMap<>();

    public void addTask(long taskId) {
        dependents.putIfAbsent(taskId, new LinkedHashSet<>());
    }

    // taskId can start only after dependsOnId is done.
    public void addDependency(long taskId, long dependsOnId) {
        if (taskId == dependsOnId) {
            throw new IllegalArgumentException("A task cannot depend on itself");
        }
        addTask(taskId);
        addTask(dependsOnId);
        dependents.get(dependsOnId).add(taskId);
    }

    // Tasks that must be done before taskId can start.
    public Set<Long> prerequisitesOf(long taskId) {
        Set<Long> result = new LinkedHashSet<>();
        for (Map.Entry<Long, Set<Long>> entry : dependents.entrySet()) {
            if (entry.getValue().contains(taskId)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    // A task can start when all of its prerequisites are done.
    public boolean canStart(long taskId, Set<Long> doneTaskIds) {
        return doneTaskIds.containsAll(prerequisitesOf(taskId));
    }

    // All prerequisites of taskId, direct and indirect, found by walking the graph breadth-first.
    // The visited set also protects against an infinite loop if the tasks end up in a cycle.
    public Set<Long> allPrerequisitesOf(long taskId) {
        Set<Long> visited = new LinkedHashSet<>();
        Deque<Long> toVisit = new ArrayDeque<>(prerequisitesOf(taskId));
        visited.addAll(toVisit);

        while (!toVisit.isEmpty()) {
            long current = toVisit.poll();
            for (long next : prerequisitesOf(current)) {
                if (visited.add(next)) {
                    toVisit.add(next);
                }
            }
        }
        return visited;
    }
}
