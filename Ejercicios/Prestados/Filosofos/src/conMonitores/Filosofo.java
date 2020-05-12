package conMonitores;

import java.util.Random;

public class Filosofo extends Thread{

	private int id;
	private Tenedor izq, dch;
	Random r=new Random();
	private Sillas silla;
	
	public Filosofo(int id,Tenedor izq,Tenedor dch,Sillas silla){
		this.id=id;
		this.izq=izq;
		this.dch=dch;
		this.silla=silla;
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(r.nextInt(200));//piensa
				silla.qSilla(id);
				izq.qTenerdor(id);
				dch.qTenerdor(id);
				Thread.sleep(r.nextInt(100));//come
				izq.dTenedor(id);
				dch.dTenedor(id);
				silla.dSilla(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
