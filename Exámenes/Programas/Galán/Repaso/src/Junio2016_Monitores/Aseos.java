package Junio2016_Monitores;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Aseos {

	private int nClientes = 0;												// Clientes dentro del aseo.

	private boolean limpiando = false;										// Equipo de limpieza ocupado.				// Vale "false" porque supongo que el aseo empieza limpio.

	private Lock l = new ReentrantLock(true);
	private Condition cliente 	= l.newCondition();
	private Condition limpieza 	= l.newCondition();

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos:
	 * El cliente espera si el equipo de limpieza esta trabajando o
	 * esta esperando para poder limpiar los aseos.
	 */
	public void entroAseo(int id) throws InterruptedException {
		l.lock();

		try{
			while(limpiando){																							// Mientras el equipo de limpieza este:
				cliente.await();																							// El cliente espera.
			}

			nClientes++;																								// Una vez el aseo este limpio (o si ya lo esta) entra el cliente.
			System.out.println("[----->]	Cliente	#"+id+"		Aseos:	"+nClientes);

		}finally {
			l.unlock();
		}

		/*
		El uso de monitores (locks) siempre debe acompañarse de la funcion "while" y no "if", ya que
		estos funcionan de una forma distinta a los semaforos: los monitores requieren una supervision
		continua de la condicion a evaluar, lor ese motivo las condiciones deben evaluarse con "while".
		 */
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos.
	 */
	public void salgoAseo(int id) throws InterruptedException {
		l.lock();

		try{
			nClientes--;																								// El cliente se marcha del aseo.
			System.out.println("[<-----]	Cliente #"+id+"		Aseos:	"+nClientes);

			if(nClientes == 0){																							// Si es el ultimo:
				limpieza.signal();																							// Envia una señal al equipo de limpieza para que deje de esperar.
			}

		}finally{
			l.unlock();
		}

	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos:
	 * El equipo de trabajo esta solo en los aseos, es decir, espera hasta que
	 * no haya ningun cliente.
	 */
    public void entraEquipoLimpieza() throws InterruptedException {
		l.lock();

		try{
			while(nClientes > 0){																						// Mientras que haya clientes en el aseo:
				limpieza.await();																							// El equipo de limpieza espera.
			}

			limpiando = true;																							// Una vez deje de esperar, empieza a limpiar (y hace esperar a los clientes en "entroAseo()").

			System.out.println("\n"+"[----->]	EQUIPO	  DE	LIMPIEZA.");

		}finally{
			l.unlock();
		}
    }

    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos.
	 */
    public void saleEquipoLimpieza() throws InterruptedException {
		l.lock();

		try{
			limpiando = false;																							// Una vez todos los clientes estan activos, indica que ya no esta limpiando (afecta en "entroAseo()").

			cliente.signalAll();																						// El equipo de limpieza indica a todos los clientes (esperando) que dejen de esperar.


			System.out.println("[<-----]	EQUIPO	  DE	LIMPIEZA."+"\n");

		}finally{
			l.unlock();
		}

		/*
		El orden de los codigos de las lineas 93 y 95 no importa mucho, pero despertando primero a los clientes
		me aseguro de que estos empiecen a ponerse en cola, ya que se detendran en el "while" de su metodo de
		entrada al aseo, ya que mientras no se indique que el equipo esta fuera =limpiando = false" no entraran.
		Asi se evita que otro equipo de limpieza aproveche y se meta antes de que entre un cliente.
		 */
	}
}
