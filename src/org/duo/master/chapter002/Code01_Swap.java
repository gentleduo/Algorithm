package org.duo.master.chapter002;

/**
 * 异或运算：相同为0，不同为1
 * 同或运算：相同为1，不同为0
 * <p>
 * 异或运算可以记成无进位相加
 */
public class Code01_Swap {

    public static void main(String[] args) {

        int a = 16;
        int b = 603;

        System.out.println(a);
        System.out.println(b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr = {3, 1, 100};

        int i = 0;
        int j = 0;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        System.out.println(arr[i] + " , " + arr[j]);

        System.out.println(arr[0]);
        System.out.println(arr[2]);

        swap(arr, 0, 0);

        System.out.println(arr[0]);
        System.out.println(arr[2]);
    }

    public static void swap(int[] arr, int i, int j) {
        // arr[0] = arr[0] ^ arr[0];
        arr[i] = arr[i] ^ arr[j];
        // 下面相当于 arr[j] = arr[i] ^ arr[j] ^ arr[j]，而由于arr[j] ^ arr[j]等于零，然后再用 arr[i] ^ 0 那么相当于将arr[i]赋值给 arr[j]
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}