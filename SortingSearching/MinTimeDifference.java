/*
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes 
 * difference between any two time-points in the list.
 * Link: https://leetcode.com/problems/minimum-time-difference/description/?envType=problem-list-v2&envId=sorting
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinTimeDifference {
    public int findminDiff(List<String> timePoints) {
        List<Integer> minsList = new ArrayList<>();
        for (String time : timePoints) {
            String[] parts = time.split(":"); //splitting hh and mm
            int hrs = Integer.parseInt(parts[0]);
            int mins = Integer.parseInt(parts[1]);
            minsList.add(hrs * 60 + mins);
        }

        Collections.sort(minsList);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < minsList.size(); i++) {
            int difference = minsList.get(i) - minsList.get(i - 1);
            minDiff = Math.min(minDiff, difference);
        }

        int firstTime = minsList.get(0);
        int lastTime = minsList.get(minsList.size() - 1);
        int wraparoundDifference = (firstTime + 1440) - lastTime; // 1440 minutes in a day

        return Math.min(minDiff, wraparoundDifference);
    }
    
}
