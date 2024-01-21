package projeto_ed.Game;

import projeto_ed.Queues.LinkedQueue;

import java.util.Iterator;

public abstract class Bot {
    Equipa equipa;
    private Vertice vertice;
    private String nome;
    private LinkedQueue<Vertice> rota;

    private int flag_index;

    private int vertice_index;

    private int last_vertice_index;

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

    public Equipa getEquipa(){
        return this.equipa;
    }

    public void setEquipa(Equipa team){
        this.equipa = team;
    }

    public int getFlag_index() {
        return flag_index;
    }

    public void setFlag_index(int flag_index) {
        this.flag_index = flag_index;
    }

    public int getVertice_index() {
        return vertice_index;
    }

    public void setVertice_index(int vertice_index) {
        this.vertice_index = vertice_index;
    }

    public LinkedQueue<Vertice> getRota() {
        return rota;
    }

    public void setRota(LinkedQueue<Vertice> rota) {
        this.rota = rota;
    }

    public int getLast_vertice_index() {
        return last_vertice_index;
    }

    public void setLast_vertice_index(int last_vertice_index) {
        this.last_vertice_index = last_vertice_index;
    }
}
