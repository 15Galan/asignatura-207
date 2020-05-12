package pajaros;

public class Cria extends Thread {

	private Nido nido;
	private int id;
	
	public Cria(int id,Nido nido){
		this.id=id;
		this.nido=nido;
	}
	
	public void run(){
		while(true){
			try {
				nido.comeBichito(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
