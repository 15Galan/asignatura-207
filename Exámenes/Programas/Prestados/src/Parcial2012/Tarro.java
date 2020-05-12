package Parcial2012;

import java.util.concurrent.*;

public class Tarro {

	private Semaphore mutex			= new Semaphore(1,true);
	private Semaphore esperaOso 	= new Semaphore(0,true);	// Inicialemnte el tarro esta vacio
	private Semaphore esperaAbeja	= new Semaphore(1,true);
	private int tarro;
	private int nPorciones;
	
	public Tarro(int H){
		tarro=H;
		nPorciones=0;
	}
	
	public void nuevaPorcion(int id) throws InterruptedException{

		esperaAbeja.acquire();			// Una abeja bloquea "esperaAbeja".

		mutex.acquire();				// Se bloquea la sección crítica.

		nPorciones++;					// La abeja genera una porción.

		if(nPorciones<tarro){
			esperaAbeja.release();		// La abeja deja la porción y termina (desbloquea "esperaAbeja").
		}

		if(nPorciones==tarro){
			esperaOso.release();		// El oso desbloquea "esperaOso".
		}

		System.out.println("[Abeja "+id+"] Llena el tarro con una porción.		Tarro: "+nPorciones+"/5.");

		mutex.release();				// Se desbloquea la sección crítica.
	}
	
	public void comoMiel() throws InterruptedException{

		esperaOso.acquire();			// El oso bloquea "esperaOso".

		mutex.acquire();				// Se bloquea la sección crítica.

		nPorciones=0;					// El oso se come todas las porciones.

		System.out.println("[Oso] Se come la miel del tarro");

		esperaAbeja.release();			// Se desbloquea "esperaAbeja".

		mutex.release();				// Se desbloquea la sección crítica.
		
	}
}