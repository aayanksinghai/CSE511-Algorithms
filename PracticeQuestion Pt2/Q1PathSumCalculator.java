import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node in the binary tree.
 */
class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}

/**
 * Solves the problem of computing the root-to-node path sum for every node in a
 * binary tree.
 */
public class Q1PathSumCalculator {

    /**
     * Computes the sum of values from the root to each node in the tree.
     * This is the main public method that initiates the calculation.
     *
     * @param root The root of the binary tree.
     * @return A map where keys are the tree nodes and values are their respective
     *         path sums from the root.
     */
    public Map<Node, Integer> computeAllPathSums(Node root) {
        Map<Node, Integer> pathSums = new HashMap<>();
        // Start the recursion from the root. The path sum to the "parent" of the root
        // is considered 0.
        computePathSumsRecursive(root, 0, pathSums);
        return pathSums;
    }

    /**
     * A private helper method that performs a preorder traversal (DFS) to calculate
     * path sums.
     * It carries the sum of the path to the parent node as it descends the tree.
     *
     * @param node          The current node being visited.
     * @param parentPathSum The sum of values on the path from the root to the
     *                      parent of the current node.
     * @param pathSums      The map used to store the computed path sum for each
     *                      node.
     */
    private void computePathSumsRecursive(Node node, int parentPathSum, Map<Node, Integer> pathSums) {
        // Base case: If the node is null, we've reached the end of a branch.
        if (node == null) {
            return;
        }

        // The path sum for the current node is its own value plus the path sum to its
        // parent.
        int currentPathSum = parentPathSum + node.value;
        pathSums.put(node, currentPathSum);

        // Recursively call for the left and right children.
        // The current node's path sum becomes the `parentPathSum` for its children.
        computePathSumsRecursive(node.left, currentPathSum, pathSums);
        computePathSumsRecursive(node.right, currentPathSum, pathSums);
    }

    /**
     * A utility method to print the results in a structured (in-order) way for easy
     * verification.
     *
     * @param node     The current node in the traversal.
     * @param pathSums The map containing the computed path sums from the main
     *                 algorithm.
     */
    public void printResultsInOrder(Node node, Map<Node, Integer> pathSums) {
        if (node == null) {
            return;
        }
        printResultsInOrder(node.left, pathSums);
        System.out.println("Path sum for node with value " + node.value + " is: " + pathSums.get(node));
        printResultsInOrder(node.right, pathSums);
    }

    /**
     * The main method to demonstrate the functionality with a sample tree.
     */
    public static void main(String[] args) {
        // Let's construct a sample binary tree:
        // 10
        // / \
        // 5 -3
        // / \ \
        // 3 2 11
        // / / \
        // 3 -2 1
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(-3);
        root.left.left = new Node(3);
        root.left.right = new Node(2);
        root.right.right = new Node(11);
        root.left.left.left = new Node(3);
        root.left.right.left = new Node(-2);
        root.left.right.right = new Node(1);

        Q1PathSumCalculator calculator = new Q1PathSumCalculator();
        Map<Node, Integer> results = calculator.computeAllPathSums(root);

        System.out.println("--- Path Sums for Each Node (Printed using In-order Traversal) ---");
        calculator.printResultsInOrder(root, results);
    }
}