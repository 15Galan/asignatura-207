package Parcial2012;

import java.util.concurrent.*;


public class Tarro {

	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore esperaOso =new Semaphore(0,true);//inicialemnte el tarro esta vacio
	private Semaphore esperaAbeja=new Semaphore(1,true);
	private int tarro;
	private int nPorciones;
	
	public Tarro(int H){
		tarro=H;
		nPorciones=0;
	}
	
	public void nuevaPorcion(int id) throws InterruptedException{
		esperaAbeja.acquire();
		mutex.acquire();
		nPorciones++;
		if(nPorciones<tarro){
			esperaAbeja.release();
		}
		if(nPorciones==tarro){
			esperaOso.release();
		}
		System.out.println("[Abeja "+id+"] Llena el tarro con una porción.		Tarro: "+nPorciones+"/5.");
		mutex.release();
	}
	
	public void comoMiel() throws InterruptedException{
		esperaOso.acquire();
		mutex.acquire();
		nPorciones=0;
		System.out.println("[Oso] Se come la miel del tarro");
		esperaAbeja.release();
		mutex.release();
		
	}
}