package Mesa;

public class Mesa {
	
	private int ingrediente = 0;	// Ingrediente que falta (ingrediente = 0 si la mesa está vacía).
	private boolean finFumar = true;
	
	private synchronized void nuevoIngrediente(int IDingrediente) throws InterruptedException{
		// Llamado por el agente par poner un nuevo ingrediente. Se asigna el ingrediente restante.
		
		while(!finFumar || ingrediente != 0){
			
			wait();
			
			ingrediente = IDingrediente;
			
			notifyAll();
		}
	}
	
	public synchronized void qFumar(int IDingrediente) throws InterruptedException{
		// Fumador que quiere fumar.
		
		while(ingrediente != IDingrediente){
			
			wait();
			
			ingrediente = 0;
			
			finFumar = false;
		}
	}
	
	public synchronized void tFumar(int id){
		// Fumador que termina de fumar.
		
		finFumar = true;
		
		notifyAll();
	}
}
