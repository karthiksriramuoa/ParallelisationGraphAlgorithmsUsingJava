package com.sequential;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FloydWarshall
{
    final static int INF = 99999;
 
    void floydWarshall(List<List<Integer>> graph, int vertices)
    {
        List<List<Integer>> dist = new ArrayList<List<Integer>>();
        int i, j, k;
 
        for (i = 0; i < vertices; i++)
            for (j = 0; j < vertices; j++)
                dist.add(graph.get(i)); 
 
        for (k = 0; k < vertices; k++)
        {
            for (i = 0; i < vertices; i++)
            {
                for (j = 0; j < vertices; j++)
                {
                    if (dist.get(i).get(k) + dist.get(k).get(j) < dist.get(i).get(j))
                    	dist.get(i).set(j, dist.get(i).get(k) + dist.get(k).get(j));
                }
            }
        }
 
        // Print the shortest distance matrix
        printSolution(dist, vertices);
    }
    
    
 
   void printSolution(List<List<Integer>> dist, int vertices)
    {
        for (int i=0; i<vertices; ++i)
        {
            for (int j=0; j<vertices; ++j)
            {
                if (dist.get(i).get(j)==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist.get(i).get(j)+"   ");
            }
            System.out.println();
        }
    }
    
    public static void main (String[] args)
    {
        /*int graph[][] = { {0,   5,  INF, 10},
                          {INF, 0,   3, INF},
                          {INF, INF, 0,   1},
                          {INF, INF, INF, 0}
                        };*/
    	try {
        FloydWarshall a = new FloydWarshall();
        Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the number of vertices");
		int numVertices = scanner.nextInt();
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		Double d = 0.0;
    	for(int i=0; i<numVertices; i++) {
    		List<Integer> elements1 = new ArrayList<Integer>();
    		for(int j=0; j<numVertices; j++) {
    			if(i==0 || i==3 || i==6)
    				if(j==0 || j==3 || j==6)
    				elements1.add(INF);
    			else
	    			d = Math.floor(Math.random()*10);
	    			elements1.add(d.intValue());
    		}
    		graph.add(elements1);
    		elements1 = null;
    		
    	}
        
        long startTime = System.nanoTime();
        a.floydWarshall(graph, numVertices);
    	long endTime   = System.nanoTime();
    	long totalTime = endTime - startTime;
    	double milliseconds = (double)totalTime / 1000000.0;
    	System.out.println(milliseconds);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
}