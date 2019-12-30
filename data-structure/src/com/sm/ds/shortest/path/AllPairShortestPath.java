package com.sm.ds.shortest.path;

import java.util.Arrays;

public class AllPairShortestPath
{
	private static final int INF = Integer.MAX_VALUE;
	private static final int V = 4;

	public static void main(String[] args)
	{
		int[][] graph = new int[][] { 
			{0,   5,  INF, 10}, 
            {INF, 0,   3, INF}, 
            {INF, INF, 0,   1}, 
            {INF, INF, INF, 0}
			
		};
		
		AllPairShortestPath shortestPath = new AllPairShortestPath();
		
		shortestPath.floydWarshall(graph);
	}
	
	public void floydWarshall(int[][] graph)
	{
		int[][] dist = new int[V][V];
		int i, j, k;
		
		// Initialize the solution matrix same as graph matrix
		// and we will update considering each as an intermediate node
		for(int index = 0; index < graph.length; index++ )
		{
			System.arraycopy(graph[index], 0, dist[index], 0, graph[index].length);
		}
		
		// Arrays.stream(dist).forEach(m -> System.out.println(Arrays.toString(m))); 
		
		// Loop for each vertex as an intermediate vertex
		for(k =0; k < V; k++)
		{
			// Pick all the vertices as src one by one
			for(i = 0; i < V; i++)
			{
				// Pick all the vertices as destination one by one
				for(j = 0; j < V; j++)
				{
					if(dist[i][k] != INF && dist[k][j] != INF
							&& dist[i][j] > dist[i][k] + dist[k][j])
					{
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
		// Print solution
		printSolution(dist);
	}
	
	private void printSolution(int[][] dist)
	{

        System.out.println("The following matrix shows the shortest "+ 
                         "distances between every pair of vertices"); 
        for (int i=0; i<V; ++i) 
        { 
            for (int j=0; j<V; ++j) 
            { 
                if (dist[i][j]==INF) 
                    System.out.print("INF "); 
                else
                    System.out.print(dist[i][j]+"   "); 
            } 
            System.out.println(); 
        } 
	}

}
