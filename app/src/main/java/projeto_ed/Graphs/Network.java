package projeto_ed.Graphs;

import java.util.Iterator;


import projeto_ed.Lists.DoublyUnorderedLinkedList;
import projeto_ed.Queues.LinkedQueue;

/**
 * Network represents an adjacency matrix implementation of a weighted graph.
 *
 * @param <T> the type of elements stored in the vertices of the network
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {

    /**
     * Inserts an edge with weight between two vertices of the network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight of the edge
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
        }
    }

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     */

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        int startIndex = getIndex(vertex1);
        int targetIndex = getIndex(vertex2);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            throw new IllegalArgumentException("Invalid start or target vertex");
        }

        double[] distances = new double[numVertices];
        boolean[] tight = new boolean[numVertices];
        int[] previousVertices = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            distances[i] = Double.POSITIVE_INFINITY;
            tight[i] = false;
            previousVertices[i] = -1;
            visited[i] = false;
        }

        distances[startIndex] = 0;

        while (true) {
            int u = -1;
            double minDistance = Double.POSITIVE_INFINITY;

            for (int i = 0; i < numVertices; i++) {
                if (!tight[i] && distances[i] < minDistance) {
                    u = i;
                    minDistance = distances[i];
                }
            }

            if (u == -1) {
                break;
            }

            tight[u] = true;

            for (int z = 0; z < numVertices; z++) {
                if (adjMatrix[u][z] > 0 && !visited[z]) {
                    double newDistance = distances[u] + adjMatrix[u][z];

                    if (newDistance < distances[z]) {
                        distances[z] = newDistance;
                        previousVertices[z] = u;
                    }
                }
            }
        }

        return distances[targetIndex];
    }
}
