package Junio2016;

import java.util.concurrent.*;
public class AseosInjusto {
	private int nClientes=0;
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore aseoOcupado=new Semaphore(1,true);
	private Semaphore mutex2=new Semaphore(1,true);
	
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{
		mutex2.acquire();
		mutex.acquire();
		nClientes++;
		if(nClientes==1){//Cuando llega el primer cliente indica que el aseo esta ocupado, los demas clientes entran sin problemas 		
				//si el primer cliente llega y aseoOcupado esta a 0 se bloquea y los demas clientes se bloquearan en el mutex
				//cuando el equipo de limpieza salga desbloqueara aseoOcupado y luego los demas clientes se desbloquearan en cascada
			aseoOcupado.acquire();
		}
		System.out.println("Entra cliente "+ id);
		mutex.release();
		mutex2.release();
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException{
		mutex.acquire();
		nClientes--;
		if(nClientes==0){
			aseoOcupado.release();
		}
		System.out.println("Sale cliente "+ id);
		mutex.release();
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException 
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
		aseoOcupado.acquire();
		System.out.println("Entra equipo de limpieza");
    }
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
     * @throws InterruptedException 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza() throws InterruptedException{
    	aseoOcupado.release();
		System.out.println("Sale equipo de limpieza");
	}
}
