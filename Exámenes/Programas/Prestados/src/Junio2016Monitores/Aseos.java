package Junio2016Monitores;

import java.util.concurrent.locks.*;

public class Aseos {
		private Lock l=new ReentrantLock(true);
		private Condition esperaCliente=l.newCondition();//CS2
		private Condition esperaEquipo=l.newCondition();//Cs1
		
		private boolean estaEquipo=false; //CS2
		private int nClientes=0;//CS1
		
		private boolean equipoEsperando=false; //para la version justa
	
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{
		l.lock();
		try{
			while(estaEquipo || equipoEsperando){ //la segunda parte de la condicion es para la version justa
				esperaCliente.await();
			}
			nClientes++;
			System.out.println("[Cliente #"+id+"]	Entra.	Clientes dentro: "+nClientes+".");
		}finally{
			l.unlock();
		}
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException{
		l.lock();
		try{
			nClientes--;
			System.out.println("[Cliente #"+id+"]	Sale.	Clientes dentro: "+nClientes+".");

			if(nClientes==0){
				esperaEquipo.signal();
			}
		}finally{
			l.unlock();
		}
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException 
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
		l.lock();
		try{
			equipoEsperando=true;//para la version justa
			while(nClientes>0){
				esperaEquipo.await();
			}
			estaEquipo=true;
			System.out.println("[Limpieza]	Entra.");

		}finally{
			l.unlock();
		}
    }
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
     * @throws InterruptedException 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza() throws InterruptedException{
    	l.lock();
    	try{
    		estaEquipo=false;
    		equipoEsperando=false;//para la version justa
			System.out.println("[Limpieza]	Sale.");
    		esperaCliente.signalAll();
    	}finally{
    		l.unlock();
    	}
	}
}
