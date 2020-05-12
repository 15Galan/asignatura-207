/**
 * autor:
 * máquina:
 */
public class Planta {

	private static final int TamSala = 3;
	private static final int NumHerramientas = 5;
	private static final int HerramientasPorTrabajador = 2;
	private static final int NumTrabajadores = 10;
	private static final int TiempoDescanso = 2000;
	private static final int TiempoEnSala = 1000;


	/** llamado por el trabajador id cuando quiere entrar
	*  en la sala contaminda. Cuando entre tiene que haber cogido dos 	*  herramientas y debe haber a lo sumo tres clientes en la sala
	*/
	private static void entraSala(int id) {

	}
	/** llamado por el trabajador id antes de salir
	*  de la sala contaminada. Cuando salga tiene que haber devuelto 	  	*  las dos herramientas que ten�a
	*/
	private static void saleSala(int id){

	}
	/** llamado por el contador para mostrar un mensaje en la pantalla
	*   cada vez que entran 10 clientes nuevos
	*/
	private static void diezMas(){

	}

	public static class Trabajador extends Thread{
		private int id;
		public Trabajador(int id){
			this.id = id;
		}

		public void run(){
			try {
				while(true){
					entraSala(id);
					Thread.sleep(TiempoEnSala);
					saleSala(id);
					Thread.sleep(TiempoDescanso);
				}
			}catch(InterruptedException ie){

			}
		}
	}
	public static class Contadora extends Thread{


		public void run(){
			try{
				while (true){
					diezMas();
				}

			}catch (InterruptedException ie){

			}
		}

	}

	public static void main(String[] str){


	}

}
