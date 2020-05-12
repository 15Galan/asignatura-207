package mrusaEsqueleto;
import java.util.*;
public class Pasajero extends Thread{
	
	private int id;
	private Coche c;
	private static Random r = new Random();
	
	public Pasajero(int id,Coche c){
		this.id = id;
		this.c = c;
	}
	
	
	public void run(){
		while (true){
			try{
				c.subir(id);
				c.bajar(id);
				Thread.sleep(r.nextInt(500));
			}catch(InterruptedException ie){
				
			}
			
		}
	}

}
