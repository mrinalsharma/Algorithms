/**
 * 
 */
package com.mrinal.algos;

import java.util.*;

class BSLowestCommonAncestor {

	public static class Node {
		Node left;
		Node right;
		int data;

		Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	public static Queue<Integer> queue = new LinkedList<>();

	/*
	 * class Node int data; Node left; Node right;
	 */
	
	public static Node lca(Node root, int v1, int v2) {
		Node curr = root;
		Node lastRemoved = null;
		while (curr != null) {
			queue.add(curr.data);
			if (curr.data == v1)
				break;
			else if (v1 <= curr.data) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		curr = root;
		while ((curr != null)) {
			if (v2 <= curr.data) {
				if ((queue.size() != 0) && (curr.data == queue.peek())) {
					lastRemoved = curr;
					curr = curr.left;
					queue.remove();
				} else
					break;
			} else {
				if ((queue.size() != 0) && (curr.data == queue.peek())) {
					lastRemoved = curr;
					curr = curr.right;
					queue.remove();
				} else
					break;
			}
		}
		return lastRemoved;
	}

	public static Node insert(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (data <= root.data) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		Node root = null;
		while (t-- > 0) {
			int data = scan.nextInt();
			root = insert(root, data);
		}
		int v1 = scan.nextInt();
		int v2 = scan.nextInt();
		scan.close();
		Node ans = lca(root, v1, v2);
		System.out.println(ans.data);
	}
}
