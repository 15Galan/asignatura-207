/**
 * Alumno:		Antonio J. Gal�n Herrera
 * Titulaci�n:	Ingenier�a Inform�tica
 * Ordenador:	PC1105
 * DNI:			26808894w
 */

package Codigo_Galan_Locks;

import java.util.concurrent.locks.*;


public class Guarderia {

	private int nBebes   = 0;
	private int nAdultos = 0;
	
	private Lock l = new ReentrantLock();
	private Condition bebe	 = l.newCondition();
	private Condition adulto = l.newCondition();
	
	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		
		l.lock();
			
			if((nBebes+1) <= 3*nAdultos){
				
				nBebes++;
				System.out.println("[ B ]["+id+"]	Entra en la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");
			
			}else{
				bebe.await();
			}
			
		l.unlock();
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		
		l.lock();
			
			if(nBebes > 0){
				nBebes--;
				System.out.println("[ B ]["+id+"]	Sale de la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");
			}
			
			adulto.signalAll();
		l.unlock();
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{
		
		l.lock();
		
			nAdultos++;
			System.out.println("[ A ]["+id+"]	Entra en la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");
		
			bebe.signalAll();
		l.unlock();
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		
		l.lock();

			if(nBebes <= 3*(nAdultos-1) && nAdultos > 0){
				
				nAdultos--;
				System.out.println("[ A ]["+id+"]	Sale de la guarderia.	[B "+nBebes+" | "+nAdultos+" A]");
			
			}else{
				adulto.await();
			}
			
		l.unlock();
	}

}
