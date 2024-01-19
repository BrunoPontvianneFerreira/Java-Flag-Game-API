/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package projeto_ed;

import projeto_ed.Game.Mapa;
import projeto_ed.Game.Vertice;
import projeto_ed.Graphs.*;

import java.util.Iterator;

public class App {

    public static void main(String[] args) {

        Network<String> graph = new Network<>();
        Mapa mapa = new Mapa(200);
        for (int i = 1; i <= 200; i++) {
            Vertice vertice = new Vertice();
            mapa.addVertex(vertice);
        }

        mapa.gerarGrafoCompletoAleatorioDirecionado(5);
        System.out.println(mapa.isConnected());

        // Imprimir o mapa
        mapa.printMapa();
        mapa.printArestas();

        Vertice bandeira1 = mapa.getVertice(5);
        bandeira1.setHasFlag1(true);
        Vertice bandeira2 = mapa.getVertice(13);
        bandeira2.setHasFlag2(true);

        mapa.printMapa();
        mapa.printArestas();

        double custo = mapa.shortestPathWeight(bandeira1, bandeira2);

        System.out.println("Shortest path weight from " + "bandeira 1" + " to " + "bandeira 2" + ": " + custo);
        Iterator<Vertice> shortestPath = mapa.iteratorShortestPath(bandeira1, bandeira2);

        // Imprima o caminho mais curto
        System.out.println("Caminho mais curto de bandeira 1 para bandeira 2:");

        while (shortestPath.hasNext()) {
            System.out.print(shortestPath.next().getNum());

            if (shortestPath.hasNext()) {
                System.out.print(" -> ");
            }
        }

        Iterator<Vertice> shortestPathArestas = mapa.weightedShortestPathIterator(bandeira1, bandeira2);

        // Imprima o caminho mais curto
        System.out.println("\nCaminho mais curto de bandeira 1 para bandeira 2 por arestas:");

        while (shortestPathArestas.hasNext()) {
            System.out.print(shortestPathArestas.next().getNum());

            if (shortestPathArestas.hasNext()) {
                System.out.print(" -> ");
            }
        }


        // Adicione vértices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");


        // Adicione arestas
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 2.0);
        graph.addEdge("A", "D", 1.0);
        graph.addEdge("A", "E", 4.0);
        graph.addEdge("B", "F", 2.0);
        graph.addEdge("F", "H", 1.0);
        graph.addEdge("D", "G", 7.0);
        graph.addEdge("G", "I", 8.0);
        graph.addEdge("H", "I", 1.0);
        graph.addEdge("B", "A", 1.0);
        graph.addEdge("C", "A", 2.0);
        graph.addEdge("D", "A", 1.0);
        graph.addEdge("E", "A", 4.0);
        graph.addEdge("F", "B", 2.0);
        graph.addEdge("H", "F", 1.0);
        graph.addEdge("G", "D", 7.0);
        graph.addEdge("I", "G", 8.0);
        graph.addEdge("I", "H", 1.0);
        graph.addEdge("G", "J", 4.0);
        graph.addEdge("J", "G", 4.0);
        graph.addEdge("E", "J", 5.0);
        graph.addEdge("J", "E", 5.0);
        graph.addEdge("I", "J", 1.0);
        graph.addEdge("J", "I", 1.0);
        System.out.println(graph.isConnected());
        double shortestPathWeight = graph.shortestPathWeight("A", "J");

        System.out.println("Shortest path weight from " + "A" + " to " + "I" + ": " + shortestPathWeight);
        Iterator<String> shortestPath2 = graph.iteratorShortestPath("A", "J");

        // Imprima o caminho mais curto
        System.out.println("Caminho mais curto de D para I:");

        while (shortestPath2.hasNext()) {
            System.out.print(shortestPath2.next());

            if (shortestPath2.hasNext()) {
                System.out.print(" -> ");
            }
        }

        // Imprima o grafo
        System.out.println("\nGraph:");

        // Execute BFS a partir de um vértice
        System.out.println("DFS from A:");
        Iterator<String> bfsIterator = graph.iteratorDFS("A");
        while (bfsIterator.hasNext()) {
            System.out.print(bfsIterator.next() + " ");
        }
        System.out.println();

        System.out.println("BFS from A:");
        Iterator<String> bfsIterator2 = graph.iteratorBFS("A");
        while (bfsIterator2.hasNext()) {
            System.out.print(bfsIterator2.next() + " ");
        }
        System.out.println();

        System.out.println("BFS from C:");
        Iterator<String> bfsIterator3 = graph.iteratorDFS("C");
        while (bfsIterator3.hasNext()) {
            System.out.print(bfsIterator3.next() + " ");
        }
        System.out.println();

        // Execute DFS a partir de um vértice
        System.out.println("DFS from D:");
        Iterator<String> dfsIterator4 = graph.iteratorBFS("D");
        while (dfsIterator4.hasNext()) {
            System.out.print(dfsIterator4.next() + " ");
        }
        System.out.println();

        // Remova um vértice
        graph.removeVertex("B");

        // Imprima o grafo após a remoção
        System.out.println("Graph after removing vertex B:");

        System.out.println("--------------------------------------------------------------");
    }
}
