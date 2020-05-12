package montanaRusa;

import java.util.concurrent.*;

public class MontanaRusa {

	private int CPD=5;//el numero esta por poner pero tiene que ser menor que el numero de pasjeros
	private int pasajeros=0;
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore espera=new Semaphore(0,true);//inicialmente el coche esta vacio
	private Semaphore esperaP=new Semaphore(1,true);
	private Semaphore esperaB=new Semaphore(0,true);
	
	public void paseo() throws InterruptedException{
		espera.acquire();
		//mutex.acquire();
		//pasajeros=0;
		//esperaP.release();
		esperaB.release();
		System.out.println("Montaña rusa de paseo");
		//mutex.release();
	}
	
	public void montarPasjero(int id) throws InterruptedException{
		esperaP.acquire();
		mutex.acquire();
		pasajeros++;
		
		if(pasajeros<CPD){
			esperaP.release();
		}
		if(pasajeros==CPD){
			espera.release();
		}
		System.out.println("Pasajero "+id+" se sube a la montaña rusa. Pasajeros = "+pasajeros);
		mutex.release();
	}
	
	public void bajarPasajero(int id) throws InterruptedException{
		esperaB.acquire();
		mutex.acquire();
		pasajeros--;
		if(pasajeros>0){
			esperaB.release();
		}
		if(pasajeros==0){
			esperaP.release();
		}
		System.out.println("Pasajero "+id+" se baja");
		mutex.release();
	}
}
