package Septiembre2016Monitores;

import java.util.concurrent.locks.*;

public class Mesa {
	/**
	 * N es el número de jugadores que intervienen
	 */
	private int nJugadores;
	private int ganador;
	private int jugadores;
	
	private Lock l = new ReentrantLock(true);
	private Condition cEspera=l.newCondition();
	
	private int car=0;
	private int cruz=0;
	private int posGanadorCara;
	private int posGanadorCruz;
	
	public Mesa(int N) {
		nJugadores = N;
		ganador=N;
		jugadores=N;
	}

	/**
	 *
	 * @param id
	 *            del jugador que llama al método
	 * @param cara
	 *            : true si la moneda es cara, false en otro caso
	 * @return un valor entre 0 y N. Si devuelve N es que ningún jugador ha
	 *         ganado y hay que repetir la partida. Si devuelve un número menor
	 *         que N, es el id del jugador ganador.
	 *
	 *         Un jugador llama al método nuevaJugada cuando quiere poner el
	 *         resultado de su tirada (cara o cruz) sobre la mesa. El jugador
	 *         deja su resultado y, si no es el último, espera a que el resto de
	 *         los jugadores pongan sus jugadas sobre la mesa. El último jugador
	 *         comprueba si hay o no un ganador, y despierta al resto de
	 *         jugadores
	 * @throws InterruptedException 
	 *
	 */
	public int nuevaJugada(int id, boolean cara) throws InterruptedException {
		l.lock();
		try{
			nJugadores--;

			if(cara){
				System.out.println("Jugador: "+id+" ha sacado cara");
				car++;
				posGanadorCara=id;

			}else{
				System.out.println("Jugador: "+id+" ha sacado cruz");
				cruz++;
				posGanadorCruz=id;
			}

			while(nJugadores>0){
				cEspera.await();
			}

			if(nJugadores==0){
				if(car==1){
					ganador=posGanadorCara;
					System.out.println("Ganador Jugador "+id);

				}else if (cruz==1){
					ganador=posGanadorCruz;
					System.out.println("Ganador Jugador "+id);

				}else{
					System.out.println("No ha habido ningun ganador");
				}

				car=0;
				cruz=0;
				nJugadores=jugadores;
				cEspera.signalAll();
			}

			return ganador;

		}finally{
			l.unlock();
		}
	}
}