package com.mrinal.algos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountInversion {

	int countInversion(List<Integer> list, int low, int high) {
		System.out.println("Low to High:" + list.subList(low, high+1));
		if (low == high)
			return 0;
		int mid = low + ((high - low) / 2);
		int leftCount = countInversion(list, low, mid);
		int rightCount = countInversion(list, mid + 1, high);
		int mergeCount = mergeCount(list, low, high, mid);
		return leftCount + rightCount + mergeCount;
	}

	int mergeCount(List<Integer> list, int low, int high, int mid) {
		int i = low, j = mid + 1;
		int inversionCount = 0;
		List<Integer> aux = new ArrayList<>();
		while (i <= mid && j <= high) {
			if (list.get(i) <= list.get(j))
			{
				aux.add(list.get(i));
				if(++i > mid)
				{
					aux.addAll(list.subList(j, high+1));
				}
			}
			else {
				aux.add(list.get(j));
				if(++j > high)
				{
					aux.addAll(list.subList(i, mid+1));
				}
				inversionCount = inversionCount+ list.subList(i, mid+1).size();
			}
		}
		i=0;
		for(int k = low; k<=high;k++)
		{
			list.set(k, aux.get(i++));
		}
		return inversionCount;
	}

	public static void main(String[] args) {
		Integer array[] = { 10,9,8,6,5,4,3,2,1 };
		List<Integer> list = Arrays.asList(array);
		System.out.println(list.toString());
		CountInversion countInversion = new CountInversion();
		System.out.println("Conversion Count: " + countInversion.countInversion(list, 0, list.size() - 1));
		for (Integer integer : list) {
			System.out.print(integer+" ");
		}
	}

}
