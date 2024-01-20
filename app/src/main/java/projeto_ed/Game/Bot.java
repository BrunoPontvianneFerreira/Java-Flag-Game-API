package projeto_ed.Game;

import projeto_ed.Queues.LinkedQueue;

import java.util.Iterator;

public abstract class Bot {
    Equipa equipa;
    private Vertice vertice;
    private String nome;

    private LinkedQueue<Vertice> rota;

    public Bot( String botName, Equipa equipa){
        this.nome = botName;
        this.equipa = equipa;
        rota = null;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice vertice) {
        this.vertice = vertice;
    }

    public LinkedQueue<Vertice> getRota() {
        return rota;
    }

    public void setRota(LinkedQueue<Vertice> rota) {
        this.rota = rota;
    }

    public Equipa getEquipa(){
        return this.equipa;
    }

    public void setEquipa(Equipa team){
        this.equipa = team;
    }
}
