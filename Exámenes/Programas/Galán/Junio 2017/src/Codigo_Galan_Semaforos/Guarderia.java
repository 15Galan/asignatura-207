package Codigo_Galan_Semaforos;

import java.util.concurrent.Semaphore;

public class Guarderia {

	private int nBebes   = 0;
	private int nAdultos = 0;
	
	private Semaphore bebe	 = new Semaphore(1, true);
	private Semaphore adulto = new Semaphore(1, true);
	private Semaphore mutex  = new Semaphore(1, true);
	
	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		
		bebe.acquire();
		mutex.acquire();

		nBebes++;

		if((nBebes) <= 3*nAdultos){

			System.out.println("[ B ]["+id+"]	Entra en la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");

			bebe.release();
		}

		mutex.release();
	}

	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		
		bebe.acquire();
		mutex.acquire();

		nBebes--;

		if(nBebes > 0){

			System.out.println("[ B ]["+id+"]	Sale de la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");

			bebe.release();
		}

		mutex.release();
	}

	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{
		
		adulto.acquire();
		mutex.acquire();

		nAdultos++;

		System.out.println("[ A ]["+id+"]	Entra en la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");
		
		adulto.release();
		mutex.release();
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		
		adulto.acquire();
		mutex.acquire();

		nAdultos--;

		if(nBebes <= 3*nAdultos && nAdultos > 0){

			System.out.println("[ A ]["+id+"]	Sale de la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");

			adulto.release();
		}

		mutex.release();
	}

}
