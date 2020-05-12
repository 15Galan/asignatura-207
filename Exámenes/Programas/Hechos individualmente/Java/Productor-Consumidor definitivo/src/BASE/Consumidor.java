package BASE;

public class Consumidor extends Thread{
	private Buffer b;
	public Consumidor(Buffer b){
		this.b = b;
	}
	
	
	public void run(){
		for (int i = 0; i<1000; i++){	
			try {
				b.extraer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//consumiendo el nuevo dato
		}
	}
}
