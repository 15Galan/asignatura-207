package Junio2014;

import java.util.concurrent.*;

public class Isla {

	private Semaphore mutex=new Semaphore (1,true);
	private Semaphore esperaHelipuerto=new Semaphore (1,true);
	private Semaphore helicoptero=new Semaphore(1,true);
	private int tam;
	private int nPersonas;
	private int helipuerto;
	
	public Isla(int tam,int tamhelipuerto){
		helipuerto=tamhelipuerto;
		this.tam=tam;
		nPersonas=tam;
	}
	
	public void subeH(int id) throws InterruptedException{
		
		
		mutex.acquire();
		
		mutex.release();
	}
}
