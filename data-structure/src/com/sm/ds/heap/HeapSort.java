package com.sm.ds.heap;

import java.util.Arrays;

public class HeapSort
{

	public static void main(String[] args)
	{
		int[] arr = {12, 41, 13, 5, 6, 7};
		
		HeapSort hs = new HeapSort();
		hs.sort(arr);
		
		System.out.println(Arrays.toString(arr));
	}

	public void sort(int[] arr)
	{
		int n = arr.length;
		
		// Build heap (rearrange array)
		// heapify should happen in bottom-up order
		for(int i = n/2 -1; i >= 0; i--)
		{
			heapify(arr, n, i);
		}
		
		// For sorting we need to remove root element one by one
		// add towards the end of array and do heapify till before last index of array
		// As the root has greater element (max heap) we will get sorted array at the end in ascending order
		for(int i = n-1; i > 0; i--)
		{
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			
			heapify(arr, i, 0);
		}
	}
	
	// Heapify recursively from index i to n, size given for the array
	public void heapify(int[] arr, int n, int i)
	{
		int largest = i; // Considering given root has largest value
		int l = 2*i + 1;
		int r = 2*i + 2;
		
		// If left > root replace largest
		if((l < n) && arr[l] > arr[largest]) largest = l;
		// If right > root replace largest
		if((r < n) && arr[r] > arr[largest]) largest = r;
		
		// If largest is root node, swap
		if(largest != i)
		{
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			// Heapify sub tree, as we moved i
			heapify(arr, n, largest);
		}
	}

}
