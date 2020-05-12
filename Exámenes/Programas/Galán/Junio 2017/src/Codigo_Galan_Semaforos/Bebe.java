package Codigo_Galan_Semaforos;

import java.util.Random;

public class Bebe extends Thread{
	
	private Random r = new Random();
	private Guarderia g;
	private int id;
	public Bebe(Guarderia g, int id){
		this.g = g;
		this.id = id;
	}

	
	public void run(){
		while (true){
			try {
				Thread.sleep(r.nextInt(2000));
				g.entraBebe(id);
				Thread.sleep(r.nextInt(1000));
				g.saleBebe(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}