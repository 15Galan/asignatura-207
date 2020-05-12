package pajaros;

import java.util.concurrent.*;

public class Nido {

	private int bichitos=5;
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore esperaC=new Semaphore(0,true);//inicialmente el plato esta vacio
	private Semaphore esperaP=new Semaphore(1,true);
	private int plato=0;
	

	
	public void nuevoBichito(int id) throws InterruptedException{
		esperaP.acquire();
		mutex.acquire();
		plato++;
		if(plato<bichitos){
			esperaP.release();
		}
		if(plato==1){
			esperaC.release();
		}
		System.out.println("Padre "+id+" pone el bichito "+plato);
		mutex.release();
	}
	
	public void comeBichito(int id) throws InterruptedException{
		esperaC.acquire();
		mutex.acquire();
		plato--;
		if(plato>0){
			esperaC.release();
		}
		if(plato==bichitos-1){
			esperaP.release();
		}
		
		System.out.println("Hijo "+id+" come el bichito "+plato);
		mutex.release();
		
	}
}
