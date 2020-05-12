package sensores;
import java.util.*;
public class Worker extends Thread{

	private Random r=new Random();
	private Mediciones m;
	public Worker(Mediciones m){
		this.m=m;
	}
	
	public void run(){
		while(true){
			try{
				m.leerMediciones();
				Thread.sleep(r.nextInt(100));
				m.finTarea();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
