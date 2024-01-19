package projeto_ed.Game;

public class Vertice {

    static private int cont = 1;
    private Bot bot;
    private boolean hasFlag1;
    private boolean hasFlag2;
    private int num;

    public Vertice() {
        bot = null;
        num = cont;
        cont += 1;

    }

    public boolean isOcuped() {
        return !(bot == null);
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public boolean isHasFlag1() {
        return hasFlag1;
    }

    public void setHasFlag1(boolean hasFlag1) {
        this.hasFlag1 = hasFlag1;
    }

    public boolean isHasFlag2() {
        return hasFlag2;
    }

    public void setHasFlag2(boolean hasFlag2) {
        this.hasFlag2 = hasFlag2;
    }


    public int getNum() {
        return num;
    }
}
