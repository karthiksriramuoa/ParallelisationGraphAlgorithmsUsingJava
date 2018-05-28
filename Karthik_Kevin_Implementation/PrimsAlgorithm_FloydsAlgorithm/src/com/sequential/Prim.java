package com.sequential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Prim {
    // Number of vertices in the graph
    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(List<Integer> keyList, List<Boolean> mstSetList){
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
        Iterator<Integer> iterator = keyList.iterator();
        int v = 0;
        while(iterator.hasNext()) {
        	iterator.next();
        	if(mstSetList.get(v) == Boolean.FALSE && keyList.get(v) < min) {
        		min = keyList.get(v);
        		min_index = v;
        	}
        	v++;
        }
        return min_index;
    }
 
    void printMST(List parentList, int n, List<List> graphList){
        System.out.println("Edge   Weight");
        for (int i = 1; i < n; i++)
        	System.out.println(parentList.get(i)+" - "+ i+"    "+
                    graphList.get(i).get((int) parentList.get(i)));
    }
    
    void primMST(List<List>graphList, int vertices){
    	Integer[] integers = new Integer[vertices];
    	Boolean[] booleans = new Boolean[vertices];
    	Arrays.fill(integers, 0);
    	Arrays.fill(booleans, Boolean.FALSE);
    	List<Integer> parentList = Arrays.asList(integers);
    	List<Integer> keyList = new ArrayList<>();
    	keyList.addAll(parentList);
    	
        List<Boolean> mstSetList =  Arrays.asList(booleans);
        Iterator<Boolean> iterator = mstSetList.iterator();
        int idx=0;
        while(iterator.hasNext()) {
        	iterator.next();
        	keyList.set(idx, Integer.MAX_VALUE);
        	idx++;
        }
        
        keyList.set(0, 0);
        parentList.set(0, -1);
        int count = 0;
        iterator = mstSetList.iterator();
        while(iterator.hasNext() && count<vertices-1) {
        	iterator.next();
        	int u = minKey(keyList, mstSetList);
        	mstSetList.set(u, Boolean.TRUE);
        	int v = 0;
        	Iterator<Integer> iterator2 = keyList.iterator();
        	while(iterator2.hasNext()) {
        		iterator2.next();
        		if ((int)graphList.get(u).get(v) !=0 && mstSetList.get(v) == Boolean.FALSE &&
            			(int)graphList.get(u).get(v) <  keyList.get(v)){
        		parentList.set(v, u);
        		keyList.set(v, (int)graphList.get(u).get(v));
        		}
        		v++;
        	}
        	count++;
        }
        
        // print the constructed MST
        printMST(parentList, vertices, graphList);
    }
 
    public static void main (String[] args)
    {
    	int numVertices = 0;
    	Scanner scanner = null;
    	List<List> graphList = null;
    	Prim t;
    	try {
    		t = new Prim();
    		scanner = new Scanner(System.in);
    		System.out.println("Please enter the number of vertices");
    		numVertices = scanner.nextInt();
    		graphList = new ArrayList<>();
        	for(int i=0; i<numVertices; i++) {
        		List<Integer> elements1 = new ArrayList<>();
        		for(int j=0; j<numVertices; j++) {
        			Double d = Math.floor(Math.random()*10);
        			elements1.add(d.intValue());
        		}
        		graphList.add(elements1);
        		elements1 = null;
        		
        	}
        	long startTime = System.nanoTime();
        	t.primMST(graphList, numVertices);
        	long endTime   = System.nanoTime();
        	long totalTime = endTime - startTime;
        	double milliseconds = (double)totalTime / 1000000.0;
        	System.out.println(milliseconds);
		} catch (Exception e) {
			System.out.println("Please enter a number");
			// TODO: handle exception
		}
    	
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          
    	
        int graph[][] = new int[][] {{0, 2, 0, 6, 0},
                                    {2, 0, 3, 8, 5},
                                    {0, 3, 0, 0, 7},
                                    {6, 8, 0, 0, 9},
                                    {0, 5, 7, 9, 0},
                                   };*/
 
        // Print the solution
    	
        
    }
}
