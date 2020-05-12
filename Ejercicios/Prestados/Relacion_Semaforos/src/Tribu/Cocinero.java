package Tribu;

public class Cocinero extends Thread {
	private Olla olla;
	private int id;
	
	public Cocinero(int id,Olla olla){
		this.id=id;
		this.olla=olla;
	}
	
	public void run(){
		while(true){
			try {
				olla.cocina(id);;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
