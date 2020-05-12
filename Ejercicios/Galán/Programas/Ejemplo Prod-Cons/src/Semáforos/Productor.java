package Semáforos;

import java.util.concurrent.Semaphore;

public class Productor extends Thread{

    Semaphore s = new Semaphore(0);
}
