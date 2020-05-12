package sensores;
import java.util.*;
public class Sensor extends Thread{

	private Random r= new Random();
	private int id;
	private Mediciones m;
	public Sensor(int id,Mediciones m){
		this.id=id;
		this.m=m;
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(r.nextInt(100));
				m.nuevaMedicion(id);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
