package org.duo.master.chapter016;

public class Edge {

	// 权重
	public int weight;
	// 起始节点
	public Node from;
	// 到达节点
	public Node to;

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
}
