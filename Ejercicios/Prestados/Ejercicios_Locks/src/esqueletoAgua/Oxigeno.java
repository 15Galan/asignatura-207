package esqueletoAgua;

import java.util.Random;

public class Oxigeno extends Thread{
	
	private static Random r = new Random();
	private int id;
	private GestorAgua gestor;
	
	public Oxigeno(int id, GestorAgua gestor){
		this.id = id;
		this.gestor = gestor;
	}

	
	public void run(){

		while (true){
		
			try {
			
				gestor.oListo(id);
				Thread.sleep(r.nextInt(1000));//espera para volver  a entrar
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}