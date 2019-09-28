package com.mrinal.algos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeadersInAnArray {
	Map<Integer, Boolean> leaders = new HashMap<>();

	void countLeadersInAnArray(List<Integer> list) {
		int i = list.size() - 1;
		int max = list.get(i);
		System.out.print(max + " ");
		while (--i > 0) {
			if (list.get(i) > max) {
				max = list.get(i);
				System.out.print(max + " ");
			}
		}
	}

	public static void main(String[] args) {
		Integer array[] = { 16, 17, 4, 3, 5, 2 };
		List<Integer> list = Arrays.asList(array);
		System.out.println(list.toString());
		LeadersInAnArray countLeadersInAnArray = new LeadersInAnArray();
		countLeadersInAnArray.countLeadersInAnArray(list);

	}
}
