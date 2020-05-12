package Mesa_Semaforos;
import java.util.concurrent.Semaphore;

public class Tenedor {
	
	private Semaphore mutex = new Semaphore(1, true);
	private int id;
	
	public Tenedor(int id){
		
		this.id = id;
	}
	
	public void qTenedor(int id) throws InterruptedException{		// Llamado por "fil id" cuando quiere el tenedor.
		
		mutex.acquire();
		System.out.println("Filosofo "+id+" tiene el tenedor "+this.id);
		
	}
	
	public void dTenedor(int id){
		
		mutex.release();
		System.out.println("Filosofo "+id+" suelta el tenedor "+this.id);
		
	}
}
