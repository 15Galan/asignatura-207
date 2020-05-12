package Junio2013;

public class ProductorImpar extends Thread {

	private Buffer buffer;
	private int dato;
	
	public ProductorImpar( Buffer buffer){
		this.buffer=buffer;
		dato=1;
	}
	
	public void run(){
		while(true){
			try {
				buffer.nuevoDatoImpar(dato);
				dato=dato+2;
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
