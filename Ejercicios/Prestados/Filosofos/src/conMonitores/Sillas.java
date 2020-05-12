package conMonitores;


public class Sillas {

	private int sLibres=4;
	
	
	public synchronized void qSilla(int id) throws InterruptedException{
		while(sLibres==0){
			wait();
		}
		sLibres--;
	}
	
	public synchronized void dSilla(int id) throws InterruptedException{
		sLibres++;
		if(sLibres==1){//estaria bien con el if y sin el if (pero el notify si hay que ponerlo
			notify();
		}
	}
	
}
