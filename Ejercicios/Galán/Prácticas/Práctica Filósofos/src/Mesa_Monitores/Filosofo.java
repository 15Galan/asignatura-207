package Mesa_Monitores;

import java.util.Random;

public class Filosofo extends Thread{
	
	private static Random r = new Random();
	private int id;
	private Tenedor izq, der;
	private Silla silla;
	
	public Filosofo(int id, Tenedor izq, Tenedor der, Silla silla){
		
		this.id = id;
		this.izq = izq;
		this.der = der;
		this.silla = silla;
	}
	
	
	public void run(){
		
		while(true){
			
			try{
				
				Thread.sleep(r.nextInt(200));	// Pensar
				silla.qSilla(id);
				izq.qTenedor(id);
				der.qTenedor(id);
				
				Thread.sleep(r.nextInt(100));	// Come
				silla.qSilla(id);
				izq.dTenedor(id);
				der.dTenedor(id);
				
			}catch(InterruptedException e){
				
				e.printStackTrace();
			}
		}
	}

}
