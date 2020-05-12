package Septiembre2016Monitores;

import java.util.concurrent.locks.*;

public class Mesa {
	/**
	 * N es el número de N que intervienen
	 */
	private int pendientes;
	private int ganador;
	private int N;
	
	private Lock l = new ReentrantLock(true);
	private Condition jugador =l.newCondition();
	
	private int nCara =0;
	private int nCruz =0;
	private int ganCara;
	private int ganCruz;
	
	public Mesa(int N) {
		pendientes = N;
		ganador=N;
		this.N =N;
	}

	/**
	 *
	 * @param id
	 *            del jugador que llama al método
	 * @param cara
	 *            : true si la moneda es nCara, false en otro caso
	 * @return un valor entre 0 y N. Si devuelve N es que ningún jugador ha
	 *         ganado y hay que repetir la partida. Si devuelve un número menor
	 *         que N, es el id del jugador ganador.
	 *
	 *         Un jugador llama al método nuevaJugada cuando quiere poner el
	 *         resultado de su tirada (nCara o nCruz) sobre la mesa. El jugador
	 *         deja su resultado y, si no es el último, espera a que el resto de
	 *         los N pongan sus jugadas sobre la mesa. El último jugador
	 *         comprueba si hay o no un ganador, y despierta al resto de
	 *         N
	 * @throws InterruptedException 
	 *
	 */
	public int nuevaJugada(int id, boolean cara) throws InterruptedException {
		l.lock();
		try{
			pendientes--;

			if(cara){
				System.out.println("Jugador: "+id+" ha sacado nCara");
				nCara++;
				ganCara =id;

			}else{
				System.out.println("Jugador: "+id+" ha sacado nCruz");
				nCruz++;
				ganCruz =id;
			}

			while(pendientes >0){
				jugador.await();
			}

			if(pendientes ==0){
				if(this.nCara ==1){
					ganador= ganCara;
					System.out.println("Ganador Jugador "+id);

				}else if (nCruz ==1){
					ganador= ganCruz;
					System.out.println("Ganador Jugador "+id);

				}else{
					System.out.println("No ha habido ningun ganador");
				}

				nCara =0;
				nCruz =0;
				pendientes = N;
				jugador.signalAll();
			}

			return ganador;

		}finally{
			l.unlock();
		}
	}
}