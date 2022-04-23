package org.duo.common;

public class BSAwesome {

    public static int oneMinIndex(int[] arr) {

        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[N - 1] < arr[N - 2]) {
            return N - 1;
        }
        int L = 0;
        int R = N - 1;
        int ans = -1;
        while (L < R - 1) {
            int mid = (L + R) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            }
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
                continue;
            }
            if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
                continue;
            }
        }
        return arr[L] < arr[R] ? L : R;
    }

    // 生成随机数组，且相邻数不相等
    public static int[] generateRandomArray(int maxSize, int maxValue) {

        int len = (int) (maxSize * Math.random());
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (maxValue * Math.random());
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (maxValue * Math.random());
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    public static boolean check(int[] arr, int minIndex) {

        if (arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 200;
        int maxValue = 21000;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int ans = oneMinIndex(arr);
            if (!check(arr, ans)) {
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
