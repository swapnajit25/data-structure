package com.sm.ds.shortest.path;

import java.util.Arrays;

public class Dijkstra
{
	private static final int V = 9;

	public static void main(String[] args)
	{
		int[][] graph = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
		
		Dijkstra shortestPaths = new Dijkstra();
		shortestPaths.runDijkstra(graph);

	}
	
	public void runDijkstra(int[][] graph)
	{
		// To hold shortest path
		int[] dist = new int[V];
		boolean[] visited = new boolean[V];
		int u = 0;
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[0] = 0; // considering 0 as the starting node
		for(int count = 0; count < V; count++)
		{
			// Every time control reaches here make the visited true
			visited[u] = true;
			
			for(int v = 0; v < V; v++)
			{
				if(graph[u][v] != 0 && visited[v] == false && dist[u] + graph[u][v] < dist[v]) 
				{
					dist[v] = dist[u] + graph[u][v];
				}
			}
			
			// Find the minimum distance/cost/weight node to pick
			u = minDistance(dist, visited);
		}
		
		
		// Print the solution
		printSolution(dist);
	}
	
	public int minDistance(int[] dist, boolean[] visited)
	{
		int min = Integer.MAX_VALUE;
		int min_index = 0;
		
		for(int v = 0; v < V; v++)
		{
			if(visited[v] == false && dist[v] < min)
			{
				min = dist[v];
				min_index = v;
			}
		}
		
		return min_index;
	}
	
	private void printSolution(int[] dist)
	{
		System.out.println("Vertex \t\t Distance from Source"); 
        for (int i = 0; i < V; i++) 
            System.out.println(i + " \t\t " + dist[i]); 
	}

}
