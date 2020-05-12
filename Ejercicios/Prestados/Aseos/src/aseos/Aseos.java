package aseos;

import java.util.concurrent.locks.*;

public class Aseos {

	private Lock l = new ReentrantLock(true);
	private Condition cCliente = l.newCondition();
	private Condition cEquipo = l.newCondition();

	boolean equipo = false;
	private boolean limpiando=false;
	boolean puertaAbierta = false;

	private int nClientes = 0;

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos CS Version
	 * injusta: El cliente espera si el equipo de limpieza est� trabajando CS
	 * Version justa: El cliente espera si el equipo de limpieza est� trabajando
	 * o est� esperando para poder limpiar los aseos
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException {
		l.lock();
		try {

			while (equipo ||limpiando) {
				cCliente.await();
			}
			nClientes++;
			System.out.println("Cliente " + id + " entra al aseo. Clientes en el aseo "+nClientes);
		} finally {
			l.unlock();
		}
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 */
	public void salgoAseo(int id) {
		l.lock();
		try {
			nClientes--;
			System.out.println("Cliente " + id + " sale del aseo. Clientes en el aseo "+nClientes);
			if (equipo && nClientes == 0) {
				puertaAbierta = false;
				cEquipo.signal();
			}
		} finally {
			l.unlock();
		}
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos CS:
	 * El equipo de trabajo est� solo en los aseos, es decir, espera hasta que
	 * no haya ning�n cliente.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void entraEquipoLimpieza() throws InterruptedException {
		l.lock();
		try {
			equipo = true;
			System.out.println("Equipo de limpieza quiere entrar");
			while (nClientes > 0) {
				cEquipo.await();
			}
			System.out.println("Equipo de limpieza entra a limpiar");

			limpiando=true;
		} finally {
			l.unlock();
		}
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando sale de los aseos
	 * 
	 * 
	 */
	public void saleEquipoLimpieza() {
		l.lock();
		try {
			equipo = false;
			limpiando=false;
			puertaAbierta = true;
			System.out.println("Equipo de limpieza termina de limpiar");
			cCliente.signalAll();
		} finally {
			l.unlock();

		}
	}
}
