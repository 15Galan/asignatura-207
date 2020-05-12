package ejercicio2Relacion;

import java.util.concurrent.locks.*;

public class Control {

	private Lock l = new ReentrantLock();
	private int Rec = 100;
	private Condition cPrim = l.newCondition();
	private Condition cRest = l.newCondition();
	private int esperando=0;

	public void qRecursos(int id, int num) throws InterruptedException {
		l.lock();
		try {

			esperando++;
			if(esperando>1){
				cRest.await();
			}
			
			while(Rec-num<0){
				cPrim.await();
			}
			
			Rec-=num;
			esperando--;
			cRest.signal();

			
		} finally {
			l.unlock();
		}
	}

	public void libRecursos(int id, int num) {
		l.lock();
		try {
			Rec+=num;
			cPrim.signal();
		} finally {
			l.unlock();
		}
	}
}
