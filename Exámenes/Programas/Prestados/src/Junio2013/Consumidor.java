package Junio2013;

public class Consumidor extends Thread {

	private Buffer buffer;
	
	public Consumidor( Buffer buffer){
		this.buffer=buffer;
	}
	
	public void run(){
		while(true){
			try {
				buffer.leerDato();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
