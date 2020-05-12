package SemaforoOptimizado;

import BASE.*;
import SemaforoBinario.BufferBinarios;

public class PrincipalSemaforoOptimizado {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Buffer b = new BufferBinarios();
        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);
        p.start();c.start();
    }

}
