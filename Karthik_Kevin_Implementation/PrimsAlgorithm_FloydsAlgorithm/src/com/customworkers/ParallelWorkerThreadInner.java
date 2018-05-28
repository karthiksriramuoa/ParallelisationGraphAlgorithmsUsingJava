package com.customworkers;

import java.util.List;

import pi.ParIterator;

public class ParallelWorkerThreadInner extends Thread {
	
	private ParIterator<Boolean> pi = null;
	private int id = -1;
	int u = 0;
	private List<Integer>keyList;
	private List<Boolean>mstSetList;
	private List<List> graphList;
	private List parentList;
	
	public ParallelWorkerThreadInner(int id, ParIterator pi, int u, List<Integer>keyList, List<Boolean>mstSetList, List<List> graphList, List parentList) {
		this.id = id;
		this.pi = pi;
		this.u = u;
		this.keyList = keyList;
		this.mstSetList = mstSetList;
		this.graphList = graphList;
		this.parentList = parentList;
	}
	
	@Override
	public void run() {
		
		int v= 0;
		while(pi.hasNext()) {
    		pi.next();
    		if ((int)graphList.get(u).get(v) !=0 && mstSetList.get(v) == Boolean.FALSE &&
        			(int)graphList.get(u).get(v) <  keyList.get(v)){
    		parentList.set(v, u);
    		keyList.set(v, (int)graphList.get(u).get(v));
    		}
    		v++;
    	}
	}
}
