package Junio2013;

public class ProductorPar extends Thread {

	private Buffer buffer;
	private int dato;
	
	public ProductorPar( Buffer buffer){
		this.buffer=buffer;
		dato=0;
	}
	
	public void run(){
		while(true){
			try {
				buffer.nuevoDatoPar(dato);
				dato=dato+2;
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
