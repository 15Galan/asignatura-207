package montanaRusa;


public class Coche extends Thread{
	private MontanaRusa coche;


	
	public Coche(MontanaRusa coche){

		this.coche=coche;
	}
	
	public void run(){
		while(true){
			try {
				coche.paseo();
//				Thread.sleep(2000);//el coche pasea durante 2 segundos
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
