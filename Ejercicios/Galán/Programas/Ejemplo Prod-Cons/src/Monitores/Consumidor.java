package Monitores;

public class Consumidor extends Thread {

	private Peterson monitor;
	private Buffer buffer;

	public Consumidor(Buffer buffer, Peterson monitor){

		this.buffer = buffer;
		this.monitor = monitor;
	}

	public void run(){

		while(true){
			monitor.preProt0();
			System.out.println("[Consumidor] Dato consumido: " + buffer.get() + ".");
			monitor.postProt0();
		}
	}

}
