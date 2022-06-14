package org.duo.master.chapter035;

public class Code01_AVLTreeMap {

	/**
	 * 因为AVL树是有序表里面用到的，所以必须要求它的key可比较
	 * @param <K>
	 * @param <V>
	 */
	public static class AVLNode<K extends Comparable<K>, V> {
		public K k;
		public V v;
		// 指向左孩子和右孩子的结点
		public AVLNode<K, V> l;
		public AVLNode<K, V> r;
		// AVL树中的平衡因子：高度（整棵AVL树的高度）
		public int h;

		public AVLNode(K key, V value) {
			k = key;
			v = value;
			h = 1;
		}
	}

	/**
	 * 用AVL树实现的有序表
	 * @param <K>
	 * @param <V>
	 */
	public static class AVLTreeMap<K extends Comparable<K>, V> {

		private AVLNode<K, V> root; // 整棵树的根结点
		private int size; // 加入的key的个数

		public AVLTreeMap() {
			root = null;
			size = 0;
		}

		// 针对cur结点整棵树右旋
		private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
			// 先记住cur的左孩子，因为右旋是cur往右边倒
			AVLNode<K, V> left = cur.l;
			// 然后左孩子的右树挂在我的（cur）的左边
			cur.l = left.r;
			// 左孩子的右接管cur
			left.r = cur;
			// 调整cur和left的高度，并且一定要先调整cur的高度后再调整left，因为右旋后头结点是left了
			cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
			left.h = Math.max((left.l != null ? left.l.h : 0), (left.r != null ? left.r.h : 0)) + 1;
			return left;
		}

		private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
			AVLNode<K, V> right = cur.r;
			cur.r = right.l;
			right.l = cur;
			cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
			right.h = Math.max((right.l != null ? right.l.h : 0), (right.r != null ? right.r.h : 0)) + 1;
			return right;
		}

		private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
			if (cur == null) {
				return null;
			}
			int leftHeight = cur.l != null ? cur.l.h : 0;
			int rightHeight = cur.r != null ? cur.r.h : 0;
			if (Math.abs(leftHeight - rightHeight) > 1) {
				if (leftHeight > rightHeight) {
					int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
					int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
					// 既是LL型，又是LR型，按照LL型标准来调整平衡性，所以这里要大于等于
					if (leftLeftHeight >= leftRightHeight) {
						cur = rightRotate(cur);
					} else {
						cur.l = leftRotate(cur.l);
						cur = rightRotate(cur);
					}
				} else {
					int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
					int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
					// 同理，如果既是RR型，又是RL型，按照RR型标准来调整平衡性
					if (rightRightHeight >= rightLeftHeight) {
						cur = leftRotate(cur);
					} else {
						cur.r = rightRotate(cur.r);
						cur = leftRotate(cur);
					}
				}
			}
			return cur;
		}

		private AVLNode<K, V> findLastIndex(K key) {
			AVLNode<K, V> pre = root;
			AVLNode<K, V> cur = root;
			while (cur != null) {
				pre = cur;
				if (key.compareTo(cur.k) == 0) {
					break;
				} else if (key.compareTo(cur.k) < 0) {
					cur = cur.l;
				} else {
					cur = cur.r;
				}
			}
			return pre;
		}

		private AVLNode<K, V> findLastNoSmallIndex(K key) {
			AVLNode<K, V> ans = null;
			AVLNode<K, V> cur = root;
			while (cur != null) {
				if (key.compareTo(cur.k) == 0) {
					ans = cur;
					break;
				} else if (key.compareTo(cur.k) < 0) {
					ans = cur;
					cur = cur.l;
				} else {
					cur = cur.r;
				}
			}
			return ans;
		}

		private AVLNode<K, V> findLastNoBigIndex(K key) {
			AVLNode<K, V> ans = null;
			AVLNode<K, V> cur = root;
			while (cur != null) {
				if (key.compareTo(cur.k) == 0) {
					ans = cur;
					break;
				} else if (key.compareTo(cur.k) < 0) {
					cur = cur.l;
				} else {
					ans = cur;
					cur = cur.r;
				}
			}
			return ans;
		}

		/**
		 * 在以cur为头的整颗子树上，加记录，并且把整棵树的头结点返回， add()方法不会遇到相同的key
		 * 搜索二叉树里不加重复的key，只要说搜索二叉树，它的潜台词就是每个key都不一样
		 * @param cur
		 * @param key
		 * @param value
		 * @return
		 */
		private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
			if (cur == null) {
				return new AVLNode<K, V>(key, value);
			} else {
				if (key.compareTo(cur.k) < 0) {
					cur.l = add(cur.l, key, value);
				} else {
					cur.r = add(cur.r, key, value);
				}
				cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
				// 以上只是搜索二叉树的增加结点，加完结点后，调平衡
				return maintain(cur);
			}
		}

		// 在cur这棵树上，删掉key所代表的节点
		// 返回cur这棵树的新头部
		private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
			if (key.compareTo(cur.k) > 0) {
				cur.r = delete(cur.r, key);
			} else if (key.compareTo(cur.k) < 0) {
				cur.l = delete(cur.l, key);
			} else {
				if (cur.l == null && cur.r == null) {
					cur = null;
				} else if (cur.l == null && cur.r != null) {
					cur = cur.r;
				} else if (cur.l != null && cur.r == null) {
					cur = cur.l;
					// 左右孩子都不为空的时候
				} else {
					// 找到右子树上的最左结点
					AVLNode<K, V> des = cur.r;
					while (des.l != null) {
						des = des.l;
					}
					// 将得到的右子树上的最左结点，替换要删除的结点，
					cur.r = delete(cur.r, des.k);
					des.l = cur.l;
					des.r = cur.r;
					cur = des;
				}
			}
			if (cur != null) {
				cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
			}
			// 调整树的平衡性
			return maintain(cur);
		}

		public int size() {
			return size;
		}

		public boolean containsKey(K key) {
			if (key == null) {
				return false;
			}
			AVLNode<K, V> lastNode = findLastIndex(key);
			return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
		}

		public void put(K key, V value) {
			if (key == null) {
				return;
			}
			AVLNode<K, V> lastNode = findLastIndex(key);
			if (lastNode != null && key.compareTo(lastNode.k) == 0) {
				lastNode.v = value;
			} else {
				size++;
				root = add(root, key, value);
			}
		}

		public void remove(K key) {
			if (key == null) {
				return;
			}
			if (containsKey(key)) {
				size--;
				root = delete(root, key);
			}
		}

		public V get(K key) {
			if (key == null) {
				return null;
			}
			AVLNode<K, V> lastNode = findLastIndex(key);
			if (lastNode != null && key.compareTo(lastNode.k) == 0) {
				return lastNode.v;
			}
			return null;
		}

		public K firstKey() {
			if (root == null) {
				return null;
			}
			AVLNode<K, V> cur = root;
			while (cur.l != null) {
				cur = cur.l;
			}
			return cur.k;
		}

		public K lastKey() {
			if (root == null) {
				return null;
			}
			AVLNode<K, V> cur = root;
			while (cur.r != null) {
				cur = cur.r;
			}
			return cur.k;
		}

		public K floorKey(K key) {
			if (key == null) {
				return null;
			}
			AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
			return lastNoBigNode == null ? null : lastNoBigNode.k;
		}

		public K ceilingKey(K key) {
			if (key == null) {
				return null;
			}
			AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
			return lastNoSmallNode == null ? null : lastNoSmallNode.k;
		}
	}
}