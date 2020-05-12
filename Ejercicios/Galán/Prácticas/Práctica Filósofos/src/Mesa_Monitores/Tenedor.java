package Mesa_Monitores;

public class Tenedor {
	
	private boolean libre = true;
	private int i;
	
	public Tenedor(int i){
		
		this.i = i;
	}
	
	public synchronized void qTenedor(int id) throws InterruptedException{		// Llamado por "fil id" cuando quiere el tenedor.
		
		while(!libre){
			
			wait();
			
			System.out.println("Filosofo "+id+" tiene el tenedor "+id);
			
			libre = false;
		}
	}
	
	public void dTenedor(int id){
		
		System.out.println("Filosofo "+id+" suelta el tenedor "+id);
		
		libre = true;
		
		notifyAll();
	}
}
