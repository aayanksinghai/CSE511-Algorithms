/*
 * Design your implementation of the circular double-ended queue (deque).

 * Link: https://leetcode.com/problems/design-circular-deque/description/?envType=problem-list-v2&envId=queue
 * Author: AAYANK SINGHAI (MT2025001)
 */


public class MyCircularDeque {

    private final int[] data; 
    private final int capacity;
    private int currentSize;
    private int front;
    private int rear;

    public MyCircularDeque(int k) {
        this.data = new int[k];
        this.capacity = k;
        this.currentSize = 0;
        
        this.front = -1; //-1 means currently empty
        this.rear = -1;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity;
        }

        data[front] = value;
        currentSize++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        data[rear] = value;
        currentSize++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        if (currentSize == 1) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        
        currentSize--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        if (currentSize == 1) {
            front = -1;
            rear = -1;
        } else {
            rear = (rear - 1 + capacity) % capacity;
        }
        
        currentSize--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : data[front];
    }

    public int getRear() {
        return isEmpty() ? -1 : data[rear];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == capacity;
    } 
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
