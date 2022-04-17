package org.duo.common;

public class SumOfFactorial {

    public static long factorial(int n) {
        long ans = 0;
        long cur = 1;
        for (int i = 1; i <= n; i++) {
            cur = cur * i;
            ans += cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        // 1+1*2+2*3+6*4+24*5
        //1+2+6+24+120
        int n = 5;
        System.out.println(factorial(n));
    }
}
