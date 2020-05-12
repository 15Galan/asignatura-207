package Junio2017_Semaforos_Profesora;

import java.util.concurrent.*;
public class Guarderia {
	
	private int nBebe	= 0;
	private int nAdulto = 0;

	private int esperandoBebe	= 0; // Para entrar
	private int esperandoAdulto = 0; // Para salir

	private Semaphore MUTEX			= new Semaphore(1);
	private Semaphore mutexBebe		= new Semaphore(1);
	private Semaphore mutexAdulto	= new Semaphore(1);
	private Semaphore esperaBebe	= new Semaphore(0);
	private Semaphore esperaAdulto	= new Semaphore(0);
	
	public void entraBebe(int id) throws InterruptedException{
		mutexBebe.acquire();
		MUTEX.acquire();

		if ((nBebe + 1)> 3*nAdulto){	// Si entra un bebe mas se violan las reglas
			esperandoBebe++;
			MUTEX.release();
			esperaBebe.acquire();		// El bebe espera
			MUTEX.acquire();
		}

		nBebe++;

		boolean correcto = nBebe<=nAdulto*3;
		System.out.println("Bebe "+id+" entra.	Bebes: "+nBebe+",	Adultos: "+nAdulto+" -->[nBebes<=nAdultos*3]="+ correcto);

		MUTEX.release();
		mutexBebe.release();
	}
	
	public void saleBebe(int id) throws InterruptedException{
		MUTEX.acquire();

		nBebe--;

		if (nBebe<= (nAdulto-1)*3){		// Se puede ir un adulto si quiere
			if (esperandoAdulto>0){
				esperandoAdulto--;
				esperaAdulto.release();
			}
		}

		boolean correcto = nBebe<=nAdulto*3;
		System.out.println("Bebe "+id+" sale.	Bebes: "+nBebe+",	Adultos: "+nAdulto+" -->[nBebes<=nAdultos*3]="+ correcto);

		MUTEX.release();
	}
	
	public void entraAdulto(int id) throws InterruptedException{
		MUTEX.acquire();
		nAdulto++;						// Los adultos entran sin problemas

		if ((nBebe+1)<=nAdulto*3){		// si puede entrar un nuevo bebe
			if (esperandoBebe>0){ 		// y hay uno esperando.
				esperandoBebe--;
				esperaBebe.release();
			}
		}

		boolean correcto = nBebe<=nAdulto*3;
		System.out.println("Adulto "+id+" entra.	Bebes: "+nBebe+",	Adultos: "+nAdulto+" -->[nBebes<=nAdultos*3]="+ correcto);

		MUTEX.release();
	}
	
	public void saleAdulto(int id) throws InterruptedException{
		mutexAdulto.acquire();
		MUTEX.acquire();

		if (nBebe > (nAdulto-1)*3){		// El adulto no se puede porque se violan
										// Las reglas
			esperandoAdulto++;

			MUTEX.release();
			esperaAdulto.acquire();
			MUTEX.release();
		}
		
		nAdulto--;

		boolean correcto = nBebe<=nAdulto*3;
		System.out.println("Adulto "+id+" sale.	Bebes: "+nBebe+",	Adultos: "+nAdulto+" -->[nBebes<=nAdultos*3]="+ correcto);

		MUTEX.release();
		mutexAdulto.release();
	}
}
