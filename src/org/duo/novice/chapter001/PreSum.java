package org.duo.novice.chapter001;

public class PreSum {


    public static int[] RangeSumArray(int[] array) {

        int N = array.length;
        int[] preSum = new int[N];
        preSum[0] = array[0];
        for (int i = 1; i < N; i++) {
            preSum[i] = preSum[i - 1] + array[i];
        }
        return preSum;
    }

    public static int RangeSum(int[] array, int L, int R) {
        return L == 0 ? array[R] : array[R] - array[L - 1];
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] preSumArray = PreSum.RangeSumArray(array);
        System.out.println(RangeSum(preSumArray, 2, 3));
    }
}
