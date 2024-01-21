package projeto_ed.Game;

import projeto_ed.Graphs.Network;
import projeto_ed.Heaps.PriorityQueue;
import projeto_ed.Lists.DoublyUnorderedLinkedList;
import projeto_ed.Queues.LinkedQueue;

import java.util.Iterator;
import java.util.Random;

/**
 * The Mapa class represents a map with vertices and adjacency matrix.
 * It allows creating a map with a specified number of vertices.
 */
public class Mapa extends Network<Vertice> {

    /**
     * Constructs a Mapa with the specified number of vertices.
     *
     * @param num The number of vertices in the map.
     */
    public Mapa(int num){
        this.adjMatrix = new double[num][num];
        this.vertices =  new Vertice[num];
        this.numVertices = 0;
    }

    /**
     * Retrieves the Vertice object at the specified position in the map.
     *
     * @param position The position of the desired Vertice (starting from 1).
     * @return The Vertice object at the specified position.
     * @throws IllegalArgumentException If the specified position is invalid.
     */
    public Vertice getVertice(int position) {
        if (position >= 1 && position <= numVertices) {
            return vertices[position - 1];
        } else {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    /**
     * G
     * @return
     */
    public Vertice[] getVertices() {
        return vertices;
    }

    /**
     *
     * @return
     */
    public double[][] getAdjMatrix() {
        return adjMatrix;
    }

    /**
     * Generates a random directed complete graph with a specified coverage rate.
     * The method populates the adjacency matrix with random distances between vertices,
     * ensuring that the graph remains directed and complete until it is connected.
     *
     * @param taxaCobertura The coverage rate indicating the likelihood of creating an edge between vertices (0 to 100).
     */
    public void gerarGrafoCompletoAleatorioDirecionado(int taxaCobertura) {
        Random random = new Random();
        int maxArestas = numVertices * (numVertices - 1);
        int numArestas = 0;

        do {
            resetGraph();

            for (int verticeOrigem = 0; verticeOrigem < numVertices; verticeOrigem++) {
                for (int verticeDestino = 0; verticeDestino < numVertices; verticeDestino++) {
                    if (verticeOrigem != verticeDestino && numArestas < maxArestas && random.nextInt(100) < taxaCobertura) {
                        int distance = gerarDistanciaAleatoria();
                        adjMatrix[verticeOrigem][verticeDestino] = distance;
                        numArestas++;
                    }
                }
            }
        } while (!isConnected());
    }

    /**
     * Generates a random undirected complete graph with a specified coverage rate.
     * The method populates the adjacency matrix with random distances between vertices,
     * ensuring that the graph remains undirected and complete until it is connected.
     *
     * @param taxaCobertura The coverage rate indicating the likelihood of creating an edge between vertices (0 to 100).
     */
    public void gerarGrafoCompletoAleatorioNaoDirecionado(int taxaCobertura) {
        Random random = new Random();

        do {
            resetGraph();

            int maxArestas = (numVertices * (numVertices - 1)) / 2;
            int numArestas = 0;

            for (int verticeOrigem = 0; verticeOrigem < numVertices - 1; verticeOrigem++) {
                for (int verticeDestino = verticeOrigem + 1; verticeDestino < numVertices; verticeDestino++) {
                    if (numArestas < maxArestas && random.nextInt(100) < taxaCobertura) {
                        int distance = gerarDistanciaAleatoria();
                        adjMatrix[verticeOrigem][verticeDestino] = distance;
                        adjMatrix[verticeDestino][verticeOrigem] = distance;
                        numArestas++;
                    }
                }
            }
        } while (!isConnected());
    }

    /**
     * Resets the graph by initializing the adjacency matrix with zero distances.
     * All edges in the graph are removed after calling this method.
     */
    private void resetGraph() {
        for (int i = 0; i < numVertices; i++) {
            for(int j=0; j<numVertices; j++){
                adjMatrix[i][j] = 0;
            }
        }
    }

    private int gerarDistanciaAleatoria() {
        Random random = new Random();
        return (int)(1 + random.nextDouble() * 14);
    }

    public void printMapa() {
        System.out.println("MAPA:");

        int verticesPorLinha = 5;
        int contador = 1;

        for (int i = 0; i < numVertices; i++) {
            Vertice vertice = vertices[i];


            System.out.print(contador);

            // Imprime o estado do vértice (ocupado ou desocupado)
            if (vertice.isOcuped()) {
                System.out.print("[" + vertice.getBot().getNome() + "]" + "\t\t");
            } else if(vertice.isHasFlag1()) {
                System.out.print("[F1]" + "\t\t");
            }else if(vertice.isHasFlag2()) {
                System.out.print("[F2]" + "\t\t");
            }else{
                System.out.print("[  ]" + "\t\t");
            }

            // Adiciona uma quebra de linha após o número especificado de vértices por linha
            if (contador % verticesPorLinha == 0) {
                System.out.println();
            }

            contador++;
        }
    }

    public void printArestas() {
        System.out.println("ARESTAS:");

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                double peso = adjMatrix[i][j];
                if (peso > 0) {
                    System.out.println("Aresta " + (i + 1) + " - " + (j + 1) + ", Peso: " + peso);
                }
            }
        }
    }

    public Iterator<Vertice> weightedShortestPathIterator(Vertice startVertex, Vertice targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            throw new IllegalArgumentException("Invalid start or target vertex");
        }

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        DoublyUnorderedLinkedList<Vertice> resultList = new DoublyUnorderedLinkedList<>();
        boolean[] visited = new boolean[numVertices];
        int[] previousVertices = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
            previousVertices[i] = -1;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            int currentVertex = traversalQueue.dequeue();

            if (currentVertex == targetIndex) {
                int backtrackVertex = targetIndex;
                while (backtrackVertex != -1) {
                    resultList.addToFront(vertices[backtrackVertex]);
                    backtrackVertex = previousVertices[backtrackVertex];
                }

                return resultList.iterator();
            }

            for (int adjacentVertex = 0; adjacentVertex < numVertices; adjacentVertex++) {
                if (adjMatrix[currentVertex][adjacentVertex] > 0 && !visited[adjacentVertex]) {
                    traversalQueue.enqueue(adjacentVertex);
                    visited[adjacentVertex] = true;
                    previousVertices[adjacentVertex] = currentVertex;
                }
            }
        }
        return resultList.iterator();
    }



    public Iterator<Vertice> treeIterator(Vertice startVertex, Vertice targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            throw new IllegalArgumentException("Invalid start or target vertex");
        }

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        boolean[] visited = new boolean[numVertices];
        int[] parent = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
            parent[i] = -1;
        }

        minHeap.addElement(new Edge(startIndex, startIndex, 0), 0);

        while (!minHeap.isEmpty()) {
            Edge currentEdge = minHeap.removeNext();
            int currentVertex = currentEdge.destination;

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;

            if (currentEdge.source != currentEdge.destination) {
                parent[currentVertex] = currentEdge.source;
            }

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (getAdjMatrix()[currentVertex][neighbor] > 0 && !visited[neighbor]) {
                    Edge newEdge = new Edge(currentVertex, neighbor, (int) getAdjMatrix()[currentVertex][neighbor]);
                    minHeap.addElement(newEdge, newEdge.weight);
                }
            }
        }

        DoublyUnorderedLinkedList<Vertice> resultList = new DoublyUnorderedLinkedList<>();
        int currentVertex = targetIndex;

        while (currentVertex != -1) {
            resultList.addToFront(vertices[currentVertex]);
            currentVertex = parent[currentVertex];
        }

        return resultList.iterator();
    }


    public class Edge implements Comparable<Edge> {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

}
