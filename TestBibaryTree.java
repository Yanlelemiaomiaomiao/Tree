package com.bit.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TestBibaryTree {
    class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;

        public TreeNode(char val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    //根据字符串创建二叉树
    public int i = 0;
    TreeNode createTestTree(String s) {
        TreeNode root = null;
        //1、如果对应字符不是#，那么创建节点
        if (s.charAt(i) != '#') {
            root = new TreeNode(s.charAt(i));
            i++;
            root.left = createTestTree(s);
            root.right = createTestTree(s);
        } else {//如果是#，那么下标直接++
            i++;
        }
        return root;
    }

    // 结点个数
    int getSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getSize(root.left) + getSize(root.right) + 1;
    }

    // 叶子结点个数
    int getLeafSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return getLeafSize(root.left) + getLeafSize(root.right);
    }

    // 第 k 层结点个数
    int getKLevelSize(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return getKLevelSize(root.left, k - 1) + getKLevelSize(root.right, k - 1);
    }


    // 查找，依次在二叉树的 根、左子树、右子树 中查找 value，如果找到，返回结点，否则返回 null
    TreeNode find(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.val == value) {
            return root;
        }
        TreeNode r = find(root.left, value);
        if (r != null) {
            return r;
        }
        r = find(root.right, value);
        if (r != null) {
            return r;
        }
        return null;
    }


    //二叉树的高度   最大深度
    int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    //二叉树的前序遍历
    void binaryTreePrevOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        binaryTreePrevOrder(root.left);

    }

    //二叉树的中序遍历
    void binaryTreeInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        binaryTreeInOrder(root.left);
        System.out.print(root.val + " ");
        binaryTreeInOrder(root.right);
    }

    //二叉树的后序遍历
    void binaryTreePostOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        binaryTreePostOrder(root.left);
        binaryTreePostOrder(root.right);
        System.out.println(root.val + " ");
    }


    //二叉树的前序遍历非递归
    void binaryTreePrevOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode top = null;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                System.out.print(cur.val + " ");
                stack.push(cur);
                cur = cur.left;
            }
            top = stack.pop();
            cur = top.right;
        }
    }

    //二叉树的中序遍历非递归
    void binaryTreeInOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode top = null;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            top = stack.pop();
            System.out.println(top.val + " ");
            cur = top.right;
        }
    }

    //二叉树的后序遍历非递归
    void binaryTreePostOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == pre) {
                System.out.println(cur.val + " ");
                stack.pop();
                pre = cur;//代表cur已经打印
                cur = null;
            } else {
                cur = cur.right;
            }
        }
    }

    //二叉树的层序遍历
    void binaryTreeLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);//add  offer
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            System.out.print(cur.val + " ");
            queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    //判断一棵树是否是完全二叉树 返回0代表是完全二叉树
    int binaryTreeComplete(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);//add  offer
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                break;
            }
        }
        while (!queue.isEmpty()) {
            TreeNode pre = queue.poll();
            if (pre != null) {
                return -1;//不是完全二叉树
            }
        }
        return 0;//是完全二叉树
    }
}
