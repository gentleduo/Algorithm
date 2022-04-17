package org.duo.common;

public class BitOperation {

    /**
     * 将一个整型转化为二进制
     *
     * @param num
     * @return
     */
    public static String toBinary(int num) {

        StringBuilder binaryBuf = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            if ((num & 1 << i) == 0) {
                binaryBuf.append("0");
            } else {
                binaryBuf.append("1");
            }
            //System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        return binaryBuf.toString();
    }

    public static void main(String[] args) {

        // 整型总共32位，最高位为符号位，
        // 所以按理说正整数表示的范围为的1到2的31次方-1，负整数表示的范围为负2的31次方-1到-1
        // 但是这样的话正零：00000000000000000000000000000000和负零：10000000000000000000000000000000这两个数就重复了
        // 于是用正零来表示0，负零来表示负的最小值，即：负2的31次方
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(toBinary(Integer.MAX_VALUE));
        System.out.println(toBinary(Integer.MIN_VALUE));
        System.out.println("================================");
        int a = 208410238;
        int b = 9810381;
        System.out.println(toBinary(a));
        System.out.println(toBinary(b));
        System.out.println("============与运算==============>");
        System.out.println(toBinary(a & b));
        System.out.println("============或运算==============>");
        System.out.println(toBinary(a | b));
        // 相同为0，不同为1
        System.out.println("============异或运算============>");
        System.out.println(toBinary(a ^ b));
        System.out.println("============非运算==============>");
        System.out.println(toBinary(~a));
        System.out.println("================================");
        // 左移，正数和负数都一样（不区分带符号左移和不带符号左移）
        System.out.println(-1 << 2);
        // 右移分带符号右移和不带符号右移，正数的带符号右移和不带符号右移是一样的
        System.out.println(Integer.MIN_VALUE);
        System.out.println(toBinary(Integer.MIN_VALUE));
        // 负数的带符号位右移：高位补符号位
        System.out.println("============负数带符号右移========>");
        System.out.println(Integer.MIN_VALUE >> 1);
        System.out.println(toBinary(Integer.MIN_VALUE >> 1));
        // 负数的不带符号位右移：高位补0
        System.out.println("============负数不带符号右移======>");
        System.out.println(Integer.MIN_VALUE >>> 1);
        System.out.println(toBinary(Integer.MIN_VALUE >>> 1));

        System.out.println(toBinary(-89103));
        System.out.println(toBinary(-89103 >> 3));
        System.out.println(-89103 >> 3);

        System.out.println(toBinary(-1));
        System.out.println(toBinary(-1 << 34));

        System.out.println("=======取反加一：相当于取相反数====>");
        int c = 5;
        System.out.println(~c + 1);
        c = -5;
        System.out.println(~c + 1);

        System.out.println(toBinary(-5));
    }
}
