package org.duo.master.chapter001;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度：O(N^2)
 * <p>
 * 跟冒泡排序的区别：冒泡不受数据状况的影响，时间复杂度固定，例如：对于[1,2,3]和[3,2,1]进行排序的时间复杂度是一样的，都是O(N^2)
 * 而插入排序的时间复杂度会根据数据状况的不同而不同，例如对[1,2,3]进行排序的时间复杂度为O(1)而对[3,2,1]进行排序的时间复杂度为O(N^2)
 * <p>
 * 但是当某个算法流程的复杂程度会根据数据状况的不同而不同时，那么必须要按照最差情况来估计时间复杂度。
 * 很明显，在最差情况下，如果arr长度为N，插入排序的每一轮常数操作的数量，还是如等差数列一般，
 * 所以总的常数操作数量 = a*(N^2) + b*N + c（a、b、c都是常数，也可以理解为a，b，c为固定时间的常数操作的耗时）
 * 所以插入排序的时间复杂度为O(N^2)
 */
public class Code03_InsertionSort {

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 不只1个数
        // 依次实现两个下标之间的数据的有序
        // 0 ~ 0
        // 0 ~ 1
        // 0 ~ 2
        // 0 ~ i
        // 0 ~ N-1
        for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // i和j是一个位置的话，会出错
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100; // 随机数组的长度0～100
        int maxValue = 100;// 值：-100～100
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            insertionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                // 打印arr1
                // 打印arr2
                succeed = false;
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println();
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        insertionSort(arr);
        printArray(arr);
    }

}
