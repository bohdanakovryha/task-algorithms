# Task Algorithms

Standalone Java practice project: data structures and algorithms used for sorting, searching and
ordering tasks with dependencies. Split out of the `taskflow` project because these classes were
implemented and tested on their own, but never actually called from the web application.

## How to run

```bash
mvn clean verify
```

JUnit 5 tests.

## What is here, and why

### `Sorting.mergeSort` and `Sorting.bubbleSort`

Both take a `List` and a `Comparator` and return a new sorted list, input untouched. Kept side by
side so they can be compared directly.

`bubbleSort` swaps neighbouring elements that are out of order, pass after pass, and stops early once
a pass makes no swaps. Simple to trace by hand, but `O(n^2)`, so it is the baseline here, not something
used elsewhere in the project.

`mergeSort` splits the list in half, sorts each half recursively, merges the results. `O(n log n)`,
`O(n)` extra memory for the merge. Picked over an in-place quicksort-style sort because it is
**stable** — equal elements keep their original order (see `mergeSort_isStable` in `SortingTest`).

### `Searching`

- `linearSearch` — checks items one by one. `O(n)`, works on any list, sorted or not.
- `binarySearch` — list must already be sorted by the given `Comparator`. `O(log n)` instead of
  `O(n)` for a linear scan.

### `DependencyGraph`

"Task B can only start after task A is done", stored as a `Map<Long, Set<Long>>` adjacency list
(`LinkedHashMap`/`LinkedHashSet`, so insertion order is kept).

- `prerequisitesOf`/`canStart` — direct prerequisites of a task, and whether they are all done.
- `allPrerequisitesOf` — same idea, but direct *and* indirect: start from the direct prerequisites,
  put them in a queue (`ArrayDeque`), and keep pulling tasks from it and adding their own
  prerequisites, skipping anything already seen (a `Set` of visited ids). That visited check also
  means a cycle just gets walked once instead of looping forever.
