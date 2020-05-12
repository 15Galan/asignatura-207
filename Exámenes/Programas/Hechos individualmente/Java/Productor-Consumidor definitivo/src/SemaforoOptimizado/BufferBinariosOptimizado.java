package SemaforoOptimizado;

import java.util.concurrent.*;

public class BufferBinariosOptimizado implements BASE.Buffer {
	
	private int N = 5;
	private int[] buffer = new int[N];
	private int i = 0, j = 0;
	private int numElem = 0;
	private int numHuecos = N;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore esperaHuecos = new Semaphore(0);
	private Semaphore esperaDatos = new Semaphore(0);
	
	public  void almacenar(int dato) throws InterruptedException{
		mutex.acquire();
		numHuecos--;
		if (numHuecos==-1){
			mutex.release();
			esperaHuecos.acquire();
			mutex.acquire();
		}
		System.out.println("Productor produce "+dato);
		buffer[i] = dato;
		i = (i+1)%N;
		numElem++;
		if (numElem == 0) esperaDatos.release();
		mutex.release();
	}
	public  int extraer() throws InterruptedException{
		mutex.acquire();
		numElem--;
		if (numElem==-1){
			mutex.release();
			esperaDatos.acquire();
			mutex.acquire();
		}
		int dato = buffer[j];
		System.out.println("Consumidor consume "+dato);
		j = (j+1)%N;
		numHuecos++;
		if (numHuecos == 0) esperaHuecos.release();
		mutex.release();
		return dato;
	}

}
