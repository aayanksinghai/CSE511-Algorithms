/*
 * You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.
 * 
 * Link: https://leetcode.com/problems/finding-mk-average/description/?envType=problem-list-v2&envId=queue
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

public class MKAverage {

    private final int m; //size of sliding window
    private final int k; //k: no of smallest/largest elements excluding from average
    private final Deque<Integer> window;
    private final TreeMap<Integer, Integer> valueCnt;
    private long windowSum;

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        this.window = new ArrayDeque<>();
        this.valueCnt = new TreeMap<>();
        this.windowSum = 0;
    }

    public void addElement(int num) {
        window.addLast(num);
        valueCnt.put(num, valueCnt.getOrDefault(num, 0) + 1);
        windowSum += num;

        if (window.size() > m) {
            int oldEle = window.removeFirst();
            
            
            windowSum -= oldEle;
            int count = valueCnt.get(oldEle);
            if (count == 1) {
                valueCnt.remove(oldEle); 
            } else {
                valueCnt.put(oldEle, count - 1);
            }
        }
    }

    public int calculateMKAverage() {
        if (window.size() < m) {
            return -1;
        }

        long sumAvg = windowSum;
        int elementsTrim = k;

        for (Map.Entry<Integer, Integer> entry : valueCnt.entrySet()) {
            int value = entry.getKey();
            int freq = entry.getValue();

            int trimCount = Math.min(elementsTrim, freq);
            sumAvg -= (long) value * trimCount;
            elementsTrim -= trimCount;

            if (elementsTrim == 0) {
                break;
            }
        }

        elementsTrim = k;
        for (Map.Entry<Integer, Integer> entry : valueCnt.descendingMap().entrySet()) {
            int value = entry.getKey();
            int freq = entry.getValue();

            int trimCount = Math.min(elementsTrim, freq);
            sumAvg -= (long) value * trimCount;
            elementsTrim -= trimCount;

            if (elementsTrim == 0) {
                break;
            }
        }
        
        return (int) (sumAvg / (m - 2 * k));
    }
}


/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */