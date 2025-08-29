/*
 * A positive integer is magical if it is divisible by either a or b.

Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return it modulo 109 + 7
 * 
 * Link: https://leetcode.com/problems/nth-magical-number/description/
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

public class NthMagicalNumber {
    private static final int MOD = 1_000_000_007;

    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private long countMagicalNumbers(long x, long a, long b, long lcm) {
        return (x / a) + (x / b) - (x / lcm); // Principle of Inclusion-Exclusion.
    }

    public int nthMagicalNumber(int n, int a, int b) {
        long longA = a;
        long longB = b;
        long lcm = (longA * longB) / gcd(longA, longB);
        long low = Math.min(longA, longB);
        long high = (long) n * Math.min(longA, longB);
        long result = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countMagicalNumbers(mid, longA, longB, lcm) < n) {
                low = mid + 1;
            } else {
                result = mid;
                high = mid - 1;
            }
        }
        return (int) (result % MOD);
    }
}
