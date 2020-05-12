package Junio2013;

import java.util.concurrent.*;

public class Buffer {

	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore esperaP=new Semaphore(1,true);
	private Semaphore esperaI=new Semaphore(1,true);
	private Semaphore esperaD=new Semaphore(0,true);
	private Semaphore esperaPos[];
	
	private int []b;
	private int hPar;
	private int hImpar;
	private int sig;
	
	public Buffer(int N){
		b=new int[N];
		
		esperaPos=new Semaphore[N];
		for(int i=0;i<esperaPos.length;i++){
			esperaPos[i]=new Semaphore(0,true);
		}
		
		for(int i=0;i<b.length;i++){
			b[i]=-1;
		}
		hPar=N/2;
		hImpar=N/2;
	}
	
	public void nuevoDatoPar(int dato) throws InterruptedException{
		esperaP.acquire();
		mutex.acquire();
		hPar--;
		if(hPar<0){
			System.out.println("Productor Par se duerme");
			mutex.release();
			esperaP.acquire();
			mutex.acquire();
		}

		b[dato%b.length]=1;
		esperaPos[dato%esperaPos.length].release();
		System.out.println("Prodcutor Par pone nuevo dato. Huecos "+hPar);
		esperaP.release();
		mutex.release();
	}
	
	public void nuevoDatoImpar(int dato) throws InterruptedException{
		esperaI.acquire();
		mutex.acquire();
		hImpar--;
		if(hImpar<0){
			System.out.println("Productor Impar se duerme");
			mutex.release();
			esperaI.acquire();
			mutex.acquire();
		}
		b[dato%b.length]=1;
		esperaPos[dato%esperaPos.length].release();
		System.out.println("Productor Impar pone nuevo dato. Huecos "+hImpar);
		esperaI.release();
		mutex.release();
	}
	
	public void leerDato() throws InterruptedException{
		esperaPos[sig%esperaPos.length].acquire();
		mutex.acquire();
		b[sig%b.length]=-1;
		if(sig%2==0){
			hPar++;
			if(hPar==0){
				esperaP.release();
			}
		}else{
			hImpar++;
			if(hImpar==0){
				esperaI.release();
			}
		}
		sig++;
		System.out.println("Consumidor consume dato.");
		mutex.release();
	}
}
