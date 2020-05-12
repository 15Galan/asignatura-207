import java.util.concurrent.*;
public class Planta {
	
	private static final int TamSala = 3;
	private static final int NumHerramientas = 5;
	private static final int HerramientasPorTrabajador = 2;
	private static final int NumTrabajadores = 10;
	private static final int TiempoDescanso = 2000;
	private static final int TiempoEnSala = 1000;
	private static  int espacioEnSala=TamSala;
	private static Semaphore mutexSala = new Semaphore(1,true);
	private static int herramientas = NumHerramientas;
	private static Semaphore esperaSala = new Semaphore(0,true);
	private static Semaphore esperaHerramientas = new Semaphore(0,true);
	private static int nTotalTrabajadores = 0;
	private static Semaphore espera10 = new Semaphore(0,true);
	
	private static void entraSala(int id) throws InterruptedException{
		mutexSala.acquire();
		herramientas = herramientas - HerramientasPorTrabajador;
		if (herramientas<0){
			mutexSala.release();
			esperaHerramientas.acquire();
			mutexSala.acquire();
		}
		espacioEnSala--;
		if (espacioEnSala < 0){
			mutexSala.release();
			esperaSala.acquire();
			mutexSala.acquire();
		}
		
		nTotalTrabajadores++;
		if (nTotalTrabajadores == NumTrabajadores){
			nTotalTrabajadores = 0;
			espera10.release();
		}
		System.out.println("Entra en Sala "+id + "herramientas : "+herramientas + " En la sala :"+espacioEnSala);
		mutexSala.release();
	}
	private static void saleSala(int id) throws InterruptedException{
		mutexSala.acquire();
		espacioEnSala++;
		if (espacioEnSala <= 0){
			esperaSala.release();
		}
		herramientas = herramientas + HerramientasPorTrabajador;
		if (herramientas < HerramientasPorTrabajador){
			esperaHerramientas.release();
		}
		
		System.out.println("Sale de Sala "+id+ "herramientas : "+herramientas + " En la sala :"+espacioEnSala);
		mutexSala.release();
	}
	private static void diezMas() throws InterruptedException{
		espera10.acquire();
		System.out.println(" Han entrado 10 trabajadores ");
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
			try{
				while (true){
					diezMas();
				}
				
			}catch (InterruptedException ie){
				
			}
		}
		
	}
	
	public static void main(String[] str){
		Trabajador[] trab = new Trabajador[10];
		Contadora c = new Contadora();
		for (int i = 0; i<trab.length; i++){
			trab[i] = new Trabajador(i);
		}
		for (int i = 0; i<trab.length; i++){
			trab[i].start();
		}
		c.start();
	}

}