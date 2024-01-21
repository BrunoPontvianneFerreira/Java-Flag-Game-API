package projeto_ed.Game;

import projeto_ed.Queues.LinkedQueue;

import java.util.Iterator;

public class BotShortestEdge extends Bot implements IBot{
    private LinkedQueue<Vertice> targetQueue;
    public BotShortestEdge(String botName, Equipa equipa) {
        super(botName, equipa);
        targetQueue = new LinkedQueue<>();
    }

    @Override
    public void createRout(Mapa map,Vertice startVertex, Vertice flag) {
        LinkedQueue<Vertice> rota = new LinkedQueue<>();
        this.setFlag_index(flag.getindex());
        this.setVertice_index(startVertex.getindex());
        for (Iterator<Vertice> it = map.weightedShortestPathIterator(startVertex, flag); it.hasNext(); ) {
            Vertice vertice = it.next();
            rota.enqueue(vertice);
        }
        rota.dequeue();
        this.setRota(rota);
    }

    @Override
    public void play(Mapa map) {
        if (getRota() == null) {
            createRout(map, map.getVertice(this.getVertice_index()), map.getVertice(this.getFlag_index()));
        }
        Vertice vertice =  getRota().first();
        if(!vertice.isOcuped()){
            this.setLast_vertice_index(this.getVertice_index());
            map.getVertice(this.getVertice_index()).setBot(null);
            vertice.setBot(this);
            getRota().dequeue();
            this.setVertice_index(vertice.getindex());
        }else {
            Vertice currentVertex = map.getVertice(this.getVertice_index());
            Vertice nextVertex = findNextAvailableVertex(map, currentVertex);

            if (nextVertex != null) {
                this.setLast_vertice_index(this.getVertice_index());
                createRout(map, currentVertex, map.getVertice(this.getFlag_index()));
                map.getVertice(this.getVertice_index()).setBot(null);
                nextVertex.setBot(this);
                this.setVertice_index(nextVertex.getindex());
            }
        }
    }

    private Vertice findNextAvailableVertex(Mapa map, Vertice currentVertex) {
        int index = -1;
        double[][] matriz = map.getAdjMatrix();
        for (int i = 0; i < map.size(); i++) {
            if (matriz[currentVertex.getindex()][i] > 0 && i != this.getLast_vertice_index() && !map.getVertice(i).isOcuped()) {
                return map.getVertice(index);
            }
        }
        return null;
    }


    public LinkedQueue<Vertice> getRota() {
        return targetQueue;
    }

    public void setRota(LinkedQueue<Vertice> rota) {
        this.targetQueue = rota;
    }
}
