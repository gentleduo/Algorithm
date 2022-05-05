# 算法复杂度 

## 常数操作(固定时间)

执行时间固定的，跟数据量没有关系的，常见的算术运算：加，减，乘，除；常见的位运算；赋值、比较、自增、自减；数组寻址等都是常数操作。

## 非常数操作(非固定时间)

执行时间非固定，跟数据量有关的操作，都不是常数时间的操作，比如：链表寻址。

## 复杂度

时间复杂度：数据量为N的样本，执行完整个算法，N与常数操作的数量的关系

选择排序思想：以从小到大排序为例，每一轮以第1个未排序的数作为最小值，记下下标，和后面的值依次进行比较，若后面的值比最小值小，那么更新最小值的下标，直到这轮比较完，会找到一个最小值，然后将最小值和第一个数进行值交换。下一轮以第2个数为最小值的下标，进行比较。总共需要n-1轮比较。

第一轮比较需要进行n-1次比较常数操作和一次交换常数操作

第二轮比较需要进行n-2次比较常数操作和一次交换常数操作

.

.

.

第n-1轮比较需要进行1次比较常数操作和一次交换常数操作

根据等差数列求和可知整个流程需要Sn=na1+n(n-1)d/2次比较常数操作，和n次交换常数操作，将两部分加起来得到整个流程的所有常数操作和为：a*N^2+b*N+c（a、b、c为常数，也可以理解为a，b，c为固定时间的常数操作的耗时），复杂度则只看表达式最高阶项的部分，低阶项以及高阶项前面的系数都不要，所以这个算法的时间复杂度为：O(N^2)。复杂度是确定算法流程的总操作数量(常数操作)与样本数量之间的表达式关系。

```java
public static int[] select(int[] num) {
    int min;
    int temp;
    for (int i = 0; i < num.length - 1; i++) {
        min = i;
        for (int j = i; j < num.length; j++) {
            if (num[j] < num[min]) {
                min = j;
            }
        }

        if (min != i) {
            temp = num[min];
            num[min] = num[i];
            num[i] = temp;
        }
    }
    return num;
}
```

当流程是随着数据的变化而变化的，那必须要用最差的情况来估计流程。即O()表示的是最差情况下算法是几阶

比如利用插入排序对数组进行排序时

当数据为[1,2,3,4,5,6,7]这种情况时，时间复杂度为O(1)

当数据为[7,6,5,4,3,2,1]这种情况时，时间复杂度为O(N^2)

所以，插入 排序的时间复杂度为O(N^2)

```java
public static void insertionSort(int[] arr) {
    if (arr == null || arr.length < 2) {
        return;
    }
    // 不只1个数
    for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
        for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
            swap(arr, j, j + 1);
        }
    }
}
// i和j是一个位置的话，会出错
public static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
}
```

# 数组和链表

## 数组

便于寻址，不便于增删数据(要维持数组在内存中的连续性)

## 链表

便于增删数据，不便于寻址

# 栈和队列

栈和队列是逻辑上的概念，用链表和数组都可以实现

## 栈

数据先进后出

## 队列

数据先进先出

# 哈希表和有序表

## 哈希表

增、删、查、改时间复杂度都是O(1)，只是这个常数操作的耗时会有点长； 当哈希表中的K,V是基本类型时，哈希表存储的是该基础类型的实际值，而当K,V是非基本类型的时候，哈希表存储的是对象的引用

## 有序表

比如 TreeMap；实现方式有：红黑树、avl、sb树、跳表；增删改查的时间复杂度为O(logN)

# 递归

## Master公式

形如：T(N)=a*T(N/b) + O(N^d) 的递归函数，即：子问题规模一致的递归可以直接通过Master公式来确定时间复杂度

如果log(b,a) < d，复杂度为O(N^d) 即：N的d次方

如果log(b,a) > d，复杂度为O(N^log(b,a)) 即：N的log(b,a)次方

如果log(b,a) == d，复杂度为O(N^d * logN) 即：N的d次方乘以log(2,N)

其中的a、b、d都是常数

比如通过递归求arr[L......R]范围中的最大值，相当于将问题分解为分别求0 ~ N/2中的最大值和N/2+1 ~ N-1中的最大值，然后再求这两个值的最大值，即T(N) = 2  * T(N / 2) + O (N^0) ，可以得到a=2，b=2，d=0，再通过master公式：因为log(2,2)>0，所以时间复杂度等于O(N^log(2,2))，即：O(N^1)，即：O(N)

```java
// 求arr中的最大值
public static int getMax(int[] arr) {
    return process(arr, 0, arr.length - 1);
}

// arr[L..R]范围上求最大值  L ... R   N
public static int process(int[] arr, int L, int R) {
    // arr[L..R]范围上只有一个数，直接返回，base case
    if (L == R) { 
        return arr[L];
    }
    // L...R 不只一个数
    // mid = (L + R) / 2
    int mid = L + ((R - L) >> 1); // 中点   	1
    int leftMax = process(arr, L, mid);
    int rightMax = process(arr, mid + 1, R);
    return Math.max(leftMax, rightMax);
}
```

