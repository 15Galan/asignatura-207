package Tribu;

public class Canibal extends Thread {

	private Olla olla;
	private int id;
	
	public Canibal(int id,Olla olla){
		this.id=id;
		this.olla=olla;
	}
	
	public void run(){
		while(true){
			try {
				olla.come(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
