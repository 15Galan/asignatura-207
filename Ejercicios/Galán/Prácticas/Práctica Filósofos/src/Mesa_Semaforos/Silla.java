package Mesa_Semaforos;
import java.util.concurrent.*;

public class Silla {
	
	private Semaphore haySillas = new Semaphore(1, true);
	private Semaphore mutex = new Semaphore(1, true);
	private int sillas = 4;		// Sillas libres
	
	public void qSilla(int id) throws InterruptedException{
		
		haySillas.acquire();
		mutex.acquire();
		sillas--;
		
		if(sillas > 0){
			
			haySillas.release();
		}
		
		mutex.release();
	}
	
	public void dSilla(int id) throws InterruptedException{
		
		haySillas.acquire();
		mutex.acquire();
		sillas--;
		
		if(sillas == 1){
			
			haySillas.release();
		}
		
		mutex.release();
	}
}
