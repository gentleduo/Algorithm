package org.duo.master.chapter016;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {

    // 点的集合
    public HashMap<Integer, Node> nodes;
    // 边的集合
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
