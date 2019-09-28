package com.mrinal.algos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IsThisABinarySearchTree {

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

	public boolean checkBST(Node root) {
		List<Integer> temp = new ArrayList<>();
		return checkBST(root, temp);
	}

	private boolean checkBST(Node root, List<Integer> temp) {
		if (root == null)
			return true;
		else {
			checkBST(root.left, temp);
			if (temp.size() == 0 || temp.get(temp.size() - 1) < root.data) {
				temp.add(root.data);
			} else
				return false;
			return checkBST(root.right, temp);
		}
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
		scan.close();
		IsThisABinarySearchTree isThisABinarySearchTree = new IsThisABinarySearchTree();
		System.out.println("Is BST: " + isThisABinarySearchTree.checkBST(root));
		// Node ans = lca(root, v1, v2);
		// System.out.println(ans.data);

	}

}
