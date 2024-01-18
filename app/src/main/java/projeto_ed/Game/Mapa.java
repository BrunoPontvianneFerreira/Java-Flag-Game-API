package projeto_ed.Game;

import projeto_ed.Graphs.Network;

import java.util.Random;

public class Mapa extends Network<Vertice> {

    public Mapa(int num){
        this.adjMatrix = new double[num][num];
        this.vertices =  new Vertice[num];
    }

    public Vertice getVertice(int position) {
        if (position >= 1 && position <= numVertices) {
            return vertices[position - 1];
        } else {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    public void gerarGrafoCompletoAleatorioDirecionado(int taxaCobertura) {
        do{Random random = new Random();

        // Garante que o grafo é completo, então o número de arestas será (N * (N - 1))
        int maxArestas = numVertices * (numVertices - 1);
        int numArestas = 0;

        for (int verticeOrigem = 0; verticeOrigem < numVertices; verticeOrigem++) {
            for (int verticeDestino = 0; verticeDestino < numVertices; verticeDestino++) {
                if (verticeOrigem != verticeDestino && numArestas < maxArestas && random.nextInt(100) < taxaCobertura) {
                    // Adiciona uma aresta entre os vértices se o valor aleatório estiver abaixo da taxa de cobertura
                    int distance = gerarDistanciaAleatoria();
                    adjMatrix[verticeOrigem][verticeDestino] = distance;
                    numArestas++;
                }
            }
        }
    }while(!isConnected());
    }

    public void gerarGrafoCompletoAleatorioNaoDirecionado(int taxaCobertura) {
        do {
            Random random = new Random();

            // Garante que o grafo é completo, então o número de arestas será (N * (N - 1)) / 2
            int maxArestas = (numVertices * (numVertices - 1)) / 2;
            int numArestas = 0;

            for (int verticeOrigem = 0; verticeOrigem < numVertices - 1; verticeOrigem++) {
                for (int verticeDestino = verticeOrigem + 1; verticeDestino < numVertices; verticeDestino++) {
                    if (numArestas < maxArestas && random.nextInt(100) < taxaCobertura) {
                        // Adiciona uma aresta entre os vértices se o valor aleatório estiver abaixo da taxa de cobertura
                        int distance = gerarDistanciaAleatoria();
                        adjMatrix[verticeOrigem][verticeDestino] = distance;
                        adjMatrix[verticeDestino][verticeOrigem] = distance; // Para grafo não direcionado
                        numArestas++;
                    }
                }
            }
        }while(!isConnected());
    }

    private int gerarDistanciaAleatoria() {
        Random random = new Random();
        return (int)(1 + random.nextDouble() * 14); // Números aleatórios entre 1 e 15
    }

    public void printMapa() {
        System.out.println("MAPA:");

        int verticesPorLinha = 5;
        int contador = 1;

        for (int i = 0; i < numVertices; i++) {
            Vertice vertice = vertices[i];

            // Imprime o número do vértice
            System.out.print(contador);

            // Imprime o estado do vértice (ocupado ou desocupado)
            if (vertice.isOcuped()) {
                System.out.print("[" + vertice.getBot().getNome() + "]");
            } else if(vertice.isHasFlag1()) {
                System.out.print("[F1]" + "\t\t");
            }else if(vertice.isHasFlag2()) {
                System.out.print("[F2]" + "\t\t");
            }else{
                System.out.print("[   ]" + "\t\t");
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


}
