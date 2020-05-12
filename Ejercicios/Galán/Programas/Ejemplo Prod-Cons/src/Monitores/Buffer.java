package Monitores;

public class Buffer {           // Variable compartida

    private int[] elem;
    private int p, c, nelem;

    public Buffer(int max){

        p = 0;
        c = 0;
        this.nelem = 0;
        elem = new int[max];
    }

    public void add(int e){

        while(nelem != elem.length) {
            Thread.yield();
        }

        elem[p] = e;
        p = (p + 1) % elem.length;
        nelem++;
    }

    public int get(){

        while(nelem > 0){
            Thread.yield();
        }

        nelem--;
        int res = elem[c];
        c = (c + 1) % elem.length;

        return res;
    }
}
