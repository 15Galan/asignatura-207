package Parcial2013Monitores;
/**
 * autor:
 * maquina:
 */

import java.util.*;
import java.util.concurrent.locks.*;
public class Tienda {
	
	
	private static Random r = new Random();
	
	private static int nDescarado=0;
	private static int nAmable=0;
	private static Lock l =new ReentrantLock(true);
	private static Condition cDescarado=l.newCondition();
	private static Condition cAmable=l.newCondition();
	private static Condition cDependiente=l.newCondition();
	private static boolean despachando=false;
	private static boolean dormido=true;
	
	/** llamado por el cliente id amable cuando quiere su racion de
	*   queso. Cuando el metodo termina, el cliente tiene que tener
	*   su racion
	 * @throws InterruptedException 
	*/
	private static void qQuesoAmable(int id) throws InterruptedException {
		l.lock();
		try{
			nAmable++;
			cDependiente.signal();
			dormido=false;
			while(nDescarado>0 || despachando){
				cAmable.await();
			}
			despachando=true;
			System.out.println("Cliente amable "+ id +" atendido");

		}finally{
			l.unlock();
		}
		
	}
	/** llamado por el cliente id descarado cuando quiere su racion de
	*   queso. Cuando el metodo termina, el cliente tiene que tener
	*   su racion
	 * @throws InterruptedException 
	*/

	private static void qQuesoDescarado(int id) throws InterruptedException{
		l.lock();
		try{
			nDescarado++;
			cDependiente.signal();
			dormido=false;
			while(despachando){
				cDescarado.await();
			}
			despachando=true;
			System.out.println("Cliente descarado "+ id +" atendido");
		}finally{
			l.unlock();
		}
	
	}
	/** llamado por el dependiente para atender a un cliente.  Si hay 	*   clientes debe atender primero a los descarados y luego a los 		*   amables. Si no hay clientes, debe esperar dormido.
	 * @throws InterruptedException 
	*/

	private static void siguiente() throws InterruptedException{
		l.lock();
		try{
		despachando=false;
		if(nAmable==0 && nDescarado==0 && !dormido){
			cDependiente.await();
			dormido=true;
			System.out.println("Dependiente se duerme");
		}
		if(nDescarado>0){
			nDescarado--;
			cDescarado.signal();
			
		}
		if(nAmable>0 && nDescarado==0){
			nAmable--;
			cAmable.signal();
		}
		}finally{
			l.unlock();
		}
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
		ClienteDescarado[] cd = new ClienteDescarado[10];
		ClienteAmable[] ca = new ClienteAmable[10];
		Dependiente d = new Dependiente();

		for (int i = 0; i<cd.length; i++){
			cd[i] = new ClienteDescarado(i);
		}
		for (int i = 0; i<ca.length; i++){
			ca[i] = new ClienteAmable(i);
		}
		d.start();
		for (int i = 0; i<cd.length; i++){
			cd[i].start();
		}
		for (int i = 0; i<ca.length; i++){
			ca[i].start();
		}

	
	}

}