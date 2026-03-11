package datastructures.stack;

public class StackDemo {

    public static void main(String[] args) {

        StackAsArray<Integer> stack = new StackAsArray<>();

        System.out.println("Pushing elements...");
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack size: " + stack.size());
        System.out.println("Top element: " + stack.peek());

        System.out.println("\nPopping elements...");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        System.out.println("Stack empty: " + stack.isEmpty());
    }
}
