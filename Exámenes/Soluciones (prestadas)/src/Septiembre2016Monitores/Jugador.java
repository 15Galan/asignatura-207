package Septiembre2016Monitores;

import java.util.Random;

public class Jugador extends Thread {
	
	private int id;
	private Mesa mesa;
	Random rnd=new Random();
	private boolean cara= rnd.nextBoolean();
	
	public Jugador(int id, Mesa mesa){
		this.id=id;
		this.mesa=mesa;
	}
	
	private void resetJugada(){
		cara=rnd.nextBoolean();
	}
	
	public void run(){
		try {
			while(true){
				mesa.nuevaJugada(id, cara);
				resetJugada();
				System.out.println("caca");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
