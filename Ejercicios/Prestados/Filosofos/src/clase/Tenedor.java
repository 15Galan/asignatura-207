package clase;

import java.util.concurrent.*;

public class Tenedor {
	

	private Semaphore mutex=new Semaphore(1,true);
	private int id;
	public Tenedor(int id){
		this.id=id;
	}
	public void qTenerdor(int id) throws InterruptedException{
		//llamado por el filosofo id que quiere coger el tenedor
		mutex.acquire();
		System.out.println("Filosofo "+id+" tiene un tenedor "+ this.id);
	}
	
	public void dTenedor(int id){
		//llamado por el filosofo id que quiere coger el tenedor
		mutex.release();
		System.out.println("Filosofo "+id+" suelta un tenedor "+this.id);
	}
}
