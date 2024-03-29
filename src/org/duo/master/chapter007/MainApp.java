package org.duo.master.chapter007;

import java.util.Comparator;
import java.util.TreeMap;

public class MainApp {


    public static void main(String[] args) {


        Student student1 = new Student("A", 1, 40);
        Student student2 = new Student("B", 2, 21);
        Student student3 = new Student("C", 3, 12);
        Student student4 = new Student("D", 4, 62);
        Student student5 = new Student("E", 5, 42);
        Student student6 = new Student("F", 6, 40);
        Student student7 = new Student("G", 7, 40);

        AgeOrder ageOrder = new AgeOrder();
//        TreeMap<Student, String> treeMap = new TreeMap<>(ageOrder);
//        treeMap.put(student1, "我是学生1，我的名字叫A");
//        treeMap.put(student2, "我是学生2，我的名字叫B");
//        treeMap.put(student3, "我是学生3，我的名字叫C");
//        treeMap.put(student4, "我是学生4，我的名字叫D");
//        treeMap.put(student5, "我是学生5，我的名字叫E");
//        for (Student s : treeMap.keySet()) {
//            System.out.println(s.name + "," + s.id + "," + s.age);
//        }

        HeapGreater<Student> studentHeapGreater = new HeapGreater<Student>(ageOrder);
        studentHeapGreater.push(student1);
        studentHeapGreater.push(student2);
        studentHeapGreater.push(student3);
        studentHeapGreater.push(student4);
        studentHeapGreater.push(student5);
        studentHeapGreater.push(student6);
        studentHeapGreater.push(student7);


//        int age = studentHeapGreater.pop().age;
//        System.out.println("age is " + age );
//        age = studentHeapGreater.pop().age;
//        System.out.println("age is " + age );
//        age = studentHeapGreater.pop().age;
//        System.out.println("age is " + age );
//        age = studentHeapGreater.pop().age;
//        System.out.println("age is " + age );
//        age = studentHeapGreater.pop().age;
//        System.out.println("age is " + age );
//        age = studentHeapGreater.pop().age;
//        System.out.println("age is " + age );
//        age = studentHeapGreater.pop().age;
//        System.out.println("age is " + age );


        studentHeapGreater.remove(student6);


        int age = studentHeapGreater.pop().age;
        System.out.println("age is " + age);
        age = studentHeapGreater.pop().age;
        System.out.println("age is " + age);
        age = studentHeapGreater.pop().age;
        System.out.println("age is " + age);
        age = studentHeapGreater.pop().age;
        System.out.println("age is " + age);
        age = studentHeapGreater.pop().age;
        System.out.println("age is " + age);
        age = studentHeapGreater.pop().age;
        System.out.println("age is " + age);
    }
}
