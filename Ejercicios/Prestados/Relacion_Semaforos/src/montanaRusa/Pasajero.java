package montanaRusa;

import java.util.Random;
public class Pasajero extends Thread{

	private MontanaRusa coche;
	private int id;
	private Random rnd=new Random();
	
	public Pasajero(int id,MontanaRusa coche){
		this.id=id;
		this.coche=coche;
	}
	
	public void run(){
		while(true){
			try {
				coche.montarPasjero(id);
				coche.bajarPasajero(id);
				Thread.sleep(rnd.nextInt(50));//pasajero da una vuelta por el parque
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
