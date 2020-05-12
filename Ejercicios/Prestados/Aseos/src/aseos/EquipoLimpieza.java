package aseos;

import java.util.Random;

public class EquipoLimpieza extends Thread{
	
	private Aseos aseo;
	private static Random r = new Random();
	
	public EquipoLimpieza(Aseos aseo){
		this.aseo = aseo;
	}
	
	
	public void run(){
		while (true){
			
			try {
				aseo.entraEquipoLimpieza();
				Thread.sleep(r.nextInt(1000));
				aseo.saleEquipoLimpieza();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
