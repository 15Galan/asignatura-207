package simpleProgreso;

public class Primo {

    private int primo;
    private int pos;

    public Primo(int primo, int pos){
        this.primo = primo;
        this.pos = pos;
    }

    public String toString(){
        return "Primo "+(++pos)+": "+primo+".";
    }
}
