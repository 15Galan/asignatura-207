package Mesa_Monitores;

public class Silla {
	
	private int sillas = 4;		// Sillas libres
	
	public synchronized void qSilla(int id) throws InterruptedException{
		
		while(true){
			
			wait();
			
			sillas--;
		}
	}
	
	public void dSilla(int id){
		
		if(sillas  == 1){
			
			notify();
		}
	}
}
