package org.duo.novice.chapter001;

public class ByteHandler {

    public static void main(String[] args) {


        System.out.println(1073741824 << 1);

        for (int i = 31; i >= 0; i--) {

            if ((1073741824 & (1 << i)) == 0) {
                System.out.print("0");
            } else {
                System.out.print("1");
            }

        }
        System.out.println("");

        System.out.println(1 << 3);

        System.out.println(-1 << 1);


        for (int i = 31; i >= 0; i--) {

            if ((-2 & (1 << i)) == 0) {
                System.out.print("0");
            } else {
                System.out.print("1");
            }

        }

        System.out.println();

        for (int i = 31; i >= 0; i--) {

            if ((1073741824 & (1 << i)) == 0) {
                System.out.print("0");
            } else {
                System.out.print("1");
            }

        }

        System.out.println();
        System.out.println(-2147483647 << 1);


        System.out.println(Integer.MIN_VALUE >>> 1);
    }
}
