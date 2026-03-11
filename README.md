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
src/main/java/datastructures/stack/StackAsArray.java

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

---

## Planned Implementations

The following data structures will be added progressively:

- Dynamic Array
- Queue
- Linked List
- Binary Search Tree
- AVL Tree
- Red-Black Tree