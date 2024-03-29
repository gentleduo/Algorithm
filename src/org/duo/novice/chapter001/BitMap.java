package org.duo.novice.chapter001;

public class BitMap {

    public static void main(String[] args) {

        System.out.println(1<<(170&63));

        System.out.println(2&63);
        System.out.println(2%64);

        System.out.println(1<<(2%64));
    }
}
