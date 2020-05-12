package esqueletoAgua;

import java.util.Random;


public class Hidrogeno extends Thread{
	
	private static Random r = new Random();
	private int id;
	private GestorAgua gestor;
	
	public Hidrogeno(int id, GestorAgua gestor){
		this.id = id;
		this.gestor = gestor;
	}

	
	public void run(){

		while (true){
		
			try {		
				Thread.sleep(r.nextInt(1000));//espera para entrar
				gestor.hListo(id);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
