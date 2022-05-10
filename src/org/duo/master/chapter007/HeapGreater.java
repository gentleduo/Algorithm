package org.duo.master.chapter007;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆
 * T一定要是非基础类型，有基础类型需求包一层
 */
public class HeapGreater<T> {

    // ArrayList的底层是数组，虽然有扩容，但是它的set(i,v)，get(i)时间复杂度也是O(1)
    private ArrayList<T> heap;
    // 反向索引表：某一个对象进来了，它放在堆的哪一个位置(即放在数组的哪一个位置)
    // 比如数组为：[a, b, c]
    // 那么在反向索引表里面就会记录：a==>0,b==>1,c==>2
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    // 由于T是泛型，所以必须提供相应的比较器
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    /**
     * 删除堆中的对象，将堆中的最后一个对象移动到被删除的对象所在的位置
     * 再通过反向索引表找到被删除的对象在数组中的下标，然后通过resign方法重新调整堆
     *
     * @param obj
     */
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

	/**
	 * 当原来有序的堆中的某个位置的对象被替换后，可以通过下面的方法调整堆，使堆仍然有序
	 * 调整原理：新的对象要么可以heapInsert方法往上冒，要么会通过heapify往下沉。
	 * 在走完heapInsert后，一进入heapify方法就会退出；同理如果一进入heapInsert方法就退出的话，那么就会执行heapify方法
	 *
	 * @param obj
	 */
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    // 请返回堆上的所有元素
    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }

}
