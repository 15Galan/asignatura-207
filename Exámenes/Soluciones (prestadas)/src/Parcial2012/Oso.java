package Parcial2012;

public class Oso extends Thread{
	
	private Tarro tarro;
	public Oso(Tarro tarro){
		this.tarro = tarro;
	}
	
	public void run(){
		boolean fin = false;
		while(true){
			try {
				tarro.comoMiel();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				fin = true;
			}
		}
	}

}
