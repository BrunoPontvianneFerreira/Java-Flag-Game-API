package projeto_ed.Game;

import projeto_ed.Queues.LinkedQueue;

public abstract class Bot {
    Team team;
    private String nome;
    private LinkedQueue<Vertex> rota;

    private int flagIndex;

    private int verticeIndex;

    private int lastVerticeIndex;
    private static int counter;

    public Bot( String botName, Team team){
        this.nome = botName;
        this.team = team;
        rota = null;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Bot.counter = counter;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Team getEquipa(){
        return this.team;
    }

    public void setEquipa(Team team){
        this.team = team;
    }

    public int getFlagIndex() {
        return flagIndex;
    }

    public void setFlagIndex(int flagIndex) {
        this.flagIndex = flagIndex;
    }

    public int getVerticeIndex() {
        return verticeIndex;
    }

    public void setVerticeIndex(int verticeIndex) {
        this.verticeIndex = verticeIndex;
    }

    public LinkedQueue<Vertex> getRota() {
        return rota;
    }

    public void setRota(LinkedQueue<Vertex> rota) {
        this.rota = rota;
    }

    public int getLastVerticeIndex() {
        return lastVerticeIndex;
    }

    public void setLastVerticeIndex(int lastVerticeIndex) {
        this.lastVerticeIndex = lastVerticeIndex;
    }
}
