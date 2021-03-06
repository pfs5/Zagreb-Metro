package hr.fer.zgmetro.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import hr.fer.zgmetro.model.Graph;
import hr.fer.zgmetro.model.Node;
import hr.fer.zgmetro.model.Path;

public class TripCounter {

	// matro 3 je poseban slucaj metro4
	public static List<Path> calculate(Graph graph, String start, String end, int stops) {

		List<Path> paths = new LinkedList<>();

		for (LinkedList<String> l : recursion(graph, start, end, stops)) {
			paths.add(new Path(l));
		}

		return paths;
	}

	public static LinkedList<LinkedList<String>> recursion(Graph graph, String state, String goal, int n) {
		LinkedList<LinkedList<String>> ret = new LinkedList<>();

		if (n == 0) {
			if (state.equals(goal)) {
				LinkedList<String> l = new LinkedList<>();
				l.add(state);
				ret.add(l);
				return ret;
			}
			return ret;
		}

		Node node = graph.getNodeByName(state);

		Map<Node, Integer> succ = node.getSuccessors();

		for (Entry<Node, Integer> e : succ.entrySet()) {
			LinkedList<LinkedList<String>> tmp = recursion(graph, e.getKey().getName(), goal, n - 1);
			if (tmp.isEmpty())
				continue;
			for (LinkedList<String> l : tmp)
				l.addFirst(state);
			ret.addAll(tmp);
		}

		return ret;
	}
}
