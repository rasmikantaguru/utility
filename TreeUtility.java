package com.utility;

import java.util.ArrayList;
import java.util.List;

public class TreeUtility {
	
	private static Node root = null;
	
	public static Node constructBinaryTree(int[] arr) {
		Node curr = addToBST(null, arr[0]);
		System.out.println(root);
		for (int i = 1; i < arr.length; i++) {
			curr = addToBST(root, arr[i]);
			System.out.println(curr);
		}
		return root;
	}
	
	public static Node addToBST(Node current, int info) {
		if (root == null) {
			root = new Node(info);
			return root;
		}
		
		if (current == null) {
			current = new Node(info);
			return current;
		}
		
		if (info < current.getInfo()) {
			current.setLeft(addToBST(current.getLeft(), info));
		} else {
			current.setRight(addToBST(current.getRight(), info));
		}
		
		return current;
	}
	
	public static void traverseInOrder(Node node) {
		if (null == node) {
			return;
		}
		System.out.println(node.getInfo());
		traverseInOrder(node.getLeft());
		traverseInOrder(node.getRight());
	}
	
	public static void traverseInOrder(Node node, List<Integer> list) {
		if (null == node) {
			return;
		}
		list.add(node.getInfo());
		traverseInOrder(node.getLeft(), list);
		traverseInOrder(node.getRight(), list);
	}
	
	public static void findCommonAncestor(int first, int second) {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		traverseInOrder(root, list1);
		traverseInOrder(root, list2);
		System.out.println(list1+"<->"+list2);
		int n = list1.size() < list2.size() ? list1.size() : list2.size();
		int anc = 0;
		for (int i = 0; i < n; i++) {
			if (list1.contains(list2.get(i))) {
				if (list2.get(i) == first || list2.get(i) == second) {
					break;
				}
				anc = list2.get(i);
			}
		}
		System.out.println("common anc::"+anc);
	}


	public static void main(String[] args) {
		constructBinaryTree(new int[]{15,23,12,10,14});
		Node temp = root;
		traverseInOrder(temp);
		findCommonAncestor(10,14);
	}

}
