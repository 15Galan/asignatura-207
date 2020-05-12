package clase;

import java.util.concurrent.*;
public class Sillas {

	private int sLibres=4;
	//private Semaphore sem=new Semaphore(4,true);
	private Semaphore haySillas=new Semaphore(1,true);
	private Semaphore mutex=new Semaphore(1,true);
	
	public void qSilla(int id) throws InterruptedException{
		haySillas.acquire();
		mutex.acquire();
		sLibres--;
		if(sLibres>0){
			haySillas.release();
		}
		mutex.release();
	}
	
	public void dSilla(int id) throws InterruptedException{
		mutex.acquire();
		sLibres++;
		if(sLibres==1){
			haySillas.release();
		}
		mutex.release();
	}
	
}
