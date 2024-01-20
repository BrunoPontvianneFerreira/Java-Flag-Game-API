package projeto_ed.Game;

import projeto_ed.Queues.LinkedQueue;

import java.util.Iterator;

public class BotShortestPath extends Bot implements IBot{
    private double routWeight;
    private LinkedQueue<Vertice> targetQueue;
    public BotShortestPath(String botName, Equipa equipa) {
        super(botName, equipa);
        routWeight = 0;
    }

    @Override
    public void createRout(Mapa map,Vertice startVertex, Vertice flag) {
        for (Iterator<Vertice> it = map.iteratorShortestPath(startVertex, flag); it.hasNext(); ) {
            Vertice vertice = it.next();
            targetQueue.enqueue(vertice);
        }
        routWeight = map.shortestPathWeight(startVertex, flag);
        targetQueue.dequeue();
    }

    @Override
    public void play() {

    }


}
