package threading;

import java.util.*;
import graph.BasicSimpleEdge;
import graph.BasicUndirectedGraph;
import graph.BasicVertex;
import graph.Edge;
import graph.Vertex;
import pu.pi.ParIterator;
import pu.pi.ParIteratorFactory;
import sequential.DijkstraSequential;

public class DijkstraThreading {
	static int numRepeats;
	static int numThreads;
	
	public static int getNumThreads() {
		return numThreads;
	}
	
	public static void setNumThreads(int num) {
		numThreads = num;
	}
	
	public static int getNumReapeats() {
		return numRepeats;
	}
	public static void setNumRepeats(int num) {
		numRepeats = num;
	}
	//updates the disFromSource Map and returns it
	public static Map<Vertex, Integer> updateDisFromSource( Map<Vertex, Integer> disFromSource,  Vertex source, Vertex currentVertex, int Weight){

		//if the current value is more than the new path it is replaced
		if((disFromSource.get(source) > (Weight + disFromSource.get(currentVertex)))){
			if(disFromSource.get(currentVertex) == Integer.MAX_VALUE || disFromSource.get(currentVertex) == Integer.MIN_VALUE) {
				disFromSource.replace(source, Weight);
			}else {
				disFromSource.replace(source, (Weight + disFromSource.get(currentVertex)));
			}
		}
		return disFromSource;
	}
	
	//Calulates the Minimum Spanning Tree (MST) using Dijkstra's Graphing Algorithm
	public static void calculateShortestPathFromSource(BasicUndirectedGraph graph, Vertex source) {
		//Create Map of Distances from source
		Map<Vertex, Integer> disFromSource = new HashMap<Vertex, Integer>();
		//Create Lists of Visited and unvisited Vertexes
		ArrayList<Vertex> visitedVertex = new ArrayList<Vertex>();
		ArrayList<Vertex> unVisitedVertex = new ArrayList<Vertex>();
		
		
		//Set Distance of Source to Source to 0 and add source to visited Vertex
		disFromSource.replace(source, 0);

		//Remove Source from unvisited List
		unVisitedVertex.remove(source);
		//set the Source Vertex as the current Vertex
		Vertex currentVertex = source;
		
		//Collect all other Vertices and set them to infinite distance and unvisited List
		Iterator<Vertex> Ver = graph.verticesIterator();
		while(Ver.hasNext()) {
			Vertex temp = Ver.next();
			disFromSource.put(temp, Integer.MAX_VALUE);
			unVisitedVertex.add(temp);
		}
		
		//iterates while there is still unvisted Vertexes
		while(unVisitedVertex.size() != 0) {
			Iterator<Edge> VerEdge = graph.incidentEdgesIterator(currentVertex);
			BasicSimpleEdge currentLowest = null;
			int lowestWeight = Integer.MAX_VALUE;
			
			//Finds the Weight to travel to the adjasent Vertex and holds it 
			while(VerEdge.hasNext()) {
				BasicSimpleEdge temp = (BasicSimpleEdge) VerEdge.next();
				//checks to see if the Edge has already been checked
				if(unVisitedVertex.contains(temp.first()) || unVisitedVertex.contains(temp.second())) {
					//this finds the lowest weight of the Vertex adjasent to the currentVertex
					if (temp.weight() < lowestWeight) {
						currentLowest = temp;
						lowestWeight =  temp.weight();
					}
					//Decides if the current Vertex is the first or second and runs decides if their is a shorter path
					if(temp.first() == currentVertex) {
						disFromSource = updateDisFromSource(disFromSource, temp.second(), currentVertex, temp.weight());
					}else{
						disFromSource = updateDisFromSource(disFromSource, temp.first(), currentVertex, temp.weight());
					}
				}
				
			}
			//gets the next Vertex and updates the currentVertex
			if(currentLowest.first() == currentVertex) {
				currentVertex = currentLowest.second();
				visitedVertex.add(currentVertex);
				unVisitedVertex.remove(currentVertex);
			}else {
				currentVertex = currentLowest.first();
				visitedVertex.add(currentVertex);
				unVisitedVertex.remove(currentVertex);
			}
		}
	}
	
	
	public static void dijkstraThreading(Iterator<BasicVertex> ITVertex, int threadCount, BasicUndirectedGraph graph) {
		List<BasicVertex> elements = new ArrayList<BasicVertex>();
		
		while(ITVertex.hasNext()) {
			elements.add(ITVertex.next());
		}

		// Get a Parallel Iterator for the collection of elements
		ParIterator<BasicVertex> pi = ParIteratorFactory.createParIterator(elements, threadCount);
	 
		// Create and start a pool of worker threads
		WorkerThread.setGraph(graph);
		Thread[] threadPool = new WorkerThread[threadCount];
		for (int i = 0; i < threadCount; i++) {
		    threadPool[i] = new WorkerThread(i, pi);
		    threadPool[i].start();
		}
			
		// ... Main thread may compute other (independent) tasks
		

		// Main thread waits for worker threads to complete
		for (int i = 0; i < threadCount; i++) {
		    try {
		    	threadPool[i].join();
		    } catch(InterruptedException e) {
		    	e.printStackTrace();
		    }
		}
	}


	public static void main(String[] args) {
		
		//Allows the user to set the size and the number of threads to be used
	   	Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Please enter the number of Verticies: ");
    		setNumRepeats(scanner.nextInt());
    		System.out.println("Please enter the number of Threads: ");
    		setNumThreads(scanner.nextInt());
        } catch (Exception e) {
            System.out.println("Please enter a number");
            // TODO: handle exception
        }
		
		//Create new Graph
		BasicUndirectedGraph<BasicVertex, BasicSimpleEdge<BasicVertex>> graph = new BasicUndirectedGraph<BasicVertex, BasicSimpleEdge<BasicVertex>>("Graph");
		
		//Create and Add Vertex to the Graph
		BasicVertex vertexA = new BasicVertex("A");
		graph.add(vertexA);
		BasicVertex vertexB = new BasicVertex("B");
		graph.add(vertexB);
		
		//Create and add Edge to the Greaph
		BasicSimpleEdge<BasicVertex> edge1 = new BasicSimpleEdge<BasicVertex>("edge1", vertexA, vertexB, false);
		edge1.setWeight(10);
		graph.add(edge1);
		
		graph = createGraph(graph, vertexA, vertexB);
		
		//----------------------Threading----------------------
		//Creates iterable object
		Iterator<BasicVertex> tempThreading = graph.verticesIterator();
		//start timer
		final long startTime = System.currentTimeMillis();
		dijkstraThreading(tempThreading, numThreads, graph);
		//end timer
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time Threading: " + (endTime - startTime) );
		
		//--------------------Sequential---------------------
		//Creates iterable object
		Iterator<BasicVertex> tempSeq = graph.verticesIterator();
		//start timer
		final long startTimeSeq = System.currentTimeMillis();
		//find all Minimum Spanning Trees (MST) for all vertices
		while(tempSeq.hasNext()) {
			BasicVertex startVertex = tempSeq.next();
			for(int i = 0; i < numRepeats; i ++) {
				DijkstraSequential.calculateShortestPathFromSource(graph, startVertex);
			}
		}
		//end timer
		final long endTimeSeq = System.currentTimeMillis();
		System.out.println("Total execution time Sequntial: " + (endTimeSeq - startTimeSeq) );
	}
	
	//Creates a small basic graph for testing
	public static BasicUndirectedGraph<BasicVertex, BasicSimpleEdge<BasicVertex>> createGraph(BasicUndirectedGraph<BasicVertex, BasicSimpleEdge<BasicVertex>> graph, BasicVertex vertexA, BasicVertex vertexB) {

			BasicVertex vertexC = new BasicVertex("C");
			graph.add(vertexC);
			BasicVertex vertexD = new BasicVertex("D");
			graph.add(vertexD);
			BasicVertex vertexE = new BasicVertex("E");
			graph.add(vertexE);
			BasicVertex vertexF = new BasicVertex("F");
			graph.add(vertexF);
			
			BasicSimpleEdge<BasicVertex> edge2 = new BasicSimpleEdge<BasicVertex>("edge2", vertexA, vertexC, false);
			edge2.setWeight(15);
			//edge2.
			BasicSimpleEdge<BasicVertex> edge3 = new BasicSimpleEdge<BasicVertex>("edge3", vertexB, vertexD, false);
			edge3.setWeight(12);
			BasicSimpleEdge<BasicVertex> edge4 = new BasicSimpleEdge<BasicVertex>("edge4", vertexB, vertexF, false);
			edge4.setWeight(15);
			BasicSimpleEdge<BasicVertex> edge5 = new BasicSimpleEdge<BasicVertex>("edge5", vertexC, vertexE, false);
			edge5.setWeight(10);
			BasicSimpleEdge<BasicVertex> edge6 = new BasicSimpleEdge<BasicVertex>("edge6", vertexD, vertexE, false);
			edge6.setWeight(2);
			BasicSimpleEdge<BasicVertex> edge7 = new BasicSimpleEdge<BasicVertex>("edge7", vertexD, vertexF, false);
			edge7.setWeight(1);
			BasicSimpleEdge<BasicVertex> edge8 = new BasicSimpleEdge<BasicVertex>("edge8", vertexF, vertexE, false);
			edge8.setWeight(5);
			graph.add(edge2);
			graph.add(edge3);
			graph.add(edge4);
			graph.add(edge5);
			graph.add(edge6);
			graph.add(edge7);
			graph.add(edge8);

			return graph;
		}

}
