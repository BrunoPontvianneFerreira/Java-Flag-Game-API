package projeto_ed.Game;

import java.util.Iterator;

public class Bot<T> {
    private Vertice vertice;
    private String nome;
    private Iterator<T> iterador;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Iterator<T> getIterador() {
        return iterador;
    }

    public void setIterador(Iterator<T> iterador) {
        this.iterador = iterador;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice vertice) {
        this.vertice = vertice;
    }
}
