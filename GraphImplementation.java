import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphImplementation implements Graph {

    private int[][] graph;

    public GraphImplementation(int size) {
        this.graph = new int[size][size];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new int[size];
        }
    }

    @Override
    public void addEdge(int v1, int v2) throws Exception {
        this.graph[v1][v2] = 1;
    }

    @Override
    public List<Integer> topologicalSort() {
        int vertexNumber = this.graph.length;
        boolean[] visited = new boolean[vertexNumber];
        List<Integer> sortedList = new ArrayList<>();
        for (int i = 0; i < vertexNumber; i++) {
            boolean flag = false;
            for (int j = 0; j < vertexNumber; j++) {
                if (this.graph[i][j] == 1) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                dfs(visited, i, sortedList);
            }
        }
        Collections.reverse(sortedList);
        return sortedList;
    }

    @Override
    public List<Integer> neighbors(int vertex) throws Exception {
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (this.graph[vertex][i] == 1) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }


    private void dfs(boolean[] visited, int vertex, List<Integer> sortList) {
        if (visited[vertex]) {
            return;
        }
        visited[vertex] = true;
        List<Integer> neighbors = null;
        try {
            neighbors = neighbors(vertex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int ver : neighbors) {
            dfs(visited, ver, sortList);
        }
        sortList.add(vertex);
    }
}
