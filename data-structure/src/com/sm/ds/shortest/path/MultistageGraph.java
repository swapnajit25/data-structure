package com.sm.ds.shortest.path;

public class MultistageGraph
{
	private static final int V = 8;
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args)
	{
		int[][] graph = new int[][] {
			{INF, 1, 2, 5, INF, INF, INF, INF}, 
	        {INF, INF, INF, INF, 4, 11, INF, INF}, 
	        {INF, INF, INF, INF, 9, 5, 16, INF}, 
	        {INF, INF, INF, INF, INF, INF, 2, INF}, 
	        {INF, INF, INF, INF, INF, INF, INF, 18}, 
	        {INF, INF, INF, INF, INF, INF, INF, 13}, 
	        {INF, INF, INF, INF, INF, INF, INF, 2}
		};
		
		MultistageGraph msg = new MultistageGraph();
		
		msg.shortestDist(graph);
	}
	
	public void shortestDist(int[][] graph)
	{
		// Create distance array & set last node dist to 0
		int[] dist = new int[V];
		dist[V-1] = 0;
		
		int[] child = new int[V];
		child[V-1] = -1;
		
		for(int i = V-2; i >= 0; i--)
		{
			dist[i] = INF;
			
			for(int j = i; j < V; j++)
			{
				if(graph[i][j] == INF) continue;
				
				// By comparing minimum distance so far we can set the minimum distance for j from i
				dist[i] = Math.min(dist[i], graph[i][j] + dist[j]);
				// Set the child of i to j
				child[i] = j;
			}
		}
		
		printSolution(dist, child);
	}
	
	private void printSolution(int[] dist, int[] child)
	{
		System.out.println("Minimum cost from Node O to 7 is: " + dist[0]);
		
		int node = 0;
		System.out.print("Through path: " + node + "\t");
		while(node != -1)
		{
			node = child[node];
			if(node == -1) break;
			System.out.print(node + "\t");
		}
	}

}
