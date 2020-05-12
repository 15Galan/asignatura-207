package conMonitores;

public class Tenedor {

	private boolean libre=true;
	private int i;
	
	public Tenedor(int i){
		this.i=i;
	}
	public synchronized void qTenerdor(int id) throws InterruptedException{
		//llamado por el filosofo id que quiere coger el tenedor
		while(!libre)
			wait();
		System.out.println("Filosofo "+id+" tiene un tenedor "+ this.i);
		libre=false;
		
		//normalmente en monitores oucrre ver un while con una condicion booleana y luego
		//la variable booleana se cambia de forma que si alguien vulve a entrar al metodo
		//se bloqueara
	}
	
	public synchronized void dTenedor(int id){
		//llamado por el filosofo id que quiere coger el tenedor
		while(libre)
			notifyAll();
		System.out.println("Filosofo "+id+" suelta un tenedor "+this.i);
		libre=true;
	}
}
