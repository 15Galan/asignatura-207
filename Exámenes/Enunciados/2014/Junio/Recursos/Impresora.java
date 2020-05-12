package impresorasMonitor;



public class Impresora extends Thread{


	private int id;
	private GestorImpresoras g;

	public Impresora(int id,GestorImpresoras g){
		this.id = id;
		this.g = g;
	}
	/**
	 * El gestor llama a este método cuando quiere asignarle un trabajo
	 * a la impresora this.
	 * @param id del proceso usuario al que pertenece el trabajo a imprimir.
	 *
	 */
	public  void nuevoTrabajo(int id) {

	}

	/**
	 * M�todo privado de la impresora. La impresora this lo llama para esperar
	 * hasta que el gestor le asigna un nuevo trabajo
	 */
	private  void espera() {

	}


	public void run(){
		while (true){

				this.espera(); // espera a que el gestor le asigne un trabajo

				//imprime el trabajo que le asigna el gestor
				//indica al gestor que ha terminado su trabajo


		}
	}

}
