package datastructures.list;

public class DoublyLinkedListDemo {

    public static void main(String[] args) {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        // add elements
        list.addFirst(3);
        list.addFirst(1);
        list.addLast(5);
        list.add(1, 2);
        list.add(3, 4);

        System.out.println("List after insertions:");
        print(list);

        // access
        System.out.println("Element at index 2: " + list.get(2));

        // contains
        System.out.println("Contains 4: " + list.contains(4));

        // remove
        list.removeFirst();
        list.removeLast();

        System.out.println("List after removing first and last:");
        print(list);

        // reverse
        list.reverse();

        System.out.println("Reversed list:");
        print(list);

        // convert to array
        Object[] array = list.toArray();
        System.out.println("Array length: " + array.length);
    }

    private static <T> void print(DoublyLinkedList<T> list) {
        for (T element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
