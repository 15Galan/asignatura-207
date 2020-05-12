package Parcial2013;

import java.util.concurrent.*;

/**
 * autor:
 * maquina:
 */
public class Planta {
	
	private static final int TamSala = 3;
	private static final int NumHerramientas = 7;
	private static final int HerramientasPorTrabajador = 2;
	private static final int NumTrabajadores = 10;
	private static final int TiempoDescanso = 2000;
	private static final int TiempoEnSala = 1000;
	
	private static int herramientas=NumHerramientas;
	private static int nTrabajadores=0;
	private static Semaphore mutex=new Semaphore(1,true);
	private static Semaphore espera =new Semaphore(0,true);
	private static Semaphore espraTotalTrabajadores =new Semaphore(0,true);
	private static Semaphore esperaHerramientas=new Semaphore(0,true);
	private static Semaphore turno=new Semaphore(1,true);
	
	private static int cont=0;
	private static int coste=0;
	
	/** llamado por el trabajador id cuando quiere entrar
	*  en la sala contaminda. Cuando entre tiene que haber cogido dos 	*  herramientas y debe haber a lo sumo tres clientes en la sala 
	 * @throws InterruptedException 
	*/
	private static void entraSala(int id) throws InterruptedException {
		turno.acquire();
		mutex.acquire();
		herramientas-=HerramientasPorTrabajador;
		if(herramientas<0){
			mutex.release();
			esperaHerramientas.acquire();
			mutex.acquire();
		}
		nTrabajadores++;
		cont++;
		if(nTrabajadores>TamSala){
			mutex.release();
			espera.acquire();
			mutex.acquire();
		}
		System.out.println("Entra en Sala "+id + " herramientas: "+herramientas + " En la sala: "+nTrabajadores);
		if(cont==NumTrabajadores){
			cont=0;
			coste+=1000;
			espraTotalTrabajadores.release();
		}
		turno.release();
		mutex.release();
		
		
	}
	/** llamado por el trabajador id antes de salir
	*  de la sala contaminada. Cuando salga tiene que haber devuelto 	  	*  las dos herramientas que ten�a 
	 * @throws InterruptedException 
	*/ 
	private static void saleSala(int id) throws InterruptedException{
		mutex.acquire();
		herramientas+=HerramientasPorTrabajador;
		
		if(herramientas==1 || herramientas==0){
			esperaHerramientas.release();
			System.out.println("Trabajador "+id+" da las herramientas a un compañero");
		}
		nTrabajadores--;
		if(nTrabajadores==TamSala){
			espera.release();
		}
		System.out.println("Sale de Sala "+id+ " herramientas: "+herramientas + " En la sala: "+nTrabajadores);
		mutex.release();
	}
	/** llamado por el contador para mostrar un mensaje en la pantalla
	*   cada vez que entran 10 trabajadores nuevos
	 * @throws InterruptedException 
	*/
	private static void diezMas() throws InterruptedException{
		espraTotalTrabajadores.acquire();
		System.out.println("Han entrado 10 trabajadores. Coste trabajo: "+coste);
	}
	
	public static class Trabajador extends Thread{
		private int id;
		public Trabajador(int id){
			this.id = id;
		}
		
		public void run(){
			try {
				while(true){
					entraSala(id);
					Thread.sleep(TiempoEnSala);
					saleSala(id);
					Thread.sleep(TiempoDescanso);
				}		
			}catch(InterruptedException ie){
				
			}
		}
	}
	
	public static class Contadora extends Thread{
		
		
		public void run(){
			while (true){
				try {
					diezMas();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] str){
		Contadora cont=new Contadora();
		cont.start();
		Trabajador t[]=new Trabajador[NumTrabajadores];
		for(int i=0;i<t.length;i++){
			t[i]=new Trabajador(i);
			t[i].start();
		}
		
	}

}