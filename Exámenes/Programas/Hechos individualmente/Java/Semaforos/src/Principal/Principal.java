package Principal;

import Recursos.*;

import java.util.Random;

public class Principal {

    public static void main(String[]args){
        Random r = new Random();
        Ascensor ascensor = new Ascensor();

        for(int i = 0; i < 100; i++){

            int id = r.nextInt(100);
            int origen  = r.nextInt(9);
            int destino = r.nextInt(9) + 10 % 9;

            Cliente cliente = new Cliente(id, origen, destino, ascensor);
        }

        ascensor.start();
    }
}
