import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        LinkedList<String> stack = new LinkedList<>();
        stack.add("JFK");
        LinkedList<String> itinerary = new LinkedList<>();

        while (!stack.isEmpty()) {
            while (graph.getOrDefault(stack.peekLast(), new PriorityQueue<>()).size() > 0) {
                stack.add(graph.get(stack.peekLast()).poll());
            }
            itinerary.addFirst(stack.pollLast());
        }

        return itinerary;
    }
}