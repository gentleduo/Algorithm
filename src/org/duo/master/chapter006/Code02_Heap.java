package org.duo.master.chapter006;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Heap {

    /**
     * 堆结构就是用数组实现的完全二叉树结构
     * 自定义大根堆
     */
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        // 时间复杂度：O(logN)
        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            // value  heapSize
            heapInsert(heap, heapSize++);
        }

        // 返回最大值，并且在大根堆中，把最大值删掉，剩下的数，依然保持大根堆组织
        // 时间复杂度：O(logN)
        public int pop() {
            // 返回值为根节点
            int ans = heap[0];
            // 将根节点跟堆中最后一个节点交互，并且将heapSize减一
            // 原来的根节点仍然留在数组中，只是将它放在堆的末尾，不会参与接下来的重新调整堆结构的运算，并且在之后有新的数据插入堆中的时候会被覆盖
            swap(heap, 0, --heapSize);
            // 通过heapify函数调整堆结构，使其仍然保持大根堆结构
            heapify(heap, 0, heapSize);
            return ans;
        }

        // 新加进来的数，现在停在了index位置，请依次往上移动，(index - 1)/2为父节点在数组中的位置
        // 移动到0位置，或者干不掉自己的父亲了，停！
        private void heapInsert(int[] arr, int index) {
            // 循环终止的条件1：arr[index]<=arr[(index - 1)/2])
            // 循环终止的条件2：当新插入的节点大于所以父节点，来到了index==0，那么(0-1)/2的结果取整后为0，而arr[0]==arr[0]，所以循环同样终止
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从index位置，往下看，不断的下沉
        // 停：较大的孩子都不再比index位置的数大；已经没孩子了
        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) { // 如果有左孩子，有没有右孩子，可能有可能没有！
                // 把较大孩子的下标，给largest
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                // index和较大孩子，要互换
                swap(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }


    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {

        // JDK的优先级队列就是堆结构，默认：小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        // 通过自定义比较器，可以构建大根堆
        PriorityQueue<Integer> heap1 = new PriorityQueue<>(new MyComparator());
		heap1.add(5);
		heap1.add(5);
		heap1.add(5);
		heap1.add(3);
        System.out.println(heap1.peek());
        // 堆中可以追加重复数据，而有序表则不行。
		heap1.add(7);
		heap1.add(0);
		heap1.add(7);
		heap1.add(0);
        System.out.println(heap1.peek());
        while (!heap1.isEmpty()) {
            System.out.println(heap1.poll());
        }
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}