package org.duo.novice.chapter001;

public class Sort {

    public static void main(String[] args) {


//        for (int i = 1; i < 2; i++) {
//            System.out.println(i);
//        }

        int[] list = {1, 24, 21, 4, 6, 1, 14, 67, 21, 8, 4, 9, 5, 1, 3, 6, 9};
        //Sort.bubbleSort(list);
        Sort.insertSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
    }

    public static void bubbleSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

//        int end = arr.length - 1;
        for (int end = arr.length - 1; end >= 1; end--) {
            for (int i = 1; i <= end; i++) {
                if (arr[i - 1] > arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
            }
        }
    }

    public static void insertSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;

        for (int i = 1; i < N; i++) {

            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
