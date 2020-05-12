package Septiembre2016Monitores;

import java.util.Random;

public class Jugador extends Thread {
	
	private int id;
	private Mesa mesa;
	Random rnd=new Random();
	private int N;
	private boolean cara= rnd.nextBoolean();
	private int ganador;

	public Jugador(int id, Mesa mesa,int N){
		this.id=id;
		this.mesa=mesa;
		this.N=N;
	}
	
	private void resetJugada(){
		cara=rnd.nextBoolean();
	}
	
	public void run(){
		ganador=N;
		try {
			while(ganador==N){
				ganador=mesa.nuevaJugada(id, cara);
				resetJugada();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
