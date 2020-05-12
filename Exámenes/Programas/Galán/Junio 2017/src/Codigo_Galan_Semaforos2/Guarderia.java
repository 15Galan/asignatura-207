package Codigo_Galan_Semaforos2;

import java.util.concurrent.Semaphore;

public class Guarderia {

	private int bebes = 0;
	private int adultos = 0;

	private Semaphore MUTEX			= new Semaphore(1, true);		// Se empieza con "esperaBebes = 0" ya que primero debe
	private Semaphore esperaBebes	= new Semaphore(0, true);		// entrar un adulto: "Debe haber 1 adulto cada 3 bebes."
	private Semaphore esperaAdultos	= new Semaphore(1, true);		// Al empezar, bebes = 0 y adultos = 0.

	/**
	 * Un bebe que quiere entrar en la Guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya al menos, haya 1 adulto por cada 3 bebes.
	 */
	public void entraBebe(int id) throws InterruptedException{

		esperaBebes.acquire();		// Se bloquea "esperaBebes (semaforo)" para que otros bebes no entren.

		MUTEX.acquire();			// Se bloquea la sección critica para que el bebe haga su accion y nadie moleste.

		bebes++;					// El bebe va a entrar.

		if(bebes <= 3* adultos){	// Los bebes solo pueden entrar si cumplen su condicion (solo a la entrada).
			System.out.println("[ B ]["+id+"]	ENTRA en la guarderia.	[B "+ bebes +"	A "+ adultos +"]");

		}else{
			esperaAdultos.release();// El bebe desbloquea "esperaAdultos (semaforo) para que entren / salgan adultos.
		}

		MUTEX.release();			// Se desbloquea la sección critica para que entren / salgan más bebes / adultos.
		esperaBebes.release();		// Una vez dentro, el bebe desbloquea su semaforo para que puedan entrar / salir mas bebes.
	}

	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo.
	 */
	public void saleBebe(int id) throws InterruptedException{

		esperaBebes.acquire();		// Se bloquea "esperaBebes (semaforo)" para que otros bebes no salgan.

		MUTEX.acquire();			// Se bloquea la seccion critica para que el bebe haga su accion y nadie molesten.

		bebes--;					// El bebe va a salir (y sale, porque los bebes pueden salir siempre).

		System.out.println("[ B ][" + id + "]	SALE de la guarderia.	[B " + bebes + "	A " + adultos + "]");

		MUTEX.release();			// Se desbloquea la seccion critica para que entren / salgan más bebes / adultos.
		esperaBebes.release();		// Una vez fuera, el bebe desbloquea su semaforo para que puedan entrar / salir mas bebes.
	}

	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo.
	 */
	public void entraAdulto(int id) throws InterruptedException{

		esperaAdultos.acquire();	// Se bloquea "esperaAdultos (semaforo)" para que otros adultos no entren.

		MUTEX.acquire();			// Se bloquea la sección crítica para que el adulto haga su accion y nadie entre.

		adultos++;					// Un adulto quiere entrar (y entra, porque los adultos pueden entrar siempre).

		System.out.println("[ A ][" + id + "]	ENTRA en la guarderia.	[B " + bebes + "	A " + adultos + "]");

		MUTEX.release();			// Se desbloquea la sección critica para que entren / salgan más bebes / adultos.
		esperaAdultos.release();    // El adulto desbloquea su semaforo para que puedan entrar / salir más adultos.
	}

	/**
	 * Un adulto que quiere irse de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya al menos, haya 1 adulto por cada 3 bebes.
	 */
	public void saleAdulto(int id) throws InterruptedException{

		esperaAdultos.acquire();	// Se bloquea "esperaAdultos (semaforo)" para que otros adultos no salgan.

		MUTEX.acquire();			// Se bloquea la sección crítica para que el adulto haga su accion y nadie salga.

		adultos--;					// Un adulto quiere salir.

		if(bebes <= 3* adultos && adultos > 0){	// Un adulto solo puede salir si cumple su condicion (solo a la salida).
			System.out.println("[ A ]["+id+"]	SALE de la guarderia.	[B "+ bebes +"	A "+ adultos +"]");

		}else{
			esperaBebes.release();		// Se desbloquea "esperaBebes (semaforo) "para que puedan entrar / salir mas bebes.
		}

		MUTEX.release();			// Se desbloquea la sección critica para que entren / salgan más bebes / adultos.
		esperaAdultos.release();	// Se desbloquea "esperaAdultos (semaforo)" para que puedan entrar / salir mas adultos.
	}
}
