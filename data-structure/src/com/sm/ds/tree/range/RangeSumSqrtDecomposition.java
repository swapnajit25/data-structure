package com.sm.ds.tree.range;

/**
 * Update operation O(1)
 * Range sum O(sqrt(n)) instead O(n)
 */
public class RangeSumSqrtDecomposition
{
	private int block_size;
	// Will hold the sum of each block (chunk of elements)
	private int[] blocks;

	public static void main(String[] args)
	{
		int[] arr = {2,5,8,12,4,3,10,9};
		int n = arr.length;
		
		RangeSumSqrtDecomposition rsqrt = new RangeSumSqrtDecomposition();
		rsqrt.build(arr, n);
		
		int qs = 1;
		int qe = 6;
		
		System.out.println("Sum from index: " + qs + " to index: " + qe + " is: " + rsqrt.querySum(arr, qs, qe));
	}
	
	private void build(int[] arr, int length)
	{
		block_size = (int) Math.ceil(Math.sqrt(length));
		blocks = new int[block_size];
		
		for(int i = 0; i < length; i++) 
		{
			// Add the sum to each block
			blocks[i/block_size] += arr[i];
		}
	}
	
	private void update(int[] arr, int index, int value)
	{
		// Hold old value
		int oldValue = arr[index];
		
		// Update the array
		arr[index] = value;
		
		// Update the block sum with change
		blocks[index/block_size] += (value - oldValue);
	}
	
	/**
	 * 
	 * @param arr
	 * @param qs query start index
	 * @param qe query end index
	 * @return
	 */
	private int querySum(int[] arr, int qs, int qe)
	{
		// To find the sum
		int sum = 0;
		
		// Find the start and end block, for the query range
		int start_block = qs/block_size;
		int end_block = qe/block_size;
		
		// Now we need to add all the elements from qs to end of first block and all the sum of the other in-between blocks
		// and start of end_block till qe
		
		// Sum all from qs to last element of first block
		for(int i = qs; i < (start_block+1)*block_size; i++)
		{
			sum += arr[i];
		}
		
		// Sum all from start_block + 1 to end_block -1
		for(int i = start_block+1; i <= end_block-1; i++)
		{
			sum += blocks[i];
		}
		
		// Sum all from start of end_block till query end (qe)
		for(int i = end_block*block_size; i <= qe; i++)
		{
			sum += arr[i];
		}
		
		return sum;
	}

}
