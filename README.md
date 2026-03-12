# Java Data Structures

This repository contains implementations of fundamental data structures written in Java.

The goal of this project is to understand how core data structures work internally by implementing them from scratch without relying on Java's built-in collections.

## Implemented Data Structures

### Stack
Array-based stack implementation with dynamic resizing.

Operations:
- push
- pop
- peek
- size
- isEmpty

Time Complexity:

| Operation | Complexity     |
|-----------|----------------|
| push      | O(1) amortized |
| pop       | O(1) amortized |
| peek      | O(1)           |
| size      | O(1)           |

Location:
`src/main/java/datastructures/stack/StackAsArray.java`

---

### Dynamic Array
Generic dynamic array implementation with automatic resizing, fail-fast iteration, and common array operations.

Operations:
- add (append)
- add (at index)
- get
- set
- remove
- contains
- indexOf
- size
- isEmpty
- clear

Time Complexity:

| Operation     | Complexity     |
|---------------|----------------|
| add (end)     | O(1) amortized |
| add (index)   | O(n)           |
| get           | O(1)           |
| set           | O(1)           |
| remove        | O(n)           |
| contains      | O(n)           |
| indexOf       | O(n)           |
| size          | O(1)           |

Location:
`src/main/java/datastructures/dynamicarray/DynamicArray.java`

---

## Example Usage
```java
StackAsArray<Integer> stack = new StackAsArray<>();

stack.push(10);
stack.push(20);

System.out.println(stack.pop());  // 20
System.out.println(stack.peek()); // 10
System.out.println(stack.size()); // 1
```

```java
DynamicArray<String> arr = new DynamicArray<>();

arr.add("Alice");
arr.add("Bob");
arr.add(1, "Charlie");

System.out.println(arr.get(0));      // Alice
System.out.println(arr.get(1));      // Charlie
System.out.println(arr.contains("Bob")); // true
System.out.println(arr.size());      // 3
```

---

## Planned Implementations

The following data structures will be added progressively:

- Queue
- Linked List
- Binary Search Tree
- AVL Tree
- Red-Black Tree