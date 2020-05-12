package Recursos;

import java.util.concurrent.Semaphore;

public class Ascensor extends Thread {

    private int planta;
    private boolean vacio;

    public Ascensor() {

        vacio = true;
        planta = 0;
    }

    public void subir() throws InterruptedException {

        if (planta < 9) {
            System.out.println("El ascensor sube a la planta" + (planta++) + ".");

        } else {
            System.out.println("El ascensor está en la planta más alta.");
        }
    }

    public void bajar() throws InterruptedException {

        if (planta > 0) {
            System.out.println("El ascensor baja a la planta" + (planta--) + ".");

        } else {
            System.out.println("El ascensor está en la planta más baja.");
        }
    }

    public void llenar() throws InterruptedException {

        vacio = false;
    }

    public void vaciar() throws InterruptedException {

        vacio = true;
    }

    public boolean estaVacio() throws InterruptedException {

        return vacio;
    }

    public int planta(){

        return planta;
    }
}
