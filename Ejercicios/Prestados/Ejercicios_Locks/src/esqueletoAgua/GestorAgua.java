package esqueletoAgua;

import java.util.concurrent.locks.*;

public class GestorAgua {

	private Lock l = new ReentrantLock(true);
	private Condition cAbiertaH = l.newCondition();
	private Condition cAbiertaO = l.newCondition();
	private Condition esperandoMolecula = l.newCondition();

	private boolean puertaH = true, puertaO = true;

	private int nH = 0, nO = 0;

	public void hListo(int id) throws InterruptedException {
		l.lock();
		try {
			while (!puertaH) {
				cAbiertaH.await();
			}
			nH++;
			System.out.println("LLega hidorgeno "+id);
			if (nH == 2) {
				puertaH = false;
			}

			if (nH == 2 && nO == 1) { // aqui estan los 3 procesos listos
				esperandoMolecula.signalAll();

			} else { // falta alguno
				esperandoMolecula.await();
			}

			nH--;
			System.out.println("Sale hidorgeno "+id);
			if (nH == 0 & nO == 0) {
				// soy el ultimo
				puertaH = true;
				puertaO =true;
				cAbiertaH.signalAll();
				cAbiertaO.signalAll();
				System.out.println("********************");
			}

		} finally {			// Bloqueo de la señal receptora.
			l.unlock();
		}

	}

	public void oListo(int id) throws InterruptedException {
		l.lock();
		try {
			while (!puertaO) {
				cAbiertaO.await();
			}
			nO++;
			System.out.println("LLega Oxigeno "+id);
			if (nO == 1) {	//se podria quitar porque seguro que es 1 pero hemos hecho copy paste del hidrogeno
				puertaO = false;
			}

			if (nH == 2 && nO == 1) { // aqui estan los 3 procesos listos

				esperandoMolecula.signalAll();

			} else { // falta alguno
				esperandoMolecula.await();
			}

			nO--;
			System.out.println("Sale Oxigeno "+id);
			if (nH == 0 & nO == 0) {
				// soy el ultimo
				puertaH = true;
				puertaO = true;
				cAbiertaH.signalAll();
				cAbiertaO.signalAll();
				System.out.println("********************");


			}

		} finally {
			l.unlock();
		}
}
}

