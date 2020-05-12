package Junio2016_Semaforos;

import java.util.concurrent.Semaphore;

public class Aseos {

	private int nClientes = 0;												// Clientes dentro del aseo.

	private boolean limpiando = false;										// Equipo de limpieza ocupado.				// Vale "false" porque supongo que el aseo empieza limpio.

	private Semaphore MUTEX		= new Semaphore(1, true);		// Seccion critica.
	private Semaphore limpieza	= new Semaphore(0, true);		// Equipo de Limpieza.						// El equipo de limpieza empieza bloqueado porque supongo que el aseo empieza limpio.
	private Semaphore cliente	= new Semaphore(1, true);		// Clientes meones.

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos:
	 * El cliente espera si el equipo de limpieza esta trabajando o
	 * esta esperando para poder limpiar los aseos.
	 */
	public void entroAseo(int id) throws InterruptedException {
		cliente.acquire();																								// El cliente bloquea a los demas (para que ni entren ni salgan a vez que el).
		MUTEX.acquire();																								// El cliente bloquea el aseo (seccion critica).

		nClientes++;

		if(!limpiando){																									// Si el equipo de limpieza no esta:
			System.out.println("[----->]	Cliente	#"+id+"		Aseos:	"+nClientes);
			cliente.release();																								// El cliente desbloquea a los demas.
		}

		MUTEX.release();																								// El cliente desbloquea el aseo (seccion critica).
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos.
	 */
	public void salgoAseo(int id) throws InterruptedException {
		cliente.acquire();																								// El cliente bloquea a los demas (para que ni entren ni salgan a la vez que el).
		MUTEX.acquire();																								// El cliente bloquea el aseo (seccion critica).

		nClientes--;
		System.out.println("[<-----]	Cliente #"+id+"		Aseos:	"+nClientes);

		if(nClientes == 0){																								// El cliente comprueba si es el ultimo.
			limpieza.release();																								// Si es asi, desbloquea al equipo de limpieza para que pueda entrar.
		}

		MUTEX.release();																								// El cliente desbloquea el aseo (seccion critica).
		cliente.release();																								// El cliente desbloquea a los demas.
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos:
	 * El equipo de trabajo esta solo en los aseos, es decir, espera hasta que
	 * no haya ningun cliente.
	 */
    public void entraEquipoLimpieza() throws InterruptedException {
		limpieza.acquire();																								// El equipo de limpieza bloquea a los demas (para que no entren mas de uno a la vez).
		MUTEX.acquire();																								// El equipo de limpieza bloquea el aseo (seccion critica).

		cliente.acquire();																								// El equipo de limpieza bloquea a los clientes para que no se genere una cola.

		limpiando = true;																								// Se indica que el aseo se esta limpiando (afecta en "entroAseo()").
		System.out.println("\n"+"[----->]	EQUIPO	  DE	LIMPIEZA.");

		limpieza.release();																								// El equipo de limpieza desbloquea a los demas.
		MUTEX.release();																								// El equipo de limpieza desbloquea el aseo (seccion critica).

		/*
		Debe bloquearse el semaforo de los clientes ya que de no hacerlo, estos se generarian y entrarian al aseo,
		pero no se notificaria por pantalla. De hecho, al salir el equipo de limpieza y entrar otro cliente,
		apareceria que en el aseo ya hay mas de un cliente, pero, ¿como? Si el equipo de limpieza acaba de salir.
		 */

    }

    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos.
	 */
    public void saleEquipoLimpieza() throws InterruptedException {
		limpieza.acquire();																								// El equipo de limpieza bloquea a los demas (para que no entren mas de uno a la vez).
		MUTEX.acquire();																								// El equipo de limpieza bloquea el aseo (seccion critica).

		System.out.println("[<-----]	EQUIPO	  DE	LIMPIEZA."+"\n");
		limpiando = false;																								// Se indica que el aseo ha terminado de limpiarse (afecta en "entroAseo()").

		MUTEX.release();																								// El equipo de limpieza desbloquea el aseo (seccion critica).
		cliente.release();																								// El equipo de limpieza desbloquea a los clientes para que puedan ir usando el aseo.

		/*
		 En este metodo no se desbloquea el equipo de limpieza, y eso es porque lo desbloquea el ultimo cliente
		 que sale del aseo (en "salgoAseo()"). Debido a esto, en el metodo "entraEquipoLimpieza()" no se necesita
		 una condicion de "nClientes == 0", porque ese metodo ya de por si solo puede ejecutarse cuando no hay
		 clientes en el aseo.
		 */
	}
}
