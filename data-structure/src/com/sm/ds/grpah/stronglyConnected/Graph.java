package com.sm.ds.grpah.stronglyConnected;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Graph
{
	private  int V;
	private List<Integer> adj[];
	

	public Graph(int v)
	{
		this.V = v;
		adj = new LinkedList[V];
		
		for(int i = 0; i < V; i++) adj[i] = new LinkedList<Integer>();
	}
	
	public void addEgde(int s, int d)
	{
		adj[s].add(d);
	}
	
	public void printStronlyConnectedComponent()
	{
		Deque<Integer> stack = new LinkedList<Integer>();
		
		// Create a visited array and initial assign to false
		boolean[] visited = new boolean[V];
		
		// Do DFS and fill the stack based on their finishing order
		for(int v = 0; v < V; v++)
		{
			if(!visited[v])
			{
				dfsFillOrder(v, visited, stack);
			}
		}
		
		// Create transpose of the graph, i.e. reversing the direction
		Graph trGraph = transposeGraph();
		
		// Mark visited[] to false for second DFS
		visited= new boolean[V];
		
		// Get one by one element from stack and perform DFS and print 
		// for each set of connected components
		while(!stack.isEmpty())
		{
			int v = stack.removeFirst();
			if(!visited[v])
			{
				trGraph.dfsUtil(v, visited); // on transpose graph
			}
		}
		
	}
	
	public List<Set<Integer>> dfsUtil(int v, boolean[] visited)
	{
		// Mark the vertex visited
		visited[v] = true;
		
		System.out.print(v + " ");
		
		// Get the adjacent vertices and check if visited  
		Iterator<Integer> itr = adj[v].iterator();
		while(itr.hasNext())
		{
			int nextV = itr.next();
			
			if(!visited[nextV])
			{
				dfsUtil(nextV, visited);
			}
		}
		
		System.out.println();
		return null; // Now printing in future will create List<Set -- of connected component>  		
	}

	private Graph transposeGraph()
	{
		Graph trGraph = new Graph(V);
		
		for(int i = 0; i < V; i++)
		{
			Iterator<Integer> itr = adj[i].iterator();
			
			while(itr.hasNext())
			{
				trGraph.addEgde(itr.next(), i);
			}
		}
		
		return trGraph;
	}

	public void dfsFillOrder(int v, boolean[] visited, Deque<Integer> stack)
	{
		// Set visited
		visited[v] = true;
		
		// Get all the adjacent vertices from this vertex
		Iterator<Integer> itr = adj[v].iterator();
		while(itr.hasNext())
		{
			int nextV = itr.next();
			if(!visited[nextV])
			{
				dfsFillOrder(nextV, visited, stack);
			}
		}
		
		// Add to stack, as all the vertex reachable from v finished now
		stack.offerFirst(v);
	}



	public static void main(String[] args)
	{
		// Create a graph given in the above diagram 
        Graph g = new Graph(5); 
        g.addEgde(1, 0); 
        g.addEgde(0, 2); 
        g.addEgde(2, 1); 
        g.addEgde(0, 3); 
        g.addEgde(3, 4); 
  
        System.out.println("Following are strongly connected components "+ 
                           "in given graph "); 
        g.printStronlyConnectedComponent(); 
	}

}
