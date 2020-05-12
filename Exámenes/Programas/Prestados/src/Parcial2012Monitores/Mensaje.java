package Parcial2012Monitores;

import java.util.concurrent.locks.*;

public class Mensaje {

	private Lock l= new ReentrantLock(true);
	private Condition cEsperaP=l.newCondition();
	private Condition cEsperaC=l.newCondition();
	
	private int huecos;
	private int ms[];
	
	public Mensaje(int tam){
		ms=new int [tam];
		
		for(int i=0;i<tam;i++){
			ms[i]=-1;
		}
		
		huecos=tam;
	}
	
	public void nuevoMs() throws InterruptedException{
		l.lock();
		try{
			huecos--;
			while(huecos<0){
				cEsperaP.await();
			}
			int i=0;
			while(ms[i]!=-1){
				i++;
			}
			ms[i]=1;
			System.out.println("Productor pone un nuevo mensaje. Huecos "+huecos);
		}finally{
			l.unlock();
		}
	}
	
	public int leoMs(int id) throws InterruptedException{
		l.lock();
		try{
			while(huecos==ms.length){
				cEsperaC.await();
			}
			huecos++;
			int i=0;
			while(ms[i]==-1){
				i++;
			}
			ms[i]=-1;
			System.out.println("Consumidor "+id+" consume un mensaje. Huecos "+huecos);
			
			return 0;

		}finally{
			l.unlock();
		}
		
		
			}
}
