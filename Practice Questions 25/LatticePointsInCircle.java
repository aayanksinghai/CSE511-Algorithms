public class LatticePointsInCircle {

    /**
     * Computes the number of lattice (integer) points inside a circle of a given
     * radius
     * centered at (0, 0).
     * The algorithm runs in O(n) or O(radius) time.
     *
     * @param n The radius of the circle.
     * @return The total number of integer points inside or on the circle.
     */
    public static long countLatticePoints(int n) {
        // Radius must be non-negative
        if (n < 0) {
            return 0;
        }

        // Let's count the points in the first quadrant (x > 0, y > 0)
        long quadrantPoints = 0;
        for (int x = 1; x <= n; x++) {
            // For a given x, find the maximum integer y such that x^2 + y^2 <= n^2
            // y <= sqrt(n^2 - x^2)
            double y_double = Math.sqrt((long) n * n - (long) x * x);
            quadrantPoints += (long) Math.floor(y_double);
        }

        // Total points = 1 (for origin) + 4 * (points on semi-axes) + 4 * (points in
        // quadrants)
        // A simpler way: The total is 4 * quadrantPoints (for all 4 quadrants) +
        // 4 * n (for the points on the axes, excluding origin) + 1 (for the origin)
        long totalPoints = 4 * quadrantPoints + 4L * n + 1;

        return totalPoints;
    }

    public static void main(String[] args) {
        int radius = 3;
        // For r = 3:
        // Points on axes: (0,0), (±1,0), (±2,0), (±3,0), (0,±1), (0,±2), (0,±3) -> 1 +
        // 4*3 = 13 points
        // Points in quadrants:
        // x=1: y <= sqrt(9-1)=sqrt(8)=2.82. y can be 1, 2. (2 points)
        // x=2: y <= sqrt(9-4)=sqrt(5)=2.23. y can be 1, 2. (2 points)
        // x=3: y <= sqrt(9-9)=0. y can be none. (0 points)
        // Total quadrant points = 2 + 2 = 4.
        // Total = 13 + 4 * 4 = 13 + 16 = 29.
        System.out.println("Radius: " + radius);
        System.out.println("Number of lattice points: " + countLatticePoints(radius)); // Expected: 29

        System.out.println("---");

        int radius2 = 5;
        System.out.println("Radius: " + radius2);
        System.out.println("Number of lattice points: " + countLatticePoints(radius2)); // Expected: 81
    }
}