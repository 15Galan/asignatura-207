package Junio2014Monitores;

import java.util.concurrent.locks.*;

public class GestorImpresoras {

	private final int NUM_IMP;
	private final int NUM_PROC;
	private Lock l=new ReentrantLock(true);
	Condition cImprimiendo=l.newCondition();
	Condition cEspera=l.newCondition();
	
	private Impresora[] impresora;
	
	
	public GestorImpresoras(Impresora[] imp, int numProc){
		this.NUM_IMP = imp.length;
		this.NUM_PROC = numProc;
		this.impresora = imp;
		//inicializacion de las variables locales
	}
	
	/**
	 * Los usuarios que quieren imprimir llaman a este metodo
	 * @param id del usuario que llama
	 * El usuario debe esperar hasta que alguna impresora ha impreso su trabajo
	 */
	public  void imprimir(int id) {
		l.lock();
		try{
			
			
		}finally{
			l.unlock();
		}
	}
	
	/**
	 * Las impresoras llaman a este metodo cuando han terminado de imprimir un
	 * trabajo
	 * @param imp, id de la impresora que llama
	 */
	public  void finTrabajo(int imp){
	
	}
}