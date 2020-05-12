package miel;

import java.util.*;
public class Abeja extends Thread{
	private Tarro tarro;
	private int id;
	private Random r = new Random();
	
	public Abeja(Tarro tarro,int id){
		this.tarro = tarro;
		this.id = id;
	}
	
	public void run(){
		for (int i = 0; i < 10; i++){
			try {
				Thread.sleep(500);
				tarro.nuevaPorcion(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}