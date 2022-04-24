package org.duo.common;

public class BitAddMinusMultiDiv {

    /**
     * 使用位运算完成加运算
     *
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {

        int sum = 0;
        while (b != 0) {
            sum = a ^ b; // 无进位相加信息 ==> sum
            b = (a & b) << 1; // 进位信息 ==> b ==> b'(进位信息)
            a = sum; // a ==> a' 无进位相加信息
        }
        return sum;
    }

    public static int negNum(int n) {

        return add(~n, 1);
    }

    /**
     * 使用位运算完成减运算
     * a - b 相当于 a + (-b)，-b等于b取反后加1
     *
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b) {

        return add(a, negNum(b));
    }

    public static int multi(int a, int b) {

        int res = 0;

        while (b != 0) {

            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n) {

        return n < 0;
    }

    public static int div(int a, int b) {

        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;

        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {

        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                // a / b
                // (a+1) / b ==c
                // a - (b*c) = d
                // d / b = e
                // c + e
                int ans = div(add(a, 1), b);
                return add(ans, div(minus(a, multi(ans, b)), b));
            }
        } else {
            return div(a, b);
        }
    }

    public static void main(String[] args) {

        System.out.println(multi(15, -3));
        System.out.println(divide(11, 3));
    }
}
