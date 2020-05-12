package impresorasMonitor;

public class GestorImpresoras {

	private final int NUM_IMP;
	private final int NUM_PROC;

	private Impresora[] impresora;
	public GestorImpresoras(Impresora[] imp, int numProc){
		this.NUM_IMP = imp.length;
		this.NUM_PROC = numProc;
		this.impresora = imp;
		//inicialización de las variables locales
	}

	/**
	 * Los usuarios que quieren imprimir llaman a este m�todo
	 * @param id del usuario que llama
	 * El usuario debe esperar hasta que alguna impresora ha impreso su trabajo
	 */
	public  void imprimir(int id) {

	}

	/**
	 * Las impresoras llaman a este método cuando han terminado de imprimir un
	 * trabajo
	 * @param imp, id de la impresora que llama
	 */
	public  void finTrabajo(int imp){

	}
}
