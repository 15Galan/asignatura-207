import java.util.concurrent.*;

public class BaseDeDatos {

	private static Semaphore mutex1=new Semaphore(1,true);
	private static Semaphore escribiendo=new Semaphore(1,true);
	private static Semaphore leyendo=new Semaphore(1,true);
	private static Semaphore mutex2=new Semaphore(1,true);
	private static int nLectores=0;
	private static int nEscritores=0;
	
	
	public static class Escritor extends Thread{
		private int id;
		public Escritor(int id){
			this.id=id;
			start();
		}
		
		public void run(){
			while(true){
				try {
					mutex2.acquire();
					
					mutex2.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public static void entraEscritor(int id) throws InterruptedException{
				mutex2.acquire();
				nEscritores++;
				if(nEscritores==1){
					leyendo.acquire();
				}
				mutex2.release();
				escribiendo.acquire();
		}
		
		public static void saleEscritor(int id) throws InterruptedException{
				mutex2.acquire();
				nEscritores--;
				if(nEscritores==0){
					leyendo.release();
				}
				mutex2.release();
				escribiendo.release();
		}
	}
	
	public static class Lector extends Thread{
		private int id;
		public Lector(int id){
			this.id=id;
			start();
		}
		
		public void run(){
			while(true){
				try {
					mutex1.acquire();
					nLectores++;	
					mutex1.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public static void entraLector(int id) throws InterruptedException{
			leyendo.acquire();
			mutex1.acquire();
			nLectores++;
			if(nLectores==1){
				escribiendo.acquire();
			}
			mutex1.release();
			leyendo.release();
		}
		
		public static void saleLector(int id) throws InterruptedException{
			mutex1.acquire();
			nLectores--;
			if(nLectores==0){
				escribiendo.release();
			}
			mutex1.release();
		}
	}
}
