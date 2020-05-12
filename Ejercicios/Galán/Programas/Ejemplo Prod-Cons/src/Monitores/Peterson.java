package Monitores;

public class Peterson {

    private volatile int turno = 0;
    private volatile boolean f0 = false;
    private volatile boolean f1 = false;

    public void preProt0(){

        f0 = true;
        turno = 1;

        while(f1 && turno == 1){
            Thread.yield();
        }
    }

    public void postProt0(){
        f0 = false;
    }

    public void preProt1(){

        f1 = true;
        turno = 1;

        while(f0 && turno == 0){
            Thread.yield();
        }
    }

    public void postProt1(){
        f1 = false;
    }
}
