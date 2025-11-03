import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class StreamMajority {

    private final PriorityQueue<Integer> maxHeap;
    private final PriorityQueue<Integer> minHeap;

    public StreamMajority() {
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
    }

    public void add(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        int totalSize = maxHeap.size() + minHeap.size();
        int maxHeapTargetSize = (3 * totalSize) / 4;

        if (maxHeap.size() > maxHeapTargetSize) {
            minHeap.add(maxHeap.poll());
        }
    }

    public int findElement() {
        if (minHeap.isEmpty()) {
            throw new IllegalStateException("Stream is empty.");
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StreamMajority stream = new StreamMajority();

        System.out.println("Enter query '1 num' or '2'. Type 'exit' to quit.");

        while (true) {
            String line = scanner.nextLine();
            if ("exit".equalsIgnoreCase(line)) {
                break;
            }

            String[] parts = line.split(" ");

            try {
                int type = Integer.parseInt(parts[0]);
                if (type == 1) {
                    if (parts.length < 2) {
                        System.out.println("Invalid format. Use '1 <number>'.");
                        continue;
                    }
                    int num = Integer.parseInt(parts[1]);
                    stream.add(num);
                    System.out.println("Added " + num);
                } else if (type == 2) {
                    try {
                        System.out.println("Element: " + stream.findElement());
                    } catch (IllegalStateException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("Invalid type.");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input.");
            }
        }
        scanner.close();
    }
}
