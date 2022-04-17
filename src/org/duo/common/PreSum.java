package org.duo.common;

public class PreSum {

    /**
     * 前缀和
     */
    public static class RangeSum {
        private int[] preSum;

        public RangeSum(int[] array) {
            int N = array.length;
            preSum = new int[N];
            preSum[0] = array[0];
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i - 1] + array[i];
            }
        }

        public int rangeSum(int L, int R) {
            return L == 0 ? preSum[R] : preSum[R] - preSum[L - 1];
        }
    }

    public static void main(String[] args) {
        RangeSum rangeSum = new RangeSum(new int[]{1, 23, 41, 8, 2, 3, 5, 3, 10});
        System.out.println(rangeSum.rangeSum(0, 5));
        System.out.println(rangeSum.rangeSum(7, 10));
    }
}
