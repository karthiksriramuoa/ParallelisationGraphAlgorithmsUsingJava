package threading;

import graph.BasicSimpleEdge;
import graph.BasicUndirectedGraph;
import graph.BasicVertex;
import pu.pi.ParIterator;

public class WorkerThread extends Thread {
	private static BasicUndirectedGraph<BasicVertex, BasicSimpleEdge<BasicVertex>> graph = new BasicUndirectedGraph("Graph");
	
	private ParIterator<BasicVertex> pi = null;
	private int id = -1;
	
	public static void setGraph(BasicUndirectedGraph inputGraph) {
		graph = inputGraph;
	}
	
	public WorkerThread(int id, ParIterator<BasicVertex> pi2) {
		this.id = id;
		this.pi = pi2;
	}

	public void run() {
		while (pi.hasNext()) {
			BasicVertex element = pi.next();
			for(int i = 0; i < DijkstraThreading.getNumReapeats(); i++ ) {
				DijkstraThreading.calculateShortestPathFromSource(graph, element);
			}
		}
	}
	
	
}
