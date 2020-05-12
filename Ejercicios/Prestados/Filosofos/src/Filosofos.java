import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofos {
	public static class Filosofo extends Thread{

		private static Semaphore ten[] = new Semaphore[5];
		private int id;
		private static Semaphore sillas = new Semaphore(4, true);
		private Random rnd = new Random();

		public Filosofo(int id) {
			this.id = id;
		}

		static {
			for (int i = 0; i < 5; i++) {
				ten[i] = new Semaphore(1, true);
			}
		}

		public void run() {
			while (true) {
				try {
					sillas.acquire();
					ten[id].acquire();
					ten[(id + 1) % 5].acquire();
					System.out.println("El filosofo "+id+"entra a comer");
					Thread.sleep(rnd.nextInt(200));//come
					ten[(id+1)%5].release();
					ten[id].release();
					System.out.println("El filosofo "+id+"deja de comer");
					sillas.release();
					Thread.sleep(rnd.nextInt(500));//piensa
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String args[]){
		Filosofo f[]=new Filosofo[5];
		
		for(int i=0;i<5;i++){
			f[i]=new Filosofo(i);
		}
		for(int i=0;i<5;i++){
			f[i].start();
		}
		
	}
}
