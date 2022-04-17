package org.duo.common;

public class RandToRand {

    public static void main(String[] args) {

        // 等概率返回[0,1)中的一个double类型的值
        double probDouble = Math.random();
        // System.out.println(probDouble);

        // 等概率返回[0,10)中的一个double类型的值
        probDouble = Math.random() * 10;
        //System.out.println(probDouble);

        int testTimes = 100000000;
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
    }
}
