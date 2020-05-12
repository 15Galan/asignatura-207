package recursosEsqueleto;

import java.util.concurrent.locks.*;
public class GestorRecursos {
	
	private Lock l=new ReentrantLock(true);
	private Condition primer=l.newCondition();
	private Condition resto=l.newCondition();
	private int num;
	private int esperando=0;
	
	public GestorRecursos(int num){
		this.num=num;
	}
	
	public void quieroRecursos(int id,int n) throws InterruptedException{
		//el cliente id necesita n recursos
		l.lock();
		try{
			System.out.println("Cliente  "+id+" pide "+n+" recursos. Quedan "+num);

			esperando++;
			if(esperando>1){	//es un if porque si fuese un while cuando se despertara reevaluaria la condicion y si en la cola hay 2 esperando
								//no saldria nunca del while
				resto.await();
			}
			
			while(num<n){	//hace falta un while porque cuando otro proceso libere recursos puede que no libere rescursos suficientes
				primer.await();
			}
			
			num-=n;
			System.out.println("Cliente  "+id+" toma "+n+" recursos. Quedan "+num);
			esperando--;
			resto.signal();	//despierta al segndo de la cola
			
			
			
			
			
		}finally{
			l.unlock();
		}
		
	}

	
	public  void devuelvoRecursos(int id, int n){
		//cliente id devuelve n recursos
		l.lock();
		try{
			num+=n;
			System.out.println("Cliente  "+id+" devuelve "+n+" recursos. Quedan "+num);			
			primer.signal();
			
			
			
		}finally{
			l.unlock();
		}
	
	}
	
	
}
