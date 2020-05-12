import java.util.concurrent.locks.*;
import java.util.*;
public class Tienda {
	
	private static Lock l = new ReentrantLock(true);
	private static Condition esperaDescarado = l.newCondition();
	private static Condition esperaAmable = l.newCondition();
	private static Condition esperaDependiente = l.newCondition();
	private static int nDescarados = 0, nAmables = 0;
	private static boolean ocupado = false;
	private static Random r = new Random();
	private static int total = 0;
	
	private static void qQuesoAmable(int id) throws InterruptedException{
		l.lock();
		try{
			nAmables++;
			esperaDependiente.signal();
			if (ocupado) esperaAmable.await();
			ocupado = false;
			System.out.println("Cliente amable "+id+" atendido");
		} finally{
			l.unlock();
		}
	}
	
	private static void qQuesoDescarado(int id) throws InterruptedException{
	
		l.lock();
		try{
			nDescarados++;
			esperaDependiente.signal();
			if (ocupado) esperaDescarado.await();
			ocupado = true;
			System.out.println("Cliente descarado "+id+" atendido");
			
		} finally{
			l.unlock();
		}
	}
	
	private static void siguiente() throws InterruptedException{
		l.lock();
		try{
			ocupado = false;
			if (nDescarados + nAmables == 0){
				System.out.println("Dependiente duerme");
				esperaDependiente.await();
			}
				
			total++;
			if (nDescarados != 0){
				System.out.println("Dependiente Cliente descarado  atendido "+total);
				nDescarados--;
				esperaDescarado.signal();
			} else {
				System.out.println("Dependiente Cliente amable  atendido "+total);
				nAmables--;
				esperaAmable.signal();
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
		/**
		 * 
		 */
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