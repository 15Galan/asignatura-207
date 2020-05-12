package Junio2017_Semaforos;

import java.util.concurrent.Semaphore;

public class Guarderia {

	private int nBebes		= 0;
	private int nAdultos	= 0;

	private Semaphore MUTEX		= new Semaphore(1, true);
	private Semaphore bebe		= new Semaphore(0, true);
	private Semaphore adulto	= new Semaphore(1, true);

//	private boolean esperaBebe, esperaAdulto;

	/**
	 * Un bebe que quiere entrar en la Guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya al menos, haya 1 adulto por cada 3 nBebes.
	 */
	public void entraBebe(int id) throws InterruptedException{

		bebe.acquire();
		MUTEX.acquire();

		nBebes++;

		if(nBebes+1 <= 3*nAdultos){
			System.out.println("[ B ]["+id+"]	ENTRA en la guarderia.	[B "+ nBebes +"	A "+ nAdultos +"]");
			bebe.release();
		}

		MUTEX.release();
	}

	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo.
	 */
	public void saleBebe(int id) throws InterruptedException{

		MUTEX.acquire();

		nBebes--;
		System.out.println("[ B ][" + id + "]	SALE de la guarderia.	[B " + nBebes + "	A " + nAdultos + "]");

		MUTEX.release();


	}

	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo.
	 */
	public void entraAdulto(int id) throws InterruptedException {

		MUTEX.acquire();

		nAdultos++;
		System.out.println("[ A ][" + id + "]	ENTRA en la guarderia.	[B " + nBebes + "	A " + nAdultos + "]");

		MUTEX.release();
	}

	/**
	 * Un adulto que quiere irse de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya al menos, haya 1 adulto por cada 3 nBebes.
	 */
	public void saleAdulto(int id) throws InterruptedException{

		adulto.acquire();
		MUTEX.acquire();

		nAdultos--;

		if(nBebes <= 3*(nAdultos-1)){
			System.out.println("[ A ]["+id+"]	SALE de la guarderia.	[B "+ nBebes +"	A "+ nAdultos +"]");
			adulto.release();
		}

		MUTEX.release();
	}
}
