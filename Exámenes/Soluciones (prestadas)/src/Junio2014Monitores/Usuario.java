package Junio2014Monitores;


import java.util.*;
public class Usuario extends Thread{
	private static Random r = new Random();
	private int id;
	private GestorImpresoras g;

	public Usuario(int id,GestorImpresoras g){
		this.id = id;
		this.g = g;
	}
	
	/**
	 * El usuario imprime varios trabajos
	 */
	public void run(){
		while (true){
			//realiza tareas propias
			// pide al gestor que le imprima un trabajo
			g.imprimir(id);
		}
	}

}