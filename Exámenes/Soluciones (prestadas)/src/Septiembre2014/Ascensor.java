package Septiembre2014;
/**
 * Clase del ascensor que tiene capacidad para una persona
 */

import java.util.concurrent.*;

public class Ascensor extends Thread{
	private int sig = 1; // para saber si el ascensor sube o baja
	private int planta = 0; //planta en la que se encuentra el ascensor
	private int numPlantas;
	
	private boolean vacio=true;
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore espera[];
	private Semaphore esperaA=new Semaphore(0,true);
	private int dst=0;
	
	public Ascensor(int C){
	// C es el numero de plantas del edificio
		numPlantas = C;
		espera=new Semaphore [C];
		for(int i=0;i<espera.length;i++){
			espera[i]=new Semaphore(0,true);
		}
	
	}
	

	// Cliente id  espera el ascensor desde origen
	public  void clienteEsperaAscensor(int origen,int id) throws InterruptedException{
		espera[origen].acquire();
		
		
		
	}
	
	//el cliente id sube al ascensor, actualiza el destino
	public  void clienteSubeAscensor(int destino, int id) throws InterruptedException{
		
		mutex.acquire();
		vacio=false;
		dst=destino;
		mutex.release();
		esperaA.acquire();
		
	}
	
	// El cliente espera hasta llegar a su planta y baja del ascensor
		public  void clienteBajaAscensor(int destino, int id) throws InterruptedException{
			if(planta==destino){
				mutex.release();
				vacio=true;
				esperaA.release();
			}
			
	}

	
	//Ascensor llega a una planta y si lleva a un cliente con destino a
	//planta lo deja bajar
	public  void ascensorLlegaPlantaBajar(int planta) throws InterruptedException{
		if(planta==dst && !vacio){
			esperaA.release();
		}
		
	}
			
	// el Ascensor espera a que se baje cliente, si es el caso
	public  void ascensorEsperaBajarCliente(int planta) throws InterruptedException{
			if(!vacio && planta==dst){
				mutex.acquire();
				vacio=true;
				mutex.release();
			}
		
		}		
	
	
	//Ascensor deja entrar a un nuevo cliente si esta vacio
	public  void ascensorLlegaPlantaSubir(int planta) throws InterruptedException{
			
			
		}
		
	//Ascensor espera subir a un cliente, si esta vacio
	public  void ascensorEsperaSubirCliente() throws InterruptedException{
			}
			
		
			
	//solo sirve para que se vayan actualizando las plantas
	//el ascensor sube hasta que llega al ultimo piso, y luego
	//baja hasta que llega a la planta 0, y asi sucesivamente
	private void siguientePiso() throws InterruptedException{
			Thread.sleep(10);
			if (planta==numPlantas-1) sig = -1;
			if (planta == 0) sig = 1;
			planta = planta + sig;
			System.out.println("Ascensor esta en la planta "+planta);
	}
		
		

		
		
	public void run(){
	
		while (true){
			try {
				ascensorLlegaPlantaBajar(planta);
				ascensorEsperaBajarCliente(planta);
				ascensorLlegaPlantaSubir(planta);
				ascensorEsperaSubirCliente();
				siguientePiso();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

}	
	
	