package aseos;

import java.util.Random;

public class Cliente extends Thread{
	
	private int id;
	private Aseos aseo;
	private static Random r = new Random();
	
	public Cliente(int id,Aseos aseo){
		this.id = id;
		this.aseo = aseo;
	}
	
	
	public void run(){
		while (true){
			
			try {
				Thread.sleep(r.nextInt(3000));
				aseo.entroAseo(id);
				Thread.sleep(r.nextInt(1000));
				aseo.salgoAseo(id);
				Thread.sleep(r.nextInt(3000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
