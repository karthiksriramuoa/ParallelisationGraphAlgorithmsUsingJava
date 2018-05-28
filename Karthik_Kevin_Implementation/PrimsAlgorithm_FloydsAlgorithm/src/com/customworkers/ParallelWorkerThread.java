package com.customworkers;
import java.util.Iterator;
import java.util.List;

import pi.ParIterator;
import pi.ParIteratorFactory;

public class ParallelWorkerThread extends Thread {
		
	private ParIterator<Boolean> pi = null;
	private int id = -1;
	private int V;
	private List<Integer>keyList;
	private List<Boolean>mstSetList;
	private List<List> graphList;
	private List parentList;
	private int threadCount = 1;

	public ParallelWorkerThread(int id, ParIterator<Boolean> pi, int v, List<Integer>keyList, List<Boolean>mstSetList, List<List> graphList, List parentList) {
		this.id = id;
		this.pi = pi;
		this.V = v;
		this.keyList = keyList;
		this.mstSetList = mstSetList;
		this.graphList = graphList;
		this.parentList = parentList;
	}

	public void run() {
		
		int count = 0;
		Thread[] threadPool = new ParallelWorkerThreadInner[threadCount];
	        while(pi.hasNext() && count<V-1) {
	        	boolean element = pi.next();
	        	System.out.println("Outer Thread " + id + " got element: " + element);
	        	int u = minKey(keyList, mstSetList);
	        	if(-1==u)
	        		u = 0;
	        	mstSetList.set(u, Boolean.TRUE);
	        	ParIterator<Integer> iterator2 = ParIteratorFactory.createParIterator(keyList, threadCount);
	        	for (int i = 0; i < threadCount; i++) {
	        	    threadPool[i] = new ParallelWorkerThreadInner(i, iterator2, u, keyList, mstSetList, graphList, parentList);
	        	    threadPool[i].start();
	        	}
	        	count++;	
	        	// ... Main thread may compute other (independant) tasks

	        	// Main thread waits for worker threads to complete
	        }
	        
        for (int i = 0; i < threadCount; i++) {
    	    try {
    	    	threadPool[i].join();
    	    } catch(InterruptedException e) {
    	    	e.printStackTrace();
    	    }
    	}
        
		System.out.println("Outer Thread "+id+" has finished.");
	}
	
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
}
