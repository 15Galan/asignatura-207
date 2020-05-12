package Septiembre2014;


import java.util.*;
public class Cliente extends Thread{

	private int id;
	private int numP;
	private Ascensor asc;
	private int origen,destino;
	private static Random r = new Random();

	public Cliente(int id,int numP,Ascensor asc){
		this.id = id;
		this.numP = numP;
		this.asc = asc;
	}


	public void run(){
		
			
			destino = r.nextInt(numP);
			try {
				origen=id;
				asc.clienteEsperaAscensor(origen,id);

				asc.clienteSubeAscensor(destino,id);
				
				asc.clienteBajaAscensor(destino,id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}

}
