package Septiembre2016Monitores;

import Septiembre2016.Jugador;
import Septiembre2016.Mesa;

public class Principal {

	public static void main(String[]args){
		
		int JUGADORES=4;
		
		
		Mesa mesa=new Mesa(JUGADORES);
		Jugador[] j=new Jugador[JUGADORES];
		
		for(int i=0;i<j.length;i++){
			j[i]=new Jugador(i,mesa, JUGADORES);
			j[i].start();
		}
	}
}
