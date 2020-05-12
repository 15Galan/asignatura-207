package recursosEsqueleto;

import java.util.*;
public class Cliente extends Thread {
	
	private static Random r = new Random();
	private int id,num;
	private GestorRecursos gestor;
	
	public Cliente(int id, GestorRecursos gestor,int num){
		this.id = id;
		this.gestor = gestor;
		this.num = num;
	}

	
	public void run(){

		while (true){
		
			try {
				int n = r.nextInt(num)+1;//cantidad de recursos que pide
				gestor.quieroRecursos(id, n);
				Thread.sleep(r.nextInt(1000));//usa los recursos
				gestor.devuelvoRecursos(id,n);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
