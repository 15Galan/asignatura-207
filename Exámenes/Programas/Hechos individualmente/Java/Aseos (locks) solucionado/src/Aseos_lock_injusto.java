import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Version injusta: El cliente espera solo si el equipo de limpieza est� trabajando
 */
public class Aseos_lock_injusto implements IAseo {
	private int numClientes = 0;
	private List<Integer> aseos = new ArrayList<>();

	private Lock lock = new ReentrantLock(true);
	private Condition esperaVacio = lock.newCondition();
	
	public List<Integer> getAseos() {
		return aseos;
	}

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera solo si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void entroAseo(int id){
		lock.lock();
		numClientes++;
		aseos.add(id);
		System.out.println(aseos +" : "+ numClientes);
		lock.unlock();

	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void salgoAseo(int id){
		lock.lock();
		aseos.remove(Integer.valueOf(id));
		numClientes--;
		System.out.println(aseos +" : "+ numClientes);
		if(numClientes == 0) esperaVacio.signal();
		lock.unlock();
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException 
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
 		System.out.println("Llega Limpieza con clientes = " + numClientes);
 		lock.lock();
			try {
		 		if (numClientes > 0) esperaVacio.await();
			} catch (InterruptedException e) { // si equipo espera debe soltar el lock al ser interrumpido; no en finally porque el unlock esta en otro metodo
				lock.unlock();
				throw e; // relanzamos la misma interrupcion para que quien nos llamo haga uso de ella si lo necesita
			}
 		if (numClientes > 0) throw new AssertionError(numClientes);
		System.out.println("Entra Limpieza con clientes = " + numClientes);
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 */
    public void saleEquipoLimpieza(){
		System.out.println("Sale Limpieza con clientes = " + numClientes);
		if (numClientes > 0) throw new AssertionError(numClientes);
		lock.unlock();
 	}
}
