package SemaforoBinario;

import BASE.*;
import java.util.concurrent.Semaphore;

public class BufferBinarios implements Buffer {
	
	private int N = 5;
	private int[] buffer = new int[N];
	private int i = 0, j = 0;
	private int numElem = 0;
	private int numHuecos = N;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore esperaHuecos = new Semaphore(1);
	private Semaphore esperaDatos = new Semaphore(1);
	
	public  void almacenar(int dato) throws InterruptedException{
		esperaHuecos.acquire();
		mutex.acquire();
		numHuecos--;
		System.out.println("[+] Productor produce "+dato);
		buffer[i] = dato;
		i = (i+1)%N;
		numElem++;
		if (numElem == 1) esperaDatos.release();
		if (numHuecos>0) esperaHuecos.release();
		mutex.release();
	}
	public  int extraer() throws InterruptedException{
		esperaDatos.acquire();
		mutex.acquire();
		numElem--;
		int dato = buffer[j];
		System.out.println("[-] Consumidor consume "+dato);
		j = (j+1)%N;
		numHuecos++;
		if (numHuecos == 1) esperaHuecos.release();
		if (numElem > 0) esperaDatos.release();
		mutex.release();
		return dato;
	}

}