package Junio2017_Monitores;

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
	 * Un bebe que quiere entrar en la Codigo_Base_Junio2017 llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya al menos, 1 adulto por cada 3 nBebes.
	 */
	public void entraBebe(int id) throws InterruptedException{
		l.lock();

		try{
			while(!(nBebes+1 <= 3*nAdultos)){
				bebe.await();
			}

			nBebes++;
			System.out.println("[ B ]["+id+"]	ENTRA en la guarderia.	[B "+nBebes+"	A "+nAdultos+"]");

		}finally{
			l.unlock();
		}
	}

	/**
	 * Un bebe que quiere irse de la Codigo_Base_Junio2017 llama a este metodo.
	 */
	public void saleBebe(int id) throws InterruptedException{
		l.lock();

		try{
			nBebes--;
			System.out.println("[ B ]["+id+"]	SALE de la guarderia.	[B "+nBebes+"	A "+nAdultos+"]");

			adulto.signalAll();
			bebe.signalAll();

		}finally{
			l.unlock();
		}
	}

	/**
	 * Un adulto que quiere entrar en la Codigo_Base_Junio2017 llama a este metodo.
	 */
	public void entraAdulto(int id) throws InterruptedException{
		l.lock();

		try{
			nAdultos++;
			System.out.println("[ A ]["+id+"]	ENTRA en la guarderia.	[B "+nBebes+"	A "+nAdultos+"]");

			bebe.signalAll();
			adulto.signalAll();

		}finally {
			l.unlock();
		}
	}
	
	/**
	 * Un adulto que quiere irse  de la Codigo_Base_Junio2017 llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya al menos, 1 adulto por cada 3 nBebes.
	 */
	public void saleAdulto(int id) throws InterruptedException{
		l.lock();

		try{
			while(!(nBebes <= 3*(nAdultos-1))){
				adulto.await();
			}

			nAdultos--;
			System.out.println("[ A ]["+id+"]	SALE de la guarderia.	[B "+nBebes+"	A "+nAdultos+"]");

		}finally{
			l.unlock();
		}
	}
}
