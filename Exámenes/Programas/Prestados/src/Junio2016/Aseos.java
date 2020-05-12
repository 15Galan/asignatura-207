package Junio2016;

import java.util.concurrent.*;
//--------------------------------------------------------------------------
//							ESTA ES LA VERSION JUSTA
//--------------------------------------------------------------------------

public class Aseos {
	private int nClientes=0;
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore aseoOcupado=new Semaphore(1,true);
	private Semaphore equEspera=new Semaphore(1,true);//El equipo no espera
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{
		mutex.acquire();

		nClientes++;

		if(nClientes==1){				// Cuando llega el primer cliente indica que el aseo esta ocupado, los demas clientes entran sin problemas.
										// Si el primer cliente llega y "aseoOcupado = 0", se bloquea y los demas clientes se bloquearan en el mutex.
			aseoOcupado.acquire();		// Cuando el equipo de limpieza salga, desbloqueara "aseoOcupado" y luego los demas clientes se desbloquearan en cascada.
		}

		System.out.println("[Cliente #"+id+"] Entra en el aseo.		Clientes dentro: "+nClientes+".");

		mutex.release();
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException{
		equEspera.acquire();
		mutex.acquire();

		nClientes--;

		if(nClientes==0){
			aseoOcupado.release();
		}

		System.out.println("[Cliente #"+id+"] Sale del aseo.		Clientes dentro: "+nClientes+".");

		mutex.release();
		equEspera.release();
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException 
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
		equEspera.acquire();		// El equipo esta esperando
    	aseoOcupado.acquire();

		System.out.println("[Limpieza] Entra.");
    }
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
     * @throws InterruptedException 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza() throws InterruptedException{
    	aseoOcupado.release();

		System.out.println("[Limpieza] Sale.");

		equEspera.release();
	}
}
