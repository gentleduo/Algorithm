package org.duo.novice.chapter001;

public class RandToRand {


    public static void main(String[] args) {
        // Math.random() 等概率的生成[0,1)区间内的double
        //System.out.println(Math.random());

        int testTimes = 1000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.3) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        int K = 9;
        // Math.random() * K ==> 等概率的生成[0,K)区间内的double
        // (int) (Math.random() * K) ==> 等概率的生成[0,K-1]区间内的int
        int ans = (int) (Math.random() * K);

        int[] counts = new int[9];
        for (int i = 0; i < testTimes; i++) {
            int ans1 = (int) (Math.random() * K);
            counts[ans1]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "这个数出现了" + counts[i] + "次");
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
            int ans1 = ExtendRand3();
            counts[ans1]++;
        }

        for (int i = 0; i < 7; i++) {
            System.out.println(i + "这个数出现了" + counts[i] + "次");
        }
    }

    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }


    // =================================================================================
    // 等概率的得到[1,5]
    public static int RandFunc() {

        return (int) (Math.random() * 5) + 1;
    }

    // 通过等概率函数：RandFunc，得到等概率得到[0,1]的函数
    public static int ExtendRand1() {

        int ans = 0;
        do {
            ans = RandFunc();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    // 通过等概率函数：ExtendRand1，等概率的得到二进制数：000 ~ 111；即：0 ~ 7 等概率返回
    public static int ExtendRand2() {

        return (ExtendRand1() << 2) + (ExtendRand1() << 1) + ExtendRand1();
    }

    // 通过等概率函数：ExtendRand2，等概率的得到0 ~ 6
    public static int ExtendRand3() {

        int ans = 0;
        do {
            ans = ExtendRand2();
        } while (ans == 7);
        return ans;
    }

    // =================================================================================
    /**
     * @return 84%的概率返回0；16%的概率返回1
     */
    public static int NotEqualProb() {

        return Math.random() < 0.84 ? 0 : 1;
    }

    // 等概率返回0,1
    // 原理：连续调用两次NotEqualProb：返回的结果组合为：00、11、01、10；并且概率分别为 P * P、 (1-P) * (1-P)、 P*(1-P)、(1-P)*P
    // 然后碰到 00 和 11 就重做
    public static int EqualProb() {
        // 在EqualProb函数内，当第一次调用NotEqualProb和第二次调用NotEqualProb的返回值不一样则返回，否则重做
        int ans = 0;
        do {
            ans = NotEqualProb();
        } while (ans == NotEqualProb());
        // 当连续俩次的结果为0 , 1或者 1 , 0，则返回第一次的结果
        // 否则重做
        return ans;
    }
}
