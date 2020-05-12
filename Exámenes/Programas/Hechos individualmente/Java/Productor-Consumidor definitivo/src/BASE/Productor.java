package BASE;

import java.util.*;
public class Productor extends Thread{
	private Buffer b;
	private Random r = new Random();
	public Productor(Buffer b){
		this.b = b;
	}
	
	
	public void run(){
		int dato;
		for (int i = 0; i<1000; i++){
			//generando el nuevo dato
			try {
				dato = r.nextInt(2000);
				b.almacenar(dato);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
