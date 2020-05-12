package CuentaDeAhorros;

import java.util.Random;
public class Cliente extends Thread{

	private Random rnd=new Random();
	private int id;
	private Cuenta cuenta;
	
	public Cliente(int id, Cuenta cuenta){
		this.id=id;
		this.cuenta=cuenta;
	}
	
	public void run(){
		while(true){
			
			try {
				cuenta.depositar(id, rnd.nextInt(100));
				Thread.sleep(rnd.nextInt(20));
				cuenta.extraer(id, rnd.nextInt(50));
				Thread.sleep(rnd.nextInt(10));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
