package Tribu;

import java.util.concurrent.*;

public class Olla {

	int tam=10;
	int olla=0;
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore esperaC=new Semaphore(1,true);//inicialmente la olla esta vacia
	private Semaphore esperaT=new Semaphore(0,true);
	
	public void cocina(int id) throws InterruptedException{		
		esperaC.acquire();
		mutex.acquire();
		olla=tam;
		System.out.println("El cocinero cocina a otro explorador");
		esperaT.release();
		mutex.release();
		
	}
	
	public void come(int id) throws InterruptedException{
		esperaT.acquire();
		mutex.acquire();
		olla--;
		if(olla>0){
			esperaT.release();
		}
		if(olla==0){
			esperaC.release();
		}
		System.out.println("Canibal "+id+" come. La olla tiene "+olla+" platos");
		mutex.release();
	}
}
