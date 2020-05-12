package CuentaDeAhorros;

import java.util.concurrent.locks.*;
public class Cuenta {

	private Lock l=new ReentrantLock();
	private int balance;
	private Condition cE=l.newCondition();
	//private Condition cD=l.newCondition();
	
	public Cuenta(){
		balance=0;
	}
	
	public void depositar(int id, int cant){
		l.lock();
		try{
			balance+=cant;
			System.out.println("Deposito de "+cant+"€ realizado por el cliente "+id+". Balance = "+balance+"€");
			
			if(balance>0){
				cE.signalAll();
			}	
		}finally{
			l.unlock();
		}
	}
	
	public void extraer(int id, int cant) throws InterruptedException{
		l.lock();
		try{
		while(balance-cant<0){
			cE.await();
		}
			balance-=cant;
			System.out.println("Extraccion de "+cant+"€ realizada por el cliente "+id+". Balance = "+balance+"€");

			
		}finally{
			l.unlock();
		}
	}
}
