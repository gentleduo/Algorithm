package org.duo.common;

/**
 * 排序算法
 *
 * 数组：便于寻址，不便于增删数据(要维持数组在内存中的连续性)
 * 链表：便于增删数据，不便于寻址
 */
public class ArraySort {

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < N; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int end = N - 1; end >= 0; end--) {
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {
                    swap(arr, second - 1, second);
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int end = 1; end < N; end++) {
//            int newNumIndex = end;
//            while (newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]) {
//                swap(arr, newNumIndex - 1, newNumIndex);
//                newNumIndex--;
//            }
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {

        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void printArray(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = {1213, 12, 8, 21, 4, 1124, 79, 6, 4, 2, 4, 80};
        printArray(arr);
//        selectSort(arr);
//        bubbleSort(arr);
        insertSort(arr);
        printArray(arr);
    }
}