package Codigo_Galan_Locks2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Guarderia {

	private int nBebes 		= 0;
	private int nAdultos 	= 0;

	private Lock l 				= new ReentrantLock();
	private Condition bebe 		= l.newCondition();
	private Condition adulto 	= l.newCondition();

	/**
	 * Un bebe que quiere entrar en la Codigo_Base llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya al menos, 1 adulto por cada 3 nBebes.
	 */
	public void entraBebe(int id) throws InterruptedException{

		l.lock();					// Se bloquea la seccion critica.

		try{
			while(!(nBebes <= 3* nAdultos)){
				bebe.await();		// Mientras no se cumpla la condicion de entrada, el bebe espera.
			}

			nBebes++;				// El bebe entra.

			System.out.println("[ B ]["+id+"]	ENTRA en la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");

		}finally{
			l.unlock();				// Se desbloquea la seccion critica.
		}
	}

	/**
	 * Un bebe que quiere irse de la Codigo_Base llama a este metodo.
	 */
	public void saleBebe(int id) throws InterruptedException{

		l.lock();					// Se bloquea la seccion critica.

		try{
			nBebes--;				// Un bebe puede salir cuando quiera.

			System.out.println("[ B ]["+id+"]	SALE de la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");

			adulto.signalAll();		// Se activan todos los adultos que estaban en espera.

		}finally{
			l.unlock();				// Se desbloquea la seccion critica.
		}
	}

	/**
	 * Un adulto que quiere entrar en la Codigo_Base llama a este metodo.
	 */
	public void entraAdulto(int id) throws InterruptedException{

		l.lock();					// Se bloquea la seccion critica.

		try{
			nAdultos++;				// Un adulto puede entrar cuando quiera.

			System.out.println("[ A ]["+id+"]	ENTRA en la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");

			bebe.signalAll();		// Se activan todos los bebes que estaban en espera.

		}finally{
			l.unlock();				// Se desbloquea la seccion critica.
		}
	}
	
	/**
	 * Un adulto que quiere irse  de la Codigo_Base llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya al menos, 1 adulto por cada 3 nBebes.
	 */
	public void saleAdulto(int id) throws InterruptedException{

		l.lock();					// Se bloquea la seccion critica.

		try{
			while(!(nBebes <= 3*nAdultos)){
				adulto.await();		// Mientras no se cumpla la condicion de salida, el adulto espera.
			}

			nAdultos--;				// El adulto sale.

			System.out.println("[ A ]["+id+"]	SALE de la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");

		}finally{
			l.unlock();				// Se desbloquea la seccion critica.
		}
	}
}
