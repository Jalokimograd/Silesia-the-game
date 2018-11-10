package algorithm;

import unit.*;
import java.util.*;

public class AStar {
	
	public static final int MAX_SEARCH_COUNT = 1000;
	
	public static class PriorityList<T extends Comparable<T>> extends
	LinkedList<T> {

		private static final long serialVersionUID = 3527382178368866145L;

		public boolean add(T object) {
			for (int i = 0; i < size(); i++) {
				if (object.compareTo(get(i)) <= 0) {
					add(i, object);
					return true;
				}
			}
			addLast(object);
			return true;
		}
	}
	//======================================================================================================================
	private static LinkedList<AStarNode> constructPath(AStarNode node) {
		LinkedList<AStarNode> path = new LinkedList<AStarNode>();
		while (node.pathParent != null) {
			path.addFirst(node);
			node = node.pathParent;
		}
		return path;
	}
	//======================================================================================================================
	public static LinkedList<AStarNode> findPath(AStarNode startNode, AStarNode goalNode) {
		int count = 0;
		if (startNode == null || goalNode == null) {
			return new LinkedList<AStarNode>();
		}

		PriorityList<AStarNode> openList = new PriorityList<AStarNode>();
		LinkedList<AStarNode> closedList = new LinkedList<AStarNode>();

		startNode.costFromStart = 0;
		startNode.estimatedCostToGoal = startNode.getEstimatedCost(goalNode);
		startNode.pathParent = null;
		openList.add(startNode);

		while (!openList.isEmpty()) {
			AStarNode node = openList.removeFirst();	//pobierany jest node o najmniejszym koszcie
			if (node == goalNode) {  // skonstruuj œcie¿kê od startu do celu
				return constructPath(node);
			}

			 if (++count > MAX_SEARCH_COUNT) return new LinkedList();

			ArrayList<AStarNode> neighbors = node.getNeighbors();
			
			for (int i = 0; i < neighbors.size(); ++i) {
				AStarNode neighborNode = (AStarNode) neighbors.get(i);  
				boolean isOpen = openList.contains(neighborNode);
				boolean isClosed = closedList.contains(neighborNode);
				int costFromStart = node.costFromStart + node.getCostTo(neighborNode);

				// sprawdŸ, czy wêze³ s¹siaduj¹cy nie by³ ju¿
				// trawersowany lub czy nie zosta³a w³aœnie
				// znaleziona krótsza œcie¿ka do niego.
				
				if ((!isOpen && !isClosed) || costFromStart < neighborNode.costFromStart) {
					neighborNode.pathParent = node;
					neighborNode.costFromStart = costFromStart;
					neighborNode.estimatedCostToGoal = neighborNode.getEstimatedCost(goalNode);
					if (isClosed) {
						closedList.remove(neighborNode);
					}
					if (!isOpen) {
						openList.add(neighborNode);
					}
				}
			}
			closedList.add(node);
		}
		// nie znaleziono œcie¿ki
		//System.out.println("Nie znalazlem sciezki:" + count);
		return new LinkedList<AStarNode>();
	}
}
