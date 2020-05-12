package miel;

import java.util.concurrent.*;
public class Tarro {
	
	private int tarro;
	private int H;
	private Semaphore mutex = new Semaphore(1,true);
	private Semaphore lleno = new Semaphore(0,true);
	private Semaphore vacio = new Semaphore(0,true);
	
	public Tarro(int h){
		H = h;
	}
	
	public void nuevaPorcion(int id) throws InterruptedException{
		mutex.acquire();
		tarro++;
		System.out.println("abeja "+id+" pone porcion en tarro "+tarro);
		if (tarro == H){
			System.out.println("abeja "+id+" encuentra lleno el tarro");
			lleno.release();
			vacio.acquire();
		}
		
		mutex.release();
	}
	
	public void comoMiel() throws InterruptedException{
		lleno.acquire();
		tarro = 0;
		System.out.println("El oso se come el tarro "+tarro);
		vacio.release();
	}

}