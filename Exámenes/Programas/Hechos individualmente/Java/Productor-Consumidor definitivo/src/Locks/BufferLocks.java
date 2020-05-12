package Locks;

import BASE.*;
import java.util.concurrent.locks.*;

public class BufferLocks implements Buffer {
	private Lock l = new ReentrantLock(true);
	private Condition esperaHuecos = l.newCondition();
	private Condition esperaDatos = l.newCondition();
	private int N = 5;
	private int[] buffer = new int[N];
	private int i = 0, j = 0;
	private int numElem = 0;
	
	public  int extraer() throws InterruptedException{
		l.lock();
		try{
			while (numElem == 0)
				esperaDatos.await();
			
			int dato = buffer[j];
			j = (j+1)%N;
			numElem--;
			System.out.println("Consumidor consume "+dato);
			esperaHuecos.signal();
			return 0;
		}finally{
			l.unlock();
		}
		
	}
	public  void almacenar(int dato) throws InterruptedException{
		l.lock();
		try{
			while (numElem == N)
				esperaHuecos.await();
			System.out.println("Productor produce "+dato);
			buffer[i] = dato;
			i = (i+1)%N;
			numElem++;
		
			esperaDatos.signal();
						
		}finally{
			l.unlock();
		}
	}
}
