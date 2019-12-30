package com.sm.ds.prim;

import java.util.Arrays;

public class PrimsMST
{
	private static final int V = 5;

	public static void main(String[] args)
	{
		PrimsMST t = new PrimsMST(); 
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 }, 
                                      { 2, 0, 3, 8, 5 }, 
                                      { 0, 3, 0, 0, 7 }, 
                                      { 6, 8, 0, 0, 9 }, 
                                      { 0, 5, 7, 9, 0 } };
                                      
       
       t.primMST(graph);
	}
	
	public void primMST(int[][] graph)
	{
		int[] parent = new int[V];
		int[] weight = new int[V];
		boolean[] visited = new boolean[V];
		
		Arrays.fill(weight, Integer.MAX_VALUE);
		weight[0] = 0;
		parent[0] = -1;
		int u = 0;
		
		for(int count=0; count < V-1; count++)
		{
			visited[u] = true;
			
			// Find the weight of the connected nodes
			for(int x = 0; x < V; x++)
			{
				if(graph[u][x] != 0 && visited[x] == false && graph[u][x] < weight[x])
				{
					parent[x] = u;
					weight[x] = graph[u][x];
				}				
			}
			
			u = minWeight(weight, visited);
		}
		
		// PrintMST
		printMST(parent, weight);
		
	}

	private int minWeight(int[] weight, boolean[] visited)
	{
		int min = Integer.MAX_VALUE;
		int min_index = 0;
		
		for(int i = 0; i < V; i++)
		{
			if(visited[i] == false && weight[i] < min)
			{
				min = weight[i];
				min_index = i;
			}
		}
		
		return min_index;
	}
	
	private void printMST(int[] parent, int[] weight)
	{
		for(int i = 1; i < V; i++)
		{
			System.out.println(parent[i] + "\t" + "-" + "\t" + i + "\t" + "=" + weight[i]);
		}
	}

}
