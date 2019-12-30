package com.sm.ds.disjoin.set;

import java.util.Arrays;

public class UnionFindDetectCycle
{
	static class Graph
	{
		int V, E;
		Edge edges[]; 
		
		public Graph(int v, int e)
		{
			this.V = v;
			this.E = e;
			
			edges = new Edge[E];
			for(int i = 0; i < E; i++)
			{
				edges[i] = new Edge();
			}
		}
	}
	
	static class Edge
	{
		private int src, dest;
	}
	
	// Utility method to do union of two sets
	private static void union(int[] parent, int x, int y)
	{
		parent[y] = x;
	}
	
	// Utility function to find the top node
	private static int find(int[] parent, int i)
	{
		if(parent[i] == -1) return i;
		
		return find(parent, parent[i]);
	}
	
	public static int isCycle(Graph graph)
	{
		int[] parent = new int[graph.V];
		Arrays.fill(parent, -1);
		
		for(int i = 0; i < graph.E; i++)
		{
			int x = find(parent, graph.edges[i].src);
			int y = find(parent, graph.edges[i].dest);
			
			if(x == y) return 1;
			
			union(parent, x, y);
		}
		
		return 0;
	}

	public static void main(String[] args)
	{
		Graph graph = new Graph(3, 3);
		
		// add edge 0-1 
        graph.edges[0].src = 0; 
        graph.edges[0].dest = 1; 
  
        // add edge 1-2 
        graph.edges[1].src = 1; 
        graph.edges[1].dest = 2; 
  
        // add edge 0-2 
        graph.edges[2].src = 0; 
        graph.edges[2].dest = 2;
        
        if(isCycle(graph) == 1) System.out.println("Graph is Cyclic");
        else System.out.println("Graph is non cyclic");

	}

}
