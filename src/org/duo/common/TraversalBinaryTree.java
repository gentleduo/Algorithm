package org.duo.common;

import javax.swing.tree.TreeNode;

public class TraversalBinaryTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            this.value = v;
        }
    }

    /**
     * 递归序
     *
     * @param head
     */
    public static void f(Node head) {

        if (head == null) {
            return;
        }
        //
        f(head.left);
        //
        f(head.right);
        //
    }

    /**
     * 头==>左==>右
     *
     * @param head
     */
    public static void pre(Node head) {

        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    /**
     * 左==>头==>右
     *
     * @param head
     */
    public static void in(Node head) {

        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    /**
     * 左==>右==>头
     *
     * @param head
     */
    public static void pos(Node head) {

        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    public static boolean isSameTree(Node p, Node q) {

        if (p == null ^ q == null) {

            return false;
        }

        if (p == null && q == null) {

            return true;
        }

        return p.value == q.value && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 返回数的最大深度
     *
     * @param node
     * @return
     */
    public static int maxDepth(Node node) {

        if (node == null) {
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    /**
     * @param root
     * @return 是否为平衡二叉树。平衡二叉树是指任意节点的左右子树的高度差不大于1。
     */
    public static boolean isBalanced(Node root) {
        return process(root).isBalanced;
    }

    /**
     * isBalanced:整棵树是否平衡
     * height:整棵树的高度是什么
     */
    public static class Info {

        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBalanced, height);
    }

    public static class Detail {

        public boolean isBinarySearchTree;
        public int max;
        public int min;

        public Detail(boolean isBinarySearchTree, int max, int min) {
            this.isBinarySearchTree = isBinarySearchTree;
            this.max = max;
            this.min = min;
        }
    }

    /**
     *
     * @param node
     * @return 判断是否是搜索二叉树，搜索二叉树是指任意节点的左子树上所有节点的值都小于该节点的值；右子树上所有节点的值都大于该节点的值
     */
    public static Detail execute(Node node) {
        if (node == null) {
            return null;
        }
        Detail leftDetail = execute(node.left);
        Detail rightDetail = execute(node.right);

        int max = node.value;
        int min = node.value;
        if (leftDetail != null) {
            max = Math.max(leftDetail.max, max);
            min = Math.min(leftDetail.min, min);
        }
        if (rightDetail != null) {
            max = Math.max(rightDetail.max, max);
            min = Math.min(rightDetail.min, min);
        }
        boolean isBinarySearchTree = true;
        if (leftDetail != null && !leftDetail.isBinarySearchTree) {
            isBinarySearchTree = false;
        }
        if (rightDetail != null && !rightDetail.isBinarySearchTree) {
            isBinarySearchTree = false;
        }

        boolean leftMaxLessX = leftDetail == null ? true : (leftDetail.max < node.value);
        boolean rightMinMoreX = rightDetail == null ? true : (rightDetail.min > node.value);

        if (!leftMaxLessX || !rightMinMoreX) {
            isBinarySearchTree = false;
        }
        return new Detail(isBinarySearchTree, max, min);
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
//        head.left.left = new Node(4);
//        head.left.right = new Node(5);
//        head.right.left = new Node(6);
//        head.right.right = new Node(7);
//        pre(head);
//        System.out.println("==================");
//        in(head);
//        System.out.println("==================");
//        pos(head);

        System.out.println(maxDepth(head));
    }
}
