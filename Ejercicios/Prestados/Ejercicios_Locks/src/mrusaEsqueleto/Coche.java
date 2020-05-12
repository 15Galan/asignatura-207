package mrusaEsqueleto;

import java.util.concurrent.locks.*;

public class Coche extends Thread {

	Lock l = new ReentrantLock(true);
	private int tam;
	private int numPas = 0;

	private boolean cocheLLeno = false;
	private Condition cCocheLLeno = l.newCondition();

	private boolean pAbierta = true;
	private Condition cpAbierta = l.newCondition();

	private boolean finViaje = false;
	private Condition cfinViaje = l.newCondition();

	public Coche(int tam) {
		this.tam = tam;
	}

	public Coche() {
		tam = 5;
	}

	public void subir(int id) throws InterruptedException {
		// id del pasajero que se sube al coche
		l.lock();
		try {
			while (!pAbierta) {
				cpAbierta.await();
			}
			numPas++;
			System.out.println("Sube pasajero " + id);

			if (numPas == tam) {
				pAbierta = false; // cierro la puerta
				cocheLLeno = true;
				finViaje=false;
				cCocheLLeno.signal();
				System.out.println("\nCoche de paseo\n");
			}

		} finally {
			l.unlock();
		}
	}

	public void bajar(int id) throws InterruptedException {
		// id del pasajero que se baja del coche
		l.lock();
		try {
			while (!finViaje) {
				cfinViaje.await();
			}
			numPas--;
			cfinViaje.signal();
			System.out.println("Se baja pasajero " + id);
			if (numPas == 0) {
				pAbierta = true;
				cpAbierta.signal();
				cocheLLeno = false;
				finViaje=false;
				System.out.println("\nfin del viaje\n");
			}

		} finally {
			l.unlock();
		}
	}


	private void esperaLleno() throws InterruptedException {
		// el coche espera a que este lleno para dar una vuelta
		l.lock();
		try {
			while (!cocheLLeno ) {
				cCocheLLeno.await();
			}
			//aqui daria la vuelta
			finViaje=true;
			cfinViaje.signal();
			cocheLLeno=false;
	
			
		} finally {
			l.unlock();
		}
	}

	public void run() {
		while (true)
			try {
				this.esperaLleno();

			} catch (InterruptedException ie) {
			}

	}
}