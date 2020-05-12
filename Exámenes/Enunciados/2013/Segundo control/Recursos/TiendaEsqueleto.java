/**
 * autor:
 * máquina:
 */

import java.util.*;
public class Tienda {


	private static Random r = new Random();

	/** llamado por el cliente id amable cuando quiere su raci�n de
	*   queso. Cuando el m�todo termina, el cliente tiene que tener
	*   su raci�n
	*/
	private static void qQuesoAmable(int id) {

	}
	/** llamado por el cliente id descarado cuando quiere su raci�n de
	*   queso. Cuando el m�todo termina, el cliente tiene que tener
	*   su raci�n
	*/

	private static void qQuesoDescarado(int id){

	}
	/** llamado por el dependiente para atender a un cliente.  Si hay 	*   clientes debe atender primero a los descarados y luego a los 		*   amables. Si no hay clientes, debe esperar dormido.
	*/

	private static void siguiente(){

	}

	public static class ClienteAmable extends Thread{
		private int id;
		public ClienteAmable(int id){
			this.id = id;
		}

		public void run(){

			try {
				Thread.sleep(r.nextInt(2000));
				qQuesoAmable(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static class ClienteDescarado extends Thread{
		private int id;
		public ClienteDescarado(int id){
			this.id = id;
		}

		public void run(){

			try {
				Thread.sleep(r.nextInt(2000));
				qQuesoDescarado(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static class Dependiente extends Thread{
		public void run(){
			while (true)
				try {
					siguiente();
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
	}

	public static void main(String[] args){
	}

}
