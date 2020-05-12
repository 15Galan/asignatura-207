package pajaros;

public class Padre extends Thread{
	private Nido nido;
	private int id;
	
	public Padre(int id,Nido nido){
		this.id=id;
		this.nido=nido;
	}
	
	public void run(){
		while(true){
			try {
				nido.nuevoBichito(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
