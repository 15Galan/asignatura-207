package Monitores;

import BASE.*;

public class BufferMonitor implements Buffer {

	private int N = 5;
	private int[] buffer = new int[N];
	private int i = 0, j = 0;
	private int numElem = 0;
	
	public synchronized void almacenar(int dato) throws InterruptedException{
		while (numElem == N)
			wait();
		System.out.println("Productor produce "+dato);
		buffer[i] = dato;
		i = (i+1)%N;
		numElem++;
	
		notify();
	}
	public synchronized int extraer() throws InterruptedException{
		while (numElem == 0)
			wait();
		
		int dato = buffer[j];
		j = (j+1)%N;
		numElem--;
		System.out.println("Consumidor consume "+dato);
		notify();
		return dato;
	}
}
