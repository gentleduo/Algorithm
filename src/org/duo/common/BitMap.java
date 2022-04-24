package org.duo.common;

/**
 * | 或运算符 两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0。
 * & 与运算符 两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0。
 * ^ 异或运算 两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。(异或运算相当于无进位相加)
 * ~ 非运算符 如果位为0，结果是1，如果位为1，结果是0.
 */
public class BitMap {

    private long[] bits;

    public BitMap(int max) {
        // (max + 64) >> 6 ==> (max +64) / 64
        // 相当于要表示最大下标为max个数时，需要的数组的大小
        // 比如当max=1时，表示需要表示0到1两个数，所以只需要1个long型(一个long，64位)
        // 比如当max=64时，表示需要表示0-64，65个数，故需要2个long型
        bits = new long[(max + 64) >> 6];
    }

    public void add(int num) {

        // |= 运算符和 += 这一类的运算符一样，拆解开就是 a = a | b；
        // num >> 6 = num / 64 ==> 定位到是数组中的那个整数
        // num & 63 = num % 64 ==> 定位到整数中的哪一位
        // num应该用bits中的第几个long来表示用：num/64;
        // num是n个long中的第几位用num%64
        // 1L << (num & 63)的含义：
        // 例如：当num=0的时候，那么num & 63也是0，意思就是要将第0位设置为1，那么1L << (num & 63)的结果为1，取或后第0位被设置成了1
        //      当num=5的时候，那么num & 63也是5，意思就是要将第5位设置为1，那么1L << (num & 63)的结果为32，取或后第5位被设置成了1
        bits[num >> 6] |= (1L << (num & 63));
    }

    public void delete(int num) {

        // &= 运算符和 += 这一类的运算符一样，拆解开就是 a = a & b；
        bits[num >> 6] &= ~(1L << (num & 63));
    }

    public boolean contains(int num) {

        return (bits[num >> 6] & (1L << (num & 63))) != 0;
    }

    public static void main(String[] args) {

        System.out.println(5 & 63);
        System.out.println(1L << 5 & 63);
    }
}
