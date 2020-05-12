package Semáforos;

import java.util.concurrent.Semaphore;

public class Consumidor extends Thread{

    Semaphore s = new Semaphore(0);
}
