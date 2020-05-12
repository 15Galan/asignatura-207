package Monitores;

import java.util.Random;

public class Productor extends Thread{

	private Peterson monitor;
	private Buffer buffer;
	private Random r;

	public Productor(Buffer buffer, Peterson monitor){

		r = new Random();
		this.buffer = buffer;
		this.monitor = monitor;
	}

	public void run(){

		while(true){
			int i = r.nextInt(1024);

			System.out.println("[Productor ] Dato producido: " + i + ".");

			monitor.preProt1();
			buffer.add(i);
			monitor.postProt1();
		}
	}
}
