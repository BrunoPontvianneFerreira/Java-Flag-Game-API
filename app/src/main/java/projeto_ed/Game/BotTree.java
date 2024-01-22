package projeto_ed.Game;

import projeto_ed.Queues.LinkedQueue;

import java.util.Iterator;

public class BotTree extends Bot implements IBot {
    public BotTree(String botName, Team team) {
        super(botName, team);
        this.setRota(new LinkedQueue<>());
    }

    @Override
    public void createRout(Map map, Vertex startVertex, Vertex flag) {
        LinkedQueue<Vertex> rota = new LinkedQueue<>();
        this.setFlagIndex(flag.getindex());
        this.setVerticeIndex(startVertex.getindex());
        for (Iterator<Vertex> it = map.treeIterator(startVertex, flag); it.hasNext(); ) {
            Vertex vertex = it.next();
            rota.enqueue(vertex);
        }
        rota.dequeue();
        this.setRota(rota);
    }

    @Override
    public void play(Map map) {
        if (getRota() == null) {
            createRout(map, map.getVertice(this.getVerticeIndex()), map.getVertice(this.getFlagIndex()));
        }
        Vertex vertex =  getRota().first();
        if(!vertex.isOccupied()){
            this.setLastVerticeIndex(this.getVerticeIndex());
            map.getVertice(this.getVerticeIndex()).setBot(null);
            vertex.setBot(this);
            getRota().dequeue();
            this.setVerticeIndex(vertex.getindex());
        }else {
            Vertex currentVertex = map.getVertice(this.getVerticeIndex());
            Vertex nextVertex = findNextAvailableVertex(map, currentVertex);

            if (nextVertex != null) {
                this.setLastVerticeIndex(this.getVerticeIndex());
                createRout(map, nextVertex, map.getVertice(this.getFlagIndex()));
                map.getVertice(this.getVerticeIndex()).setBot(null);
                nextVertex.setBot(this);
                this.setVerticeIndex(nextVertex.getindex());
            }
        }
    }

    private Vertex findNextAvailableVertex(Map map, Vertex currentVertex) {
        double[][] matriz = map.getAdjMatrix();
        for (int i = 0; i < map.size(); i++) {
            if (matriz[currentVertex.getindex() - 1][i] > 0 && (i != this.getLastVerticeIndex()-1) && !map.getVertice(i+1).isOccupied()) {
                return map.getVertice(i + 1);
            }
        }
        return null;
    }
}
