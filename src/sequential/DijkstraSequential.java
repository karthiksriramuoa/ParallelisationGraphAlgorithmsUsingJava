package sequential;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import graph.*;

public class DijkstraSequential {	
	
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
	
	//Calculates the Minimum Spanning Tree (MST) using Dijkstra's Graphing Algorithm
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

	public static void main(String[] args) {
		BasicUndirectedGraph<BasicVertex, BasicSimpleEdge<BasicVertex>> graph = new BasicUndirectedGraph("Graph");
		
		BasicVertex vertexA = new BasicVertex("A");
		graph.add(vertexA);
		BasicVertex vertexB = new BasicVertex("B");
		graph.add(vertexB);
		
		BasicSimpleEdge edge1 = new BasicSimpleEdge("edge1", vertexA, vertexB, false);
		edge1.setWeight(10);
		
		graph.add(edge1);
		
		graph = createGraph(graph, vertexA, vertexB);
		
		Iterator<BasicVertex> temp = graph.verticesIterator();
		
		//find all Minimum Spanning Trees (MST) for all vertices
		while(temp.hasNext()) {
			calculateShortestPathFromSource(graph, temp.next());		
		}		
	}
	
	//Creates a small basic graph for testing
	public static BasicUndirectedGraph createGraph(BasicUndirectedGraph graph, BasicVertex vertexA,  BasicVertex vertexB) {

		BasicVertex vertexC = new BasicVertex("C");
		graph.add(vertexC);
		BasicVertex vertexD = new BasicVertex("D");
		graph.add(vertexD);
		BasicVertex vertexE = new BasicVertex("E");
		graph.add(vertexE);
		BasicVertex vertexF = new BasicVertex("F");
		graph.add(vertexF);
		
		BasicSimpleEdge edge2 = new BasicSimpleEdge("edge2", vertexA, vertexC, false);
		edge2.setWeight(15);
		//edge2.
		BasicSimpleEdge edge3 = new BasicSimpleEdge("edge3", vertexB, vertexD, false);
		edge3.setWeight(12);
		BasicSimpleEdge edge4 = new BasicSimpleEdge("edge4", vertexB, vertexF, false);
		edge4.setWeight(15);
		BasicSimpleEdge edge5 = new BasicSimpleEdge("edge5", vertexC, vertexE, false);
		edge5.setWeight(10);
		BasicSimpleEdge edge6 = new BasicSimpleEdge("edge6", vertexD, vertexE, false);
		edge6.setWeight(2);
		BasicSimpleEdge edge7 = new BasicSimpleEdge("edge7", vertexD, vertexF, false);
		edge7.setWeight(1);
		BasicSimpleEdge edge8 = new BasicSimpleEdge("edge8", vertexF, vertexE, false);
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
