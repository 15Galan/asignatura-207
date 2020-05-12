package Codigo_Galan_Locks;

import java.util.Random;

public class Adulto extends Thread{
	
	private Random r = new Random();
	private Guarderia g;
	private int id;
	public Adulto(Guarderia g, int id){
		this.g = g;
		this.id = id;
	}

	
	public void run(){
		while (true){
			try {
				Thread.sleep(r.nextInt(2000));
				g.entraAdulto(id);
				Thread.sleep(r.nextInt(1000));
				g.saleAdulto(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
