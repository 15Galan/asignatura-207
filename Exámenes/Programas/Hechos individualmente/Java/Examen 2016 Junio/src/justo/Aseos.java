package justo;

import java.util.concurrent.Semaphore;

public class Aseos {
	private int clientes = 0;
	private Semaphore aseosOcupados = new Semaphore(1, true);
	private Semaphore limpiando = new Semaphore(1, true);
	private Semaphore mutex			= new Semaphore(1, true);
	private Semaphore mutex2		= new Semaphore(1, true);

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{

		aseosOcupados.acquire();

		mutex2.acquire();
			mutex.acquire();
				clientes++;

				if(clientes == 1){
					limpiando.acquire();		// Bloqueo el semáforo de limpiar
				}

				System.out.println("[injusto.Cliente #"+id+"]	Entra en el aseo.		Clientes dentro: "+clientes+".");

			mutex.release();
		mutex2.release();

		aseosOcupados.release();
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException {

		mutex.acquire();
			clientes--;

			if (clientes == 0) {
				limpiando.release();
			}

			System.out.println("[injusto.Cliente #"+id+"]	Sale del aseo.			Clientes dentro: "+clientes+".");

		mutex.release();
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException {

    	aseosOcupados.acquire();
		limpiando.acquire();			// Me aseguro de, si está abierto, bloquear el semáforo

		System.out.println("[Limpieza]		Entra.");
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza() throws InterruptedException{

		System.out.println("[Limpieza]		Sale.");

		limpiando.release();
		aseosOcupados.release();
	}
}
