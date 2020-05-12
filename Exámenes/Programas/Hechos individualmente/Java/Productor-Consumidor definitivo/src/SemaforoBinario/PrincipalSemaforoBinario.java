package SemaforoBinario;

import BASE.*;

public class PrincipalSemaforoBinario {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Buffer b = new BufferBinarios();
        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);
        p.start();c.start();
    }

}
