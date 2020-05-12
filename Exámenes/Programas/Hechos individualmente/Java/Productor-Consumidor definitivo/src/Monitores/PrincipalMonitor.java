package Monitores;

import BASE.*;

public class PrincipalMonitor {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Buffer b = new BufferMonitor();
        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);
        p.start();c.start();
    }

}