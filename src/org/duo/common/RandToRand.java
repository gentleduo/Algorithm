package org.duo.common;

public class RandToRand {

    public static void main(String[] args) {

        // 等概率返回[0,1)中的一个double类型的值
        double probDouble = Math.random();
        // System.out.println(probDouble);

        // 等概率返回[0,10)中的一个double类型的值
        probDouble = Math.random() * 10;
        //System.out.println(probDouble);

        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() * 10 < 5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        int K = 10;
        // 等概率返回[0,K-1]中的一个int类型的值
        int probInt = (int) (Math.random() * K);
        //System.out.println(probInt);
        int[] counts = new int[K];
        for (int i = 0; i < testTimes; i++) {
            int ans = (int) (Math.random() * K);
            counts[ans]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "这个数出现了:" + counts[i] + "次");
        }

        count = 0;
        double x = 0.7;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower2() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));

        counts = new int[7];
        for (int i = 0; i < testTimes; i++) {
            int num = CustomEqualRandom06();
            counts[num]++;
        }
        for (int i = 0; i < 7; i++) {
            System.out.println(i + "这个数出现了" + counts[i] + "次");
        }

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (CustomFixdEqualRandom() == 1) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
    }

    /**
     * @return 返回[0, 1)之间的一个double类型的数
     * 任意的x，x属于[0,1)，[0,x)范围上的数出现概率由原来的x调整成x平方
     */
    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }

    /**
     * @return 等概率返回1到5之间的整数
     */
    public static int baseEqualRandom() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * @return 等概率返回0、1
     */
    public static int CustomEqualRandom01() {
        int ans = 0;
        do {
            ans = baseEqualRandom();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    /**
     * @return 得到000 ~ 111做到等概率，等概率返回0~7中的一个
     */
    public static int CustomEqualRandom07() {
        return (CustomEqualRandom01() << 2) + (CustomEqualRandom01() << 1) + CustomEqualRandom01();
    }

    public static int CustomEqualRandom06() {
        int ans = 0;
        do {
            ans = CustomEqualRandom07();
        } while (ans == 7);
        return ans;
    }

    /**
     * @return 固定概率返回0、1
     */
    public static int baseFixedRandom() {
        return Math.random() < 0.7 ? 0 : 1;
    }

    /**
     * @return
     */
    public static int CustomFixdEqualRandom() {

        int ans = 0;
        do {
            ans = baseFixedRandom();
        } while (ans == baseFixedRandom());
        return ans;
    }
}
