package projeto_ed.Game;

import projeto_ed.Queues.LinkedQueue;

import java.util.Iterator;

public class BotShortestEdge extends Bot implements IBot{
    private LinkedQueue<Vertice> targetQueue;
    public BotShortestEdge(String botName, Equipa equipa) {
        super(botName, equipa);
    }

    @Override
    public void createRout(Mapa map,Vertice startVertex, Vertice flag) {
        for (Iterator<Vertice> it = map.weightedShortestPathIterator(startVertex, flag); it.hasNext(); ) {
            Vertice vertice = it.next();
            targetQueue.enqueue(vertice);
        }
        targetQueue.dequeue();
    }

    @Override
    public void play() {

    }
}
